package com.zup.proposta.proposta;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.zup.proposta.cartao.Cartao;
import com.zup.proposta.cartao.CartaoResponse;
import com.zup.proposta.proposta.endereco.Endereco;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.util.StringJoiner;

import static com.zup.proposta.proposta.PropostaStatusEnum.GERADO;

@Entity
@Table(name = "propostas")
public class Proposta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String titular;

    @NotBlank
    private String documento;

    @NotBlank
    @Email
    private String email;

    @NotNull
    @Embedded
    @Valid
    private Endereco endereco;

    @NotNull
    @JsonProperty
    @Positive
    private BigDecimal salario;

    @NotNull
    @Enumerated(EnumType.STRING)
    private PropostaStatusEnum statusProposta = GERADO;

    @OneToOne(mappedBy = "proposta", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Cartao cartao;

    @NotNull
    @Enumerated(EnumType.STRING)
    private PropostaStatusEnum estado = GERADO;


    public Proposta(@NotBlank String titular,
                    @NotBlank String documento,
                    @NotBlank @Email String email,
                    @NotNull @Valid Endereco endereco,
                    @NotNull @Positive BigDecimal salario
                    ) {
        this.titular = titular;
        this.documento = documento;
        this.email = email;
        this.endereco = endereco;
        this.salario = salario;
        }

    @Override
    public String toString() {
        return new StringJoiner(", ", Proposta.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("nome='" + titular + "'")
                .add("documento='" + documento + "'")
                .add("email='" + email + "'")
                .add("endereco=" + endereco)
                .add("salario=" + salario)
                .add("statusProposta=" + statusProposta)
                .add("cartao=" + cartao)
                .toString();
    }

    public Long getId() {
        return id;
    }

    public String getTitular() {
        return titular;
    }

    public String getDocumento() {
        return documento;
    }

    public String getEmail() {
        return email;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public BigDecimal getSalario() {
        return salario;
    }

    public PropostaStatusEnum getStatusProposta() {
        return statusProposta;
    }

    public PropostaStatusEnum getEstado() {
        return estado;
    }

    @Deprecated
    public Proposta() {
    }

    public void atualizaStatus(PropostaStatusEnum consultaEnum) {
        this.statusProposta = statusProposta;
    }

    public void associaCartao(CartaoResponse response) {
        this.cartao = new Cartao(response.getId(), response.getTitular(), response.getEmitidoEm(), response.getLimite(), this);
    }
}

