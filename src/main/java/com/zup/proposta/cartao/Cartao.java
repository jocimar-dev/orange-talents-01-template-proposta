package com.zup.proposta.cartao;


import com.zup.proposta.proposta.Proposta;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "cartoes")
public class Cartao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String numero;

    @NotBlank
    private String titular;

    private LocalDateTime criadoEm;

    private BigDecimal limite;

    @OneToOne
    private Proposta proposta;

    @OneToMany(mappedBy = "cartao", cascade = CascadeType.PERSIST)
    private final List<CartaoBiometria> biometrias = new ArrayList<>();

    @Deprecated
    public Cartao() {
    }

    public Cartao(@NotBlank String numero,
                  @NotBlank String titular,
                  LocalDateTime criadoEm,
                  BigDecimal limite,
                  Proposta proposta) {
        this.numero = numero;
        this.titular = titular;
        this.criadoEm = criadoEm;
        this.limite = limite;
        this.proposta = proposta;
    }

    public Long getId() {
        return id;
    }
}
