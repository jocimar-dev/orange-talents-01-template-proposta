package com.zup.proposta.consultadadossolicitante;

import com.zup.proposta.proposta.PropostaStatusEnum;

public enum StatusConsultaEnum {
    COM_RESTRICAO(PropostaStatusEnum.NAO_ELEGIVEL),
    SEM_RESTRICAO(PropostaStatusEnum.ELEGIVEL);

    private PropostaStatusEnum propostaStatus;

    private StatusConsultaEnum(PropostaStatusEnum propostaStatus) {
        this.propostaStatus = propostaStatus;
    }

    public PropostaStatusEnum getPropostaStatus() {
        return propostaStatus;
    }

}
