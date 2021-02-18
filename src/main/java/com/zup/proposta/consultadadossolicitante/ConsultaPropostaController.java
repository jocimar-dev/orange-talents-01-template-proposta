package com.zup.proposta.consultadadossolicitante;

import com.zup.proposta.proposta.Proposta;
import com.zup.proposta.proposta.PropostaRepository;
import com.zup.proposta.proposta.PropostaResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class ConsultaPropostaController {

    @Autowired
    private PropostaRepository propostaRepository;

    @GetMapping("/propostas/{id}")
    public ResponseEntity<?> busca(@PathVariable Long id) {
        Optional<Proposta> possivelProposta = propostaRepository.findById(id);
        if(possivelProposta.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(new PropostaResponse(possivelProposta.get()));
    }

}
