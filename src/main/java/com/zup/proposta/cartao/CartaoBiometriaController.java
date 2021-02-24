package com.zup.proposta.cartao;

import com.zup.proposta.proposta.exceptions.RejectedValue;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;
import java.net.URI;

@RestController
public class CartaoBiometriaController {

    @PersistenceContext
    private EntityManager manager;

    @Transactional
    @PostMapping("/cartoes/{id}/biometrias")
    public ResponseEntity<?> cadastra(@PathVariable Long id,
                                      @RequestBody @Valid NovoCartaoBiometriaRequest request,
                                      UriComponentsBuilder uriBuilder) {

        Cartao cartao = manager.find(Cartao.class, id);
        if(cartao == null){
            return ResponseEntity.notFound().build();
        }

        if(!request.forBase64()){
            RejectedValue fieldError = new RejectedValue("biometria", "Esta biometria está inválida");
            return ResponseEntity.badRequest()
                    .body(fieldError);
        }
        CartaoBiometria cartaoBiometria = new CartaoBiometria(cartao, request.getTexto());
        manager.persist(cartaoBiometria);

        URI location = uriBuilder.path("/cartoes/{idCartao}/biometrias/{idBiometria}").buildAndExpand(cartao.getId(), cartaoBiometria.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

}
