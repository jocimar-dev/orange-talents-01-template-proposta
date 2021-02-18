package com.zup.proposta.proposta;

import com.zup.proposta.consultadadossolicitante.ConsultaMap;
import com.zup.proposta.consultadadossolicitante.ConsultaRequest;
import com.zup.proposta.consultadadossolicitante.ConsultaResponse;
import feign.FeignException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import java.util.HashMap;

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
                                              UriComponentsBuilder builder){
        if(repository.existsByDocumento(request.getDocumento())) {
            HashMap<String, Object>  resposta = new HashMap<>();
            resposta.put("resposta", "Documento já cadastrado no sistema: ");
            return ResponseEntity.unprocessableEntity().body(resposta + request.getDocumento());
        }

        Proposta proposta = request.toModel();
        repository.save(proposta);

        try {
            ConsultaResponse resultado = consultaMap.consultas(new ConsultaRequest(proposta));
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
