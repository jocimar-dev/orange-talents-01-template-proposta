package com.zup.proposta.bloqueios;

public class BloqueioRequest {

    private String processoBloqueio;

    public BloqueioRequest(String processoBloqueio) {
        this.processoBloqueio = processoBloqueio;
    }

    public String getProcessoBloqueio() {
        return processoBloqueio;
    }
}
