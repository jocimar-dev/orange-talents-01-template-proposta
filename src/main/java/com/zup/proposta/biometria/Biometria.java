package com.zup.proposta.biometria;

import com.zup.proposta.cartao.Cartao;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "biometrias")
public class Biometria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @ManyToOne(cascade = CascadeType.REFRESH, targetEntity = Cartao.class)
    @JoinColumn(name = "cartao_id")
    private Cartao cartao;

    @NotBlank
    private String biometria;

    public Biometria(@NotNull Cartao cartao,
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
