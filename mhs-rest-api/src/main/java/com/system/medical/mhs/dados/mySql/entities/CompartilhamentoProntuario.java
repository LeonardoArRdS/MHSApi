/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.system.medical.mhs.dados.mySql.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author Felipe
 */
@Entity
@Table(name = "MHS.share_prontuario")
public class CompartilhamentoProntuario implements Serializable {
    @Id
    private Long protocolo;
    
    @Column(name = "ultimo_update")
    private Date ultimoUpdate;
    
    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "id_pessoa")
    private Pessoa pessoa;
    
    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "id_crm")
    private CRM crm;
    
    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "id_status_share")
    private StatusCompartilhamento status;  
    
    @OneToOne(mappedBy = "compartilhamento")
    private CompartilhamentoProntuarioConsulta prontuarioConsulta;
    
    @Column(name = "inicio_vigencia")
    private Date inicioVigencia;
    
    @Column(name = "fim_vigencia")
    private Date fimVigencia;
    

    public CompartilhamentoProntuario() {
    }

    public Long getProtocolo() {
        return protocolo;
    }

    public Date getUltimoUpdate() {
        return ultimoUpdate;
    }

    public void setUltimoUpdate(Date ultimoUpdate) {
        this.ultimoUpdate = ultimoUpdate;
    }
    
    public void setProtocolo(Long protocolo) {
        this.protocolo = protocolo;
    }
    
    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public CRM getCrm() {
        return crm;
    }

    public void setCrm(CRM crm) {
        this.crm = crm;
    }

    public StatusCompartilhamento getStatus() {
        return status;
    }

    public void setStatus(StatusCompartilhamento status) {
        this.status = status;
    }

    public Date getInicioVigencia() {
        return inicioVigencia;
    }

    public void setInicioVigencia(Date inicioVigencia) {
        this.inicioVigencia = inicioVigencia;
    }

    public Date getFimVigencia() {
        return fimVigencia;
    }

    public void setFimVigencia(Date fimVigencia) {
        this.fimVigencia = fimVigencia;
    }

    public CompartilhamentoProntuarioConsulta getProntuarioConsulta() {
        return prontuarioConsulta;
    }

    public void setProntuarioConsulta(CompartilhamentoProntuarioConsulta prontuarioConsulta) {
        this.prontuarioConsulta = prontuarioConsulta;
    }
    
    
    
}
