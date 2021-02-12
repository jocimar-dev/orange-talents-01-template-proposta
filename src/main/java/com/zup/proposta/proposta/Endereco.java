package com.zup.proposta.proposta;

import javax.persistence.Embeddable;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import java.util.StringJoiner;

@Embeddable
@Table(name = "endereco")
public class Endereco {

    @NotBlank
    private String cep;

    @NotBlank
    private String logradouro;

    @NotBlank
    private String numero;

    @NotBlank
    private String bairro;

    @NotBlank
    private String complemento;

    @NotBlank
    private String municipio;

    public Endereco(@NotBlank String cep,
                    @NotBlank String logradouro,
                    @NotBlank String numero,
                    @NotBlank String bairro,
                    @NotBlank String complemento,
                    @NotBlank String municipio) {
        super();
        this.cep = cep;
        this.logradouro = logradouro;
        this.numero = numero;
        this.bairro = bairro;
        this.complemento = complemento;
        this.municipio = municipio;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Endereco.class.getSimpleName() + "[", "]")
                .add("cep='" + cep + "'")
                .add("logradouro='" + logradouro + "'")
                .add("numero='" + numero + "'")
                .add("bairro='" + bairro + "'")
                .add("complemento='" + complemento + "'")
                .add("municipio='" + municipio + "'")
                .toString();
    }

    @Deprecated
    public Endereco() {

    }
}
