package com.zup.proposta.consulta;

import java.util.StringJoiner;

public class ConsultaResponse {

    private String propostaId;

    private String nome;

    private String documento;

    private StatusConsultaEnum resultado;

    public ConsultaResponse(String nome,
                            String documento,
                            StatusConsultaEnum resultado) {
        this.nome = nome;
        this.documento = documento;
        this.resultado = resultado;
    }

    public String getPropostaId() {
        return propostaId;
    }

    public String getNome() {
        return nome;
    }

    public String getDocumento() {
        return documento;
    }

    public StatusConsultaEnum getResultado() {
        return resultado;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", ConsultaResponse.class.getSimpleName() + "[", "]")
                .add("propostaId='" + propostaId + "'")
                .add("nome='" + nome + "'")
                .add("documento='" + documento + "'")
                .add("resultado='" + resultado + "'")
                .toString();
    }

}
