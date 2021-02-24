package com.zup.proposta.proposta;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.zup.proposta.proposta.endereco.EnderecoResponse;

import java.math.BigDecimal;

public class PropostaResponse {

    @JsonProperty
    private String documento;
    @JsonProperty
    private String email;
    @JsonProperty
    private String nome;
    @JsonProperty
    private BigDecimal salario;
    @JsonProperty
    private EnderecoResponse endereco;
    @JsonProperty
    private String status;

    public PropostaResponse(Proposta proposta) {
        this.documento = proposta.getDocumento();
        this.email = proposta.getEmail();
        this.nome = proposta.getTitular();
        this.salario = proposta.getSalario();
        this.endereco = new EnderecoResponse(proposta.getEndereco());
        this.status = proposta.getStatusProposta().getStatus();
    }

}
