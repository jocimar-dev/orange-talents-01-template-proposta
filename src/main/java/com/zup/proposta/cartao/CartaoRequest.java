package com.zup.proposta.cartao;

import com.zup.proposta.proposta.Proposta;

public class CartaoRequest {

    private Long idProposta;

    public CartaoRequest(Proposta proposta) {
        this.idProposta = proposta.getId();
    }

    public Long getIdProposta() {
        return idProposta;
    }

}
