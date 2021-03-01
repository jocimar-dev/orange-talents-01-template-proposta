package com.zup.proposta.proposta;

import com.zup.proposta.consulta.ConsultaMap;
import com.zup.proposta.consulta.ConsultaRequest;
import com.zup.proposta.consulta.ConsultaResponse;
import com.zup.proposta.enums.PropostaStatusEnum;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class PropostaController {

    private final PropostaRepository repository;
    private final ConsultaMap consultaMap;

    @Autowired
    public PropostaController(PropostaRepository repository, ConsultaMap consultaMap) {
        this.repository = repository;
        this.consultaMap = consultaMap;
    }

    @PostMapping("/propostas")
    @Transactional
    public ResponseEntity<?> criaNovaProposta(@RequestBody @Valid NovaPropostaRequest request,
                                              UriComponentsBuilder builder) throws InterruptedException {
            Optional<Proposta> possivelProposta = repository.findByDocumento(request.getDocumento());
            if(possivelProposta.isPresent()) {
                return ResponseEntity.unprocessableEntity()
                        .body(new RejectedValue
                                ("documento",
                                        "Já existe uma proposta cadastrada para o documento "
                                                +request.getDocumento()));
        }

        Proposta proposta = request.toModel();
        repository.save(proposta);

            try {
                ConsultaResponse resultado = consultaMap.cadastraSolicitacao(new ConsultaRequest(proposta));
                proposta.atualizaStatus(resultado.getResultado().getPropostaStatus());
            }catch(FeignException.UnprocessableEntity ex) {
                proposta.atualizaStatus(PropostaStatusEnum.NAO_ELEGIVEL);
            }
        repository.save(proposta);

        URI location = builder
                .path("/api/propostas/{id}")
                .buildAndExpand(proposta.getId())
                .toUri();
        return ResponseEntity
                .created(location)
                .build();
    }

}
