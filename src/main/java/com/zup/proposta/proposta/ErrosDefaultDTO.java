package com.zup.proposta.proposta;

public class ErrosDefaultDTO {

    private String campo;
    private String erro;

    public ErrosDefaultDTO(String campo, String erro) {
        this.campo = campo;
        this.erro = erro;
    }

    public String getCampo() {
        return campo;
    }

    public String getErro() {
        return erro;
    }
}
