package com.zup.proposta.cliente;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;
import java.util.stream.Stream;

public class ServletRequestClient {

        private final HttpServletRequest request;

    public ServletRequestClient(HttpServletRequest request) {
        this.request = request;
    }


    public String atualiza() {
        String xRealIp = request.getHeader("X-Real-IP");
        String xForwardedFor = request.getHeader("X-Forwarded-For");
        String remoteAddr = request.getRemoteAddr();

        return Stream.of(xRealIp, xForwardedFor, remoteAddr)
                .filter(Objects::nonNull)
                .findFirst().orElse(null);
    }

}
