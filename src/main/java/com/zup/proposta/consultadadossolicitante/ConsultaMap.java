package com.zup.proposta.consultadadossolicitante;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(value = "consulta", url = "http://localhost/9999")
public interface ConsultaMap {

    @PostMapping("/api/consultas")
    ConsultaResponse consultas(@RequestBody ConsultaRequest request);

}
