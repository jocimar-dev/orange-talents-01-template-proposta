package com.zup.proposta.proposta;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RejectedValueProp {

    @JsonProperty
    private String mensagem;

    @JsonProperty
    private String campo;

    public RejectedValueProp(String mensagem, String campo) {
        this.mensagem = mensagem;
        this.campo = campo;
    }
}
