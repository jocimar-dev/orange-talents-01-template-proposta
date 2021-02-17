package com.zup.proposta.consultadadossolicitante;

public class ConsultaRequest {
    private String propostaId;

    private String nome;

    private String documento;

    public ConsultaRequest(String propostaId,
                           String nome,
                           String documento) {
        this.propostaId = propostaId;
        this.nome = nome;
        this.documento = documento;
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
}
