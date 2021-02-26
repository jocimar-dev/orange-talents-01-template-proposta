package com.zup.proposta.proposta;

import com.zup.proposta.cartao.Cartao;
import com.zup.proposta.cartao.CartaoClient;
import com.zup.proposta.cartao.CartaoResponse;
import com.zup.proposta.enums.StatusConsultaEnum;
import feign.FeignException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class ConsultaCartaoScheduler {

    private static final Logger log = LoggerFactory.getLogger(ConsultaCartaoScheduler.class);

    private final PropostaRepository repository;

    private final CartaoClient cartaoClient;

    @Autowired
    public ConsultaCartaoScheduler(PropostaRepository repository, CartaoClient cartaoClient) {
        this.repository = repository;
        this.cartaoClient = cartaoClient;
    }

    @Scheduled(fixedRateString = "${tempo.resposta.consulta}")
    @Transactional
    public void consultaCartaoDasPropostasElegiveis() {
        List<Proposta> propostas = repository.findAllElegiveisSemCartao(StatusConsultaEnum.COM_RESTRICAO);
        for(Proposta proposta : propostas) {
            try {
                CartaoResponse response = cartaoClient.consulta(proposta.getId());
                Cartao cartao = new Cartao(response.getId(), response.getTitular(), response.getEmitidoEm(), response.getLimite(), proposta);
                proposta.associaCartao(cartao);
                repository.save(proposta);
            }catch (FeignException.InternalServerError e){
                log.info("Não foi encontrado um cartão para a proposta: "+proposta.getId());
            }
        }
    }

}
