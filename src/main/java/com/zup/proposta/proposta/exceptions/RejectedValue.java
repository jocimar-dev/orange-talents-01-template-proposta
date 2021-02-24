package com.zup.proposta.proposta.exceptions;

public class RejectedValue {


    private String mensagem;

    private String campo;

    public RejectedValue(String mensagem, String campo) {
        this.mensagem = mensagem;
        this.campo = campo;
    }

    public String getMensagem() {
        return mensagem;
    }

    public String getCampo() {
        return campo;
    }
}
