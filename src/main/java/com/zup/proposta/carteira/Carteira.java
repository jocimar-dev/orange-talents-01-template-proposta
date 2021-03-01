package com.zup.proposta.carteira;

import com.zup.proposta.cartao.Cartao;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="carteiras")
public class Carteira {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @ManyToOne
    private Cartao cartao;

    @Email
    @NotBlank
    private String email;

    @NotNull
    @Enumerated(EnumType.STRING)
    private CarteiraTipoenum carteiraTipoenum;

    public Carteira(@NotNull Cartao cartao,
                    @Email @NotBlank String email,
                    @NotNull CarteiraTipoenum carteiraTipoenum) {
        this.cartao = cartao;
        this.email = email;
        this.carteiraTipoenum = carteiraTipoenum;
    }

    public Long getId() {
        return id;
    }

    @Deprecated
    public Carteira() {

    }


}