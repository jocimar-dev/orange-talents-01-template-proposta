package com.zup.proposta.proposta;

import javax.validation.constraints.NotBlank;

public class EnderecoRequest {
    private Long id;

    @NotBlank
    private String cep;

    @NotBlank
    private String logradouro;

    @NotBlank
    private String numero;

    @NotBlank
    private String bairro;

    @NotBlank
    private String complemento;

    @NotBlank
    private String municipio;

    public EnderecoRequest(@NotBlank String cep,
                           @NotBlank String logradouro,
                           @NotBlank String numero,
                           @NotBlank String bairro,
                           @NotBlank String complemento,
                           @NotBlank String municipio) {
        super();
        this.cep = cep;
        this.logradouro = logradouro;
        this.numero = numero;
        this.bairro = bairro;
        this.complemento = complemento;
        this.municipio = municipio;
    }

    public Endereco toModel() {
        return new Endereco(cep, logradouro, numero, bairro, complemento, municipio);
    }

    public Long getId() {
        return id;
    }
}
