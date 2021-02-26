package com.zup.proposta.consulta;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "consulta", url = "${solicitacao.analise.proposta}")
public interface ConsultaMap {

    @PostMapping("/api/solicitacao")
    ConsultaResponse cadastraSolicitacao(@RequestBody ConsultaRequest request);

    @GetMapping("/api/solicitacao/{id}")
    ConsultaResponse consultaSolicitacao(@RequestParam("idProposta") Long idProposta);


}
