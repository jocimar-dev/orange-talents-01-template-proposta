package com.zup.proposta.proposta;

import feign.FeignException;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;
import java.net.URI;
import java.util.HashMap;

@RestController("/api")
public class PropostaController {

    private final PropostaRepository repository;

    public PropostaController(PropostaRepository repository) {
        this.repository = repository;
    }


    @PostMapping("/propostas")
    @Transactional
    public ResponseEntity<?> criaNovaProposta(@RequestBody @Valid NovaPropostaRequest request,
                                              UriComponentsBuilder builder){
        if(repository.existsByDocumento(request.getDocumento())) {
            HashMap<String, Object>  resposta = new HashMap<>();
            resposta.put("resposta", "Documento já cadastrado no sistema");
            return ResponseEntity.unprocessableEntity().body(resposta);
        }

        Proposta proposta = request.toModel();
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
