package com.zup.proposta.consultadadossolicitante;

import java.util.StringJoiner;

public class ConsultaResponse {

    private String propostaId;

    private String nome;

    private String documento;

    private StatusConsultaEnum resultado;


    @Override
    public String toString() {
        return new StringJoiner(", ", ConsultaResponse.class.getSimpleName() + "[", "]")
                .add("propostaId='" + propostaId + "'")
                .add("nome='" + nome + "'")
                .add("documento='" + documento + "'")
                .add("resultado='" + resultado + "'")
                .toString();
    }


    public StatusConsultaEnum getResultado() {
        return resultado;
    }
}
