package com.zup.proposta.carteira;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "carteira", url = "${cartao.proposta.cadastro}")
public interface CarteiraCliente {

    @PostMapping("/api/cartoes/{id}/carteiras")
    CarteiraClienteResponse adiciona(@PathVariable String id, @RequestBody CarteiraClienteRequest request);
}