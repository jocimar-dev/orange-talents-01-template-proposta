package com.zup.proposta.cartao;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class CartaoResponse {

    @JsonProperty
    private String id;

    @JsonProperty
    private String titular;

    @JsonProperty
    private LocalDateTime emitidoEm;

    @JsonProperty
    private Long idProposta;

    @JsonProperty
    private BigDecimal limite;

    public CartaoResponse(String titular,
                          LocalDateTime emitidoEm,
                          Long idProposta,
                          BigDecimal limite) {
        this.titular = titular;
        this.emitidoEm = emitidoEm;
        this.idProposta = idProposta;
        this.limite = limite;
    }

    public String getId() {
        return id;
    }

    public String getTitular() {
        return titular;
    }

    public LocalDateTime getEmitidoEm() {
        return emitidoEm;
    }

    public Long getIdProposta() {
        return idProposta;
    }

    public BigDecimal getLimite() {
        return limite;
    }
}
