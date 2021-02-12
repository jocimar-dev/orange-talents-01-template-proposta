package com.zup.proposta.proposta;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

public class NovaPropostaRequest {

    @JsonProperty
    @NotBlank
    private String documento;

    @NotBlank
    private String nome;

    @NotBlank
    @Email
    @JsonProperty
    private String email;

    @JsonProperty
    private @NotBlank Endereco endereco;

    @NotBlank
    @Positive
    @JsonProperty
    private BigDecimal salario;


    public NovaPropostaRequest(@NotBlank String documento,
                               @NotBlank String nome,
                               @NotBlank @Email String email,
                               @NotBlank Endereco endereco,
                               @NotBlank @Positive BigDecimal salario,
                               String estado) {
        this.documento = documento;
        this.nome = nome;
        this.email = email;
        this.endereco = endereco;
        this.salario = salario;
    }

    public boolean propostasCadastradasParaODocumento(EntityManager em) {
        Query query = em.createQuery("select 1 from Proposta where documento = :documento");
        query.setParameter("documento", documento);
        var lista = query.getResultList();

        Assert.state(lista.size() <= 1, "Há uma proposta cadastrada para este documento: "+documento);

        return !lista.isEmpty();
    }

    @Deprecated
    public NovaPropostaRequest() {

    }

    public String getDocumento() {
        return documento;
    }

    public Proposta toModel() {
        return new Proposta(documento, nome, email, endereco, salario);
    }
}
