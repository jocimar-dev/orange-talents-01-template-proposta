package com.zup.proposta.cartao;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "cartao", url = "http://localhost:8888")
public interface CartaoClient {

    @GetMapping("/api/cartoes")
    CartaoResponse consulta(@RequestParam("idProposta") Long idProposta);

    @PostMapping("/api/cartoes/{id}")
    CartaoResponse associaCartoesProposta(@PathVariable String id, @RequestBody CartaoRequest cartoesPropostaRequest);


}
