package com.zup.proposta.proposta;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.zup.proposta.consultadadossolicitante.StatusConsultaEnum;
import com.zup.proposta.validator.CPForCNPJ;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.util.StringJoiner;

import static com.zup.proposta.consultadadossolicitante.StatusConsultaEnum.CRIADO;

@Entity
@Table(name = "propostas")
public class Proposta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String nome;

    @CPForCNPJ
    private String documento;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    @Embedded
    @Valid
    private Endereco endereco;

    @NotBlank
    @JsonProperty
    @Positive
    private BigDecimal salario;

    @NotNull
    @Enumerated(EnumType.STRING)
    private StatusConsultaEnum estado = CRIADO;

    public Proposta(@NotBlank String nome,
                    String documento,
                    @NotBlank @Email String email,
                    @NotBlank Endereco endereco,
                    @NotBlank @Positive BigDecimal salario) {
        this.nome = nome;
        this.documento = documento;
        this.email = email;
        this.endereco = endereco;
        this.salario = salario;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Proposta.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("nome='" + nome + "'")
                .add("documento='" + documento + "'")
                .add("email='" + email + "'")
                .add("endereco=" + endereco)
                .add("Salario=" + salario)
                .add("estado=" + estado)
                .toString();
    }

    public String getDocumento() {
        return documento;
    }

    public String getNome() {
        return nome;
    }

    public Long getId() {
        return id;
    }

    @Deprecated
    public Proposta() {
    }


    public void atualizaEstado(StatusConsultaEnum estado) {
        this.estado = estado;
    }
}
