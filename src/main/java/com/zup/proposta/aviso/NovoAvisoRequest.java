package com.zup.proposta.aviso;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.StringJoiner;

public class NovoAvisoRequest {

    @NotBlank
    private String destino;

    @Future
    @NotNull
    private LocalDate validoAte;

    public NovoAvisoRequest(@NotBlank String destino,
                            @Future @NotNull LocalDate validoAte) {
        this.destino = destino;
        this.validoAte = validoAte;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", NovoAvisoRequest.class.getSimpleName() + "[", "]")
                .add("destino='" + destino + "'")
                .add("validoAte=" + validoAte)
                .toString();
    }

    public String getDestino() {
        return destino;
    }

    public LocalDate getValidoAte() {
        return validoAte;
    }

}
