package com.zup.proposta.consulta;

import com.zup.proposta.proposta.PropostaStatusEnum;

public enum StatusConsultaEnum {
    COM_RESTRICAO(PropostaStatusEnum.NAO_ELEGIVEL),
    SEM_RESTRICAO(PropostaStatusEnum.ELEGIVEL);

    private PropostaStatusEnum propostaStatus;

    StatusConsultaEnum(PropostaStatusEnum propostaStatus) {
        this.propostaStatus = propostaStatus;
    }

    public PropostaStatusEnum getPropostaStatus() {
        return propostaStatus;
    }

}
