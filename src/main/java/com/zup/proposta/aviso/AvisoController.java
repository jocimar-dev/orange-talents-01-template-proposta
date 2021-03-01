package com.zup.proposta.aviso;

import com.zup.proposta.cartao.Cartao;
import com.zup.proposta.cartao.CartaoClient;
import com.zup.proposta.cartao.CartaoRepository;
import com.zup.proposta.service.RequestHeadersService;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
public class AvisoController {

    private final CartaoRepository cartaoRepository;
    private final CartaoClient cartaoClient;
    private final RequestHeadersService requestHeadersService;

    @Autowired
    public AvisoController(CartaoRepository cartaoRepository,
                           CartaoClient cartaoClient,
                           RequestHeadersService requestHeadersService) {
        this.cartaoRepository = cartaoRepository;
        this.cartaoClient = cartaoClient;
        this.requestHeadersService = requestHeadersService;
    }

    @PostMapping("/cartoes/{id}/avisos")
    public ResponseEntity<?> cadastraAviso(@PathVariable Long id,
                                           @RequestBody @Valid NovoAvisoRequest avisoRequest,
                                           HttpServletRequest request) {

        Optional<Cartao> possivelCartao = cartaoRepository.findById(id);

        if(possivelCartao.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        Cartao cartao = possivelCartao.get();
        try {
            cartaoClient.avisaCliente(cartao.getNumero(), new AvisoClienteRequest(avisoRequest));
            cartao.associarAviso(avisoRequest, requestHeadersService.getRequestHeaders(request));
            cartaoRepository.save(cartao);
        }catch (FeignException ex){
            System.out.println(ex);
            Map<String, Object> errors = new HashMap<>();
            errors.put("violação", "Aviso não realizado!");
            return ResponseEntity.badRequest().body(errors);
        }
        return ResponseEntity.ok().build();
    }

}