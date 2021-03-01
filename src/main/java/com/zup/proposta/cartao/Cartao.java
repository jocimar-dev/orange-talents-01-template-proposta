package com.zup.proposta.cartao;


import com.zup.proposta.aviso.Aviso;
import com.zup.proposta.aviso.NovoAvisoRequest;
import com.zup.proposta.enums.StatusConsultaEnum;
import com.zup.proposta.proposta.Proposta;
import com.zup.proposta.service.RequestHeadersService;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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
import java.util.Map;

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

    private LocalDateTime emitidoEm;

    private BigDecimal limite;

    @OneToOne
    private Proposta proposta;

    @Enumerated(EnumType.STRING)
    private StatusConsultaEnum status = StatusConsultaEnum.SEM_RESTRICAO;


    @OneToMany(mappedBy = "cartao", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<Aviso> avisos = new ArrayList<>();


    public Cartao(String numero,
                  String titular,
                  LocalDateTime emitidoEm,
                  BigDecimal limite,
                  Proposta proposta) {
        this.numero = numero;
        this.titular = titular;
        this.emitidoEm = emitidoEm;
        this.limite = limite;
        this.proposta = proposta;
        this.status = status;
    }

    public Cartao() {

    }

    public Long getId() {
        return id;
    }

    public String getNumero() {
        return numero;
    }

    public String getTitular() {
        return titular;
    }

    public LocalDateTime getEmitidoEm() {
        return emitidoEm;
    }

    public BigDecimal getLimite() {
        return limite;
    }

    public Proposta getProposta() {
        return proposta;
    }

    public StatusConsultaEnum getStatus() {
        return status;
    }


    public boolean cartaoBloqueado() {
        return this.status == StatusConsultaEnum.COM_RESTRICAO;
    }

    public void associarAviso(NovoAvisoRequest request, Map<String, String> requestHeaders) {
        avisos.add(new Aviso(this, request.getDestino(), request.getValidoAte(),
                requestHeaders.get(RequestHeadersService.IP), requestHeaders.get(RequestHeadersService.USER_AGENT)));
    }


}
