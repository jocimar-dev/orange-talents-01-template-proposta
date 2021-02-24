package com.zup.proposta.proposta.endereco;

import javax.persistence.Embeddable;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import java.util.StringJoiner;

@Embeddable
@Table(name = "endereco")
public class Endereco {

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

    public Endereco(@NotBlank String cep,
                    @NotBlank String logradouro,
                    @NotBlank String numero,
                    @NotBlank String bairro,
                    @NotBlank String complemento,
                    @NotBlank String municipio,
                    @NotBlank String cidade,
                    @NotBlank String uf
    ) {
        this.cep = cep;
        this.logradouro = logradouro;
        this.numero = numero;
        this.bairro = bairro;
        this.complemento = complemento;
        this.municipio = municipio;
        this.cidade = cidade;
        this.uf = uf;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Endereco.class.getSimpleName() + "[", "]")
                .add("cep='" + cep + "'")
                .add("logradouro='" + logradouro + "'")
                .add("numero='" + numero + "'")
                .add("bairro='" + bairro + "'")
                .add("complemento='" + complemento + "'")
                .add("municipio='" + municipio + "'")
                .add("cidade='" + cidade + "'")
                .add("uf='" + uf + "'")
                .toString();
    }

    public String getCep() {
        return cep;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public String getNumero() {
        return numero;
    }

    public String getBairro() {
        return bairro;
    }

    public String getComplemento() {
        return complemento;
    }

    public String getMunicipio() {
        return municipio;
    }

    public String getCidade() {
        return cidade;
    }

    public String getUf() {
        return uf;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    @Deprecated
    public Endereco() {

    }
}
