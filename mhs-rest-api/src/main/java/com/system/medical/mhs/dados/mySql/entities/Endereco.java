/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.system.medical.mhs.dados.mySql.entities;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Table;

/**
 *
 * @author Felipe
 */
@MappedSuperclass
public abstract class Endereco {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    
    @Column(name = "cep_faixa")
    private Integer faixaCep;
    
    @Column(name = "cep_dig")
    private Integer digitosCep;
    
    @Column(name = "rua")
    private String rua;
    
    @Column(name = "numero")
    private Integer numero;
    
    @Column(name = "bairro")
    private String bairro;
    
    @Column(name = "complemento")
    private String complemento;
    
    @Column(name = "latitude")
    private Double latitude;
    
    @Column(name = "longitude")
    private Double longitude;

    public Endereco() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCEP() {
        return this.faixaCep + "-" + this.digitosCep;
    }
    
    public String getRua() {
        return rua;
    }

    public Integer getFaixaCep() {
        return faixaCep;
    }

    public void setFaixaCep(Integer faixaCep) {
        this.faixaCep = faixaCep;
    }

    public Integer getDigitosCep() {
        return digitosCep;
    }

    public void setDigitosCep(Integer digitosCep) {
        this.digitosCep = digitosCep;
    }
    
    public void setRua(String rua) {
        this.rua = rua;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    @Override
    public String toString() {
        return this.rua + " - nº " + this.numero + " - " + this.bairro + " - CEP: " + this.getCEP();
    }
    
}
