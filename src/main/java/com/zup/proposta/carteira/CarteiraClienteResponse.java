package com.zup.proposta.carteira;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class CarteiraClienteResponse {

    private String id;

    private String resultado;

    public String getId() {
        return id;
    }

    public String getResultado() {
        return resultado;
    }
}