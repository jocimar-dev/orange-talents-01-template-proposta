package com.zup.proposta.consulta;

import com.zup.proposta.enums.StatusConsultaEnum;

import java.util.StringJoiner;

public class ConsultaResponse {

    private String propostaId;

    private String nome;

    private String documento;

    private StatusConsultaEnum resultadoSolicitacao;

    public ConsultaResponse(String nome,
                            String documento,
                            StatusConsultaEnum resultadoSolicitacao) {
        this.nome = nome;
        this.documento = documento;
        this.resultadoSolicitacao = resultadoSolicitacao;
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

    public StatusConsultaEnum getResultadoSolicitacao() {
        return resultadoSolicitacao;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", ConsultaResponse.class.getSimpleName() + "[", "]")
                .add("propostaId='" + propostaId + "'")
                .add("nome='" + nome + "'")
                .add("documento='" + documento + "'")
                .add("resultadoSolicitacao='" + resultadoSolicitacao + "'")
                .toString();
    }

}
