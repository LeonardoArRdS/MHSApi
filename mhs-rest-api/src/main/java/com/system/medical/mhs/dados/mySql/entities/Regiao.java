/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.system.medical.mhs.dados.mySql.entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author Felipe
 */
@Entity
@Table(name = "MHS.regiao")
public class Regiao implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "id_zona")
    private ZonaRegiao zona;
    
    @Column(name = "cep_inicio")
    private Integer inicioFaixaCep;
    
    @Column(name = "cep_fim")
    private Integer fimFaixaCep;

    public Regiao() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ZonaRegiao getZona() {
        return zona;
    }

    public void setZona(ZonaRegiao zona) {
        this.zona = zona;
    }

    public Integer getInicioFaixaCep() {
        return inicioFaixaCep;
    }

    public void setInicioFaixaCep(Integer inicioFaixaCep) {
        this.inicioFaixaCep = inicioFaixaCep;
    }

    public Integer getFimFaixaCep() {
        return fimFaixaCep;
    }

    public void setFimFaixaCep(Integer fimFaixaCep) {
        this.fimFaixaCep = fimFaixaCep;
    }
    
    
}
