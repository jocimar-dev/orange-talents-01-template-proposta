package com.zup.proposta.bloqueios;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RejectedValueBloqueio {

    @JsonProperty
    private String mensagem;

    @JsonProperty
    private String campo;

    public RejectedValueBloqueio(String mensagem, String campo) {
        this.mensagem = mensagem;
        this.campo = campo;
    }
}
