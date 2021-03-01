package com.zup.proposta.carteira;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CarteiraClienteRequest {

    @JsonProperty
    private String email;

    @JsonProperty
    private String carteira;

    public CarteiraClienteRequest(CarteiraRequest request) {
        this.email = request.getEmail();
        this.carteira = request.getCarteiraTipoenum().toString();
    }
}