package com.zup.proposta.bloqueios;

import com.zup.proposta.cartao.Cartao;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Table(name = "bloqueioCartao")
public class BloqueioCartao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @ManyToOne(cascade = CascadeType.PERSIST)
    private Cartao cartaoId;

    @CreationTimestamp
    private LocalDateTime instanteBloqueio;

    @NotBlank
    private String clienteIp;

    @NotBlank
    private String userAgent;

    public BloqueioCartao(@NotNull Cartao cartaoId,
                          LocalDateTime instanteBloqueio,
                          @NotBlank String clienteIp,
                          @NotBlank String userAgent) {
        this.cartaoId = cartaoId;
        this.instanteBloqueio = instanteBloqueio;
        this.clienteIp = clienteIp;
        this.userAgent = userAgent;
    }

    public BloqueioCartao(String clienteIp, String userAgent, Cartao cartao) {
    }

    public Long getId() {
        return id;
    }

    public Cartao getCartaoId() {
        return cartaoId;
    }

    public LocalDateTime getInstanteBloqueio() {
        return instanteBloqueio;
    }

    public String getClienteIp() {
        return clienteIp;
    }

    public String getUserAgent() {
        return userAgent;
    }

}
