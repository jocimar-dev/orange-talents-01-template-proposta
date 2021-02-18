package com.zup.proposta.cartao;

public class RejectValue {


    private String mensagem;

    private String campo;

    public RejectValue(String mensagem, String campo) {
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
