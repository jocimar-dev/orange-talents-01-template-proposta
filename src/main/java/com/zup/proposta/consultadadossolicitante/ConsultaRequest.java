package com.zup.proposta.consultadadossolicitante;

import com.zup.proposta.proposta.Proposta;

public class ConsultaRequest {
    private Long propostaId;

    private String nome;

    private String documento;


    public ConsultaRequest(Proposta proposta) {
        this.propostaId = proposta.getId();
        this.nome = proposta.getNome();
        this.documento = proposta.getDocumento();
    }

    public Long getPropostaId() {
        return propostaId;
    }

    public String getNome() {
        return nome;
    }

    public String getDocumento() {
        return documento;
    }


}
