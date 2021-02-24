package com.zup.proposta.proposta;

import com.zup.proposta.cartao.CartaoClient;
import com.zup.proposta.cartao.CartaoResponse;
import com.zup.proposta.consulta.StatusConsultaEnum;
import feign.FeignException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.List;

@Component
public class ConsultaCartao {

    private static final Logger log = LoggerFactory.getLogger(ConsultaCartao.class);

    private final PropostaRepository repository;

    private final CartaoClient cartaoClient;

    @Autowired
    public ConsultaCartao(PropostaRepository repository, CartaoClient cartaoClient) {
        this.repository = repository;
        this.cartaoClient = cartaoClient;
    }

    @Scheduled(fixedRateString = "15000")
    public void consultaCartaoDasPropostasElegiveis() {
        List<Proposta> propostas = repository.findAllElegiveisSemCartao(StatusConsultaEnum.COM_RESTRICAO);
        for(Proposta proposta : propostas) {
            try {
                CartaoResponse response = cartaoClient.consulta(proposta.getId());
                proposta.associaCartao(response);
                repository.save(proposta);
            }catch (FeignException ex){
                log.info("Não encontrou um cartão para a proposta: "+proposta.getId());
            }
        }
    }

}
