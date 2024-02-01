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
import javax.persistence.EmbeddedId;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

/**
 *
 * @author Felipe
 */
public class ActionMedicacao {
    @EmbeddedId
    private IdActionMedicacao id;
    
    @Column(name = "data_hora_atualizacao")
    private Date atualizacao;

    public ActionMedicacao() {
    }

    public IdActionMedicacao getId() {
        return id;
    }

    public void setId(IdActionMedicacao id) {
        this.id = id;
    }

    public Date getAtualizacao() {
        return atualizacao;
    }

    public void setAtualizacao(Date atualizacao) {
        this.atualizacao = atualizacao;
    }
    
    public Medicacao getMedicacao(){
        return this.id.getMedicacao();
    }
    
    public StatusMedicacao getStatus(){
        return this.id.getStatus();
    }    
}

@Embeddable
class IdActionMedicacao implements Serializable {
    @ManyToOne(fetch = FetchType.LAZY)
    private Medicacao medicacao;
    
    @ManyToOne(fetch = FetchType.LAZY)
    private StatusMedicacao status;

    public IdActionMedicacao() {
    }

    public Medicacao getMedicacao() {
        return medicacao;
    }

    public void setMedicacao(Medicacao medicacao) {
        this.medicacao = medicacao;
    }

    public StatusMedicacao getStatus() {
        return status;
    }

    public void setStatus(StatusMedicacao status) {
        this.status = status;
    }    
}