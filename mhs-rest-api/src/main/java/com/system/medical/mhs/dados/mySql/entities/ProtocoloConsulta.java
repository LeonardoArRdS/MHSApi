/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.system.medical.mhs.dados.mySql.entities;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author Felipe
 */
@Table(name = "MHS.protocolo_consulta")
@Entity
@AssociationOverrides({
    @AssociationOverride(name = "id.pessoa", joinColumns = @JoinColumn(name = "id_pessoa")),
    @AssociationOverride(name = "id.crm", joinColumns = @JoinColumn(name = "id_crm")),
    @AssociationOverride(name = "id.clinica", joinColumns = @JoinColumn(name = "id_clinica"))
})
public class ProtocoloConsulta implements Serializable{
    @EmbeddedId
    private IdProtocoloConsulta id;
   
    @OneToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "protocolo_consulta")
    private Consulta consulta;

    public ProtocoloConsulta() {
        this.id = new IdProtocoloConsulta();
    }

    public ProtocoloConsulta(IdProtocoloConsulta id, Consulta consulta) {
        this.id = id;
        this.consulta = consulta;
    }
    
    public ProtocoloConsulta(CRM crm, Pessoa pessoa, Clinica clinica, Date dataHora) {
        this.id = new IdProtocoloConsulta(crm, pessoa, clinica, dataHora);
    }

    public IdProtocoloConsulta getId() {
        return id;
    }

    public void setId(IdProtocoloConsulta id) {
        this.id = id;
    }

    public Consulta getConsulta() {
        return consulta;
    }

    public void setConsulta(Consulta consulta) {
        this.consulta = consulta;
    }
    
    public CRM getCRM(){
        return this.getId().getCrm();
    }
    
    public Pessoa getPessoa(){
        return this.getId().getPessoa();
    }
    
    public String getHora(){
        return new SimpleDateFormat("HH:mm").format(this.getId().getDataHora());
    }
    
    public void setData(Date data){
        this.getId().setDataHora(data);
    }
    
    public String getData(){
        return new SimpleDateFormat("dd/MM/yyyy").format(this.getId().getDataHora());
    }
    
    public Date getDataCompleta(){
        return this.getId().getDataHora();
    }

    public Clinica getClinica() {
        return this.getId().getClinica();
    }
    
    public void setCrm(CRM crm){
        this.id.setCrm(crm);
    }
    
    public void setPessoa(Pessoa pessoa){
        this.id.setPessoa(pessoa);
    }
    
    public void setClinica(Clinica clinica){
        this.id.setClinica(clinica);
    }
    public void seeData(){
    	System.out.println("");
    }
}