package com.zup.proposta.cartao;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "biometrias")
public class CartaoBiometria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @ManyToOne(cascade = CascadeType.REFRESH, targetEntity = Cartao.class)
    @JoinColumn(name = "cartao_id")
    private Cartao cartao;

    @NotBlank
    private String biometria;

    public CartaoBiometria(@NotNull Cartao cartao,
                           @NotBlank String biometria) {
        this.cartao = cartao;
        this.biometria = biometria;
    }

    public Long getId() {
        return id;
    }

    public Cartao getCartao() {
        return cartao;
    }

    public String getBiometria() {
        return biometria;
    }
}
