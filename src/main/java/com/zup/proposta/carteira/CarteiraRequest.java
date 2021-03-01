package com.zup.proposta.carteira;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class CarteiraRequest {

    @Email
    @NotBlank
    private String email;

    @NotNull
    @NotBlank
    private CarteiraTipoEnum carteiraTipoenum;

    public CarteiraRequest(@NotBlank String email,
                           @NotBlank CarteiraTipoEnum carteiraTipoenum) {
        this.email = email;
        this.carteiraTipoenum = carteiraTipoenum;
    }

    public String getEmail() {
        return email;
    }

    public CarteiraTipoEnum getCarteiraTipoenum() {
        return carteiraTipoenum;
    }
}