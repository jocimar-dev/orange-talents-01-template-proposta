package com.zup.proposta.cartao;

import com.fasterxml.jackson.annotation.JsonCreator;
import org.apache.tomcat.util.codec.binary.Base64;

import javax.validation.constraints.NotBlank;

public class NovoCartaoBiometriaRequest {

    @NotBlank
    private String texto;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public NovoCartaoBiometriaRequest(@NotBlank String texto) {
        this.texto = texto;
    }

    public boolean forBase64() {
        return Base64.isBase64(texto.getBytes());
    }

    public String getTexto() {
        return texto;
    }
}
