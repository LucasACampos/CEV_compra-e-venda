package br.com.cev.dto;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.LinkedHashMap;
import java.util.Map;

public class CepDto {

    @JsonProperty("cep")
    private String cep;
    @JsonProperty("logradouro")
    private String logradouro;
    @JsonProperty("complemento")
    private String complemento;
    @JsonProperty("unidade")
    private String unidade;
    @JsonProperty("bairro")
    private String bairro;
    @JsonProperty("localidade")
    private String localidade;
    @JsonProperty("uf")
    private String uf;
    @JsonProperty("estado")
    private String estado;
    @JsonProperty("regiao")
    private String regiao;
    @JsonProperty("ibge")
    private String ibge;
    @JsonProperty("gia")
    private String gia;
    @JsonProperty("ddd")
    private String ddd;
    @JsonProperty("siafi")
    private String siafi;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("cep")
    public String getCep() {
        return cep;
    }

    @JsonProperty("cep")
    public void setCep(String cep) {
        this.cep = cep;
    }

    @JsonProperty("logradouro")
    public String getLogradouro() {
        return logradouro;
    }

    @JsonProperty("logradouro")
    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    @JsonProperty("complemento")
    public String getComplemento() {
        return complemento;
    }

    @JsonProperty("complemento")
    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    @JsonProperty("unidade")
    public String getUnidade() {
        return unidade;
    }

    @JsonProperty("unidade")
    public void setUnidade(String unidade) {
        this.unidade = unidade;
    }

    @JsonProperty("bairro")
    public String getBairro() {
        return bairro;
    }

    @JsonProperty("bairro")
    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    @JsonProperty("localidade")
    public String getLocalidade() {
        return localidade;
    }

    @JsonProperty("localidade")
    public void setLocalidade(String localidade) {
        this.localidade = localidade;
    }

    @JsonProperty("uf")
    public String getUf() {
        return uf;
    }

    @JsonProperty("uf")
    public void setUf(String uf) {
        this.uf = uf;
    }

    @JsonProperty("estado")
    public String getEstado() {
        return estado;
    }

    @JsonProperty("estado")
    public void setEstado(String estado) {
        this.estado = estado;
    }

    @JsonProperty("regiao")
    public String getRegiao() {
        return regiao;
    }

    @JsonProperty("regiao")
    public void setRegiao(String regiao) {
        this.regiao = regiao;
    }

    @JsonProperty("ibge")
    public String getIbge() {
        return ibge;
    }

    @JsonProperty("ibge")
    public void setIbge(String ibge) {
        this.ibge = ibge;
    }

    @JsonProperty("gia")
    public String getGia() {
        return gia;
    }

    @JsonProperty("gia")
    public void setGia(String gia) {
        this.gia = gia;
    }

    @JsonProperty("ddd")
    public String getDdd() {
        return ddd;
    }

    @JsonProperty("ddd")
    public void setDdd(String ddd) {
        this.ddd = ddd;
    }

    @JsonProperty("siafi")
    public String getSiafi() {
        return siafi;
    }

    @JsonProperty("siafi")
    public void setSiafi(String siafi) {
        this.siafi = siafi;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}