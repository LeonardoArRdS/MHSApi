/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.system.medical.mhs.dados.mySql.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

/**
 *
 * @author Felipe
 */

@Embeddable
public class IdProtocoloConsulta implements Serializable {
    @ManyToOne(fetch = FetchType.LAZY)
    private CRM crm;
    
    @ManyToOne(fetch = FetchType.LAZY)
    private Pessoa pessoa;
    
    @ManyToOne
    private Clinica clinica;
    
    @Column(name = "data_hora")
    private Date dataHora;

    public IdProtocoloConsulta() {
    }

    public IdProtocoloConsulta(CRM crm, Pessoa pessoa, Clinica clinica, Date dataHora) {
        this.crm = crm;
        this.pessoa = pessoa;
        this.clinica = clinica;
        this.dataHora = dataHora;
    }
    
    public CRM getCrm() {
        return crm;
    }

    public void setCrm(CRM crm) {
        this.crm = crm;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public Date getDataHora() {
        return dataHora;
    }

    public void setDataHora(Date dataHora) {
        this.dataHora = dataHora;
    }

    public Clinica getClinica() {
        return clinica;
    }

    public void setClinica(Clinica clinica) {
        this.clinica = clinica;
    }
    
}