package com.zup.proposta.proposta.endereco;

import javax.validation.constraints.NotBlank;

public class EnderecoRequest {


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

    @NotBlank
    private String cidade;

    @NotBlank
    private String uf;

    public EnderecoRequest(@NotBlank String cep,
                           @NotBlank String logradouro,
                           @NotBlank String numero,
                           @NotBlank String bairro,
                           @NotBlank String complemento,
                           @NotBlank String municipio,
                           @NotBlank String cidade,
                           @NotBlank String uf) {
        this.cep = cep;
        this.logradouro = logradouro;
        this.numero = numero;
        this.bairro = bairro;
        this.complemento = complemento;
        this.municipio = municipio;
        this.cidade = cidade;
        this.uf = uf;
    }



    public Endereco toModel() {
        return new Endereco(cep, logradouro, numero, bairro, complemento, municipio, cidade, uf);
    }

}
