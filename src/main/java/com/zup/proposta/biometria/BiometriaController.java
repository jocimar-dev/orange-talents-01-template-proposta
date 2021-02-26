package com.zup.proposta.biometria;

import com.zup.proposta.cartao.Cartao;
import com.zup.proposta.exceptions.RejectedValue;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/api")
public class BiometriaController {

    @PersistenceContext
    private EntityManager manager;

    @Transactional
    @PostMapping("/cartoes/{id}/biometrias")
    public ResponseEntity<?> cadastra(@PathVariable Long id,
                                      @RequestBody @Valid BiometriaRequest request,
                                      UriComponentsBuilder builder) {

        Cartao cartao = manager.find(Cartao.class, id);
        if(cartao == null){
            return ResponseEntity.notFound().build();
        }

        if(!request.forBase64()){
            RejectedValue fieldError = new RejectedValue("biometria",
                    "Biometria não é inválida");
            return ResponseEntity.badRequest()
                    .body(fieldError);
        }
        Biometria biometria = new Biometria(cartao, request.getTexto());
        manager.persist(biometria);

        URI location = builder.path("/cartoes/{idCartao}/biometrias/{idBiometria}")
                .buildAndExpand(cartao.getId(), biometria.getId()).toUri();
        return ResponseEntity
                .created(location)
                .body("Biometria criada com sucesso!");
    }

}
