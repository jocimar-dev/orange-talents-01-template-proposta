package com.zup.proposta.bloqueios;

import com.zup.proposta.cartao.Cartao;
import com.zup.proposta.cartao.CartaoClient;
import com.zup.proposta.cartao.CartaoRepository;
import com.zup.proposta.cliente.ServletRequestClient;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpServletRequest;

@RestController
public class BloqueioController {

    private final static String APP_NAME = "api-proposta";

    private final CartaoRepository cartaoRepository;
    private final BloqueioRepository bloqueioRepository;
    private final CartaoClient cartaoClient;

    @Autowired
    public BloqueioController(CartaoRepository cartaoRepository,
                              BloqueioRepository bloqueioRepository,
                              CartaoClient cartaoClient) {

        this.cartaoRepository = cartaoRepository;
        this.bloqueioRepository = bloqueioRepository;
        this.cartaoClient = cartaoClient;
    }
    @PutMapping("/cartoes/{id}/bloqueio")
    @Transactional(timeout = 5000)
    public ResponseEntity<?> createNewBiometry(@PathVariable Long id, HttpServletRequest request) {
        Cartao cartaoId = cartaoRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Cartão não econtrado!"));

        if (cartaoId.cartaoBloqueado()) {
            return ResponseEntity.unprocessableEntity().body(new RejectedValueBloqueio("bloqueio",
                    "Este cartão já encontra-se bloqueado!"));
        }

        ServletRequestClient servletRequestClient = new ServletRequestClient(request);

        String clienteIp = servletRequestClient.atualiza();
        String userAgent = request.getHeader("User-Agent");

        try {
            cartaoClient.bloqueioCartoes(cartaoId.getNumero(), new BloqueioRequest(APP_NAME));
        } catch (FeignException e) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "Este cartão já está bloqueado!");
        }

        BloqueioCartao bloqueioCartao = new BloqueioCartao(clienteIp, userAgent, cartaoId);
        cartaoId.cartaoBloqueado();

        bloqueioRepository.save(bloqueioCartao);
        cartaoRepository.save(cartaoId);

        return ResponseEntity.ok().build();
      }


}
