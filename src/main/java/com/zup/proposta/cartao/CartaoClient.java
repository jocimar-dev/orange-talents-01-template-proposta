package com.zup.proposta.cartao;

import com.zup.proposta.bloqueios.BloqueioRequest;
import com.zup.proposta.bloqueios.BloqueioResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "cartao", url = "${cartao.proposta.cadastro}")
public interface CartaoClient {

    @GetMapping("/api/cartoes")
    CartaoResponse consulta(@RequestParam("idProposta") Long idProposta);

    @PostMapping("/api/cartoes/{id}")
    CartaoResponse associaCartoesProposta(@PathVariable String id, @RequestBody CartaoRequest cartoesPropostaRequest);

    @PostMapping("/api/cartoes/{id}/bloqueios")
    BloqueioResponse bloqueioCartoes(@PathVariable String id, @RequestBody BloqueioRequest request);


}
