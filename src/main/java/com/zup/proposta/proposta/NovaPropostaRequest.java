package com.zup.proposta.proposta;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.zup.proposta.proposta.endereco.EnderecoRequest;
import com.zup.proposta.validator.CPForCNPJ;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;

public class NovaPropostaRequest {

    @NotBlank
    private String nome;

    @CPForCNPJ
    @NotBlank
    private String documento;

    @NotBlank
    @Email
    @JsonProperty
    private String email;

    @JsonProperty
    @NotNull
    @Valid
    private EnderecoRequest endereco;

    @NotNull
    @PositiveOrZero
    @JsonProperty
    private BigDecimal salario;

    public NovaPropostaRequest(@NotBlank String documento,
                               @NotBlank String nome,
                               @NotBlank @Email String email,
                               @NotNull @Valid EnderecoRequest endereco,
                               @NotNull @PositiveOrZero BigDecimal salario) {
        this.documento = documento;
        this.nome = nome;
        this.email = email;
        this.endereco = endereco;
        this.salario = salario;
    }

    public String getDocumento() {
        return documento;
    }

    public EnderecoRequest getEndereco() {
        return endereco;
    }

    public Proposta toModel() {
        return new Proposta(nome, documento, email, endereco.toModel(), salario);
    }
}
