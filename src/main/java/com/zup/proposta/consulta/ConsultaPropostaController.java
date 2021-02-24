package com.zup.proposta.consulta;

import com.zup.proposta.proposta.Proposta;
import com.zup.proposta.proposta.PropostaRepository;
import com.zup.proposta.proposta.PropostaResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api")
public class ConsultaPropostaController {

    private final PropostaRepository propostaRepository;

    public ConsultaPropostaController(PropostaRepository propostaRepository) {
        this.propostaRepository = propostaRepository;
    }

    @GetMapping("/propostas/{id}")
    public ResponseEntity<?> buscaProposta(@PathVariable Long id) {
        Optional<Proposta> novaProposta = propostaRepository.findById(id);
        if(novaProposta.isEmpty()){
            return ResponseEntity
                    .notFound()
                    .build();
        }
        return ResponseEntity.ok(new PropostaResponse(novaProposta.get()));
    }

}
