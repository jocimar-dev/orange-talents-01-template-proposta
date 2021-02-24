package com.zup.proposta.consulta;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.zup.proposta.proposta.Proposta;

public class ConsultaRequest {

    @JsonProperty("idProposta")
    private Long idProposta;

    @JsonProperty("nome")
    private String nome;

    @JsonProperty("documento")
    private String documento;


    public ConsultaRequest(Proposta proposta) {
        this.idProposta = proposta.getId();
        this.nome = proposta.getTitular();
        this.documento = proposta.getDocumento();
    }

    public Long getIdProposta() {
        return idProposta;
    }

    public String getNome() {
        return nome;
    }

    public String getDocumento() {
        return documento;
    }


}
