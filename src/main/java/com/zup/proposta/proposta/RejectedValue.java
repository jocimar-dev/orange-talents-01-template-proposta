package com.zup.proposta.proposta;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RejectedValue {

    @JsonProperty
    private String mensagem;

    @JsonProperty
    private String campo;

    public RejectedValue(String mensagem, String campo) {
        this.mensagem = mensagem;
        this.campo = campo;
    }
}
