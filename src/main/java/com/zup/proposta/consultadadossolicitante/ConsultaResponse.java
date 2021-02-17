package com.zup.proposta.consultadadossolicitante;

import java.util.StringJoiner;

public class ConsultaResponse {

    private String propostaId;

    private String nome;

    private String documento;

    private String resultado;

    public ConsultaResponse(String propostaId,
                            String nome,
                            String documento,
                            String resultado) {
        this.propostaId = propostaId;
        this.nome = nome;
        this.documento = documento;
        this.resultado = resultado;
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

    public String getPropostaId() {
        return propostaId;
    }

    public String getNome() {
        return nome;
    }

    public String getDocumento() {
        return documento;
    }

    public String getResultado() {
        return resultado;
    }
}
