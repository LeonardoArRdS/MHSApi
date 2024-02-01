/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.system.medical.mhs.dados.mySql.entities;

import java.io.Serializable;
import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author Felipe
 */
@Table(name = "MHS.dependencia")
@Entity
@AssociationOverrides({
    @AssociationOverride(name = "id.responsavel", joinColumns = @JoinColumn(name = "id_responsavel"))
    ,
    @AssociationOverride(name = "id.dependente", joinColumns = @JoinColumn(name = "id_dependente"))
})
public class Dependencia implements Serializable {

    @EmbeddedId
    private IdDependencia id;

    @ManyToOne
    @JoinColumn(name = "id_parentesco")
    private Parentesco parentesco;

    public Dependencia() {
    }

    public IdDependencia getId() {
        return id;
    }

    public void setId(IdDependencia id) {
        this.id = id;
    }

    public Parentesco getParentesco() {
        return parentesco;
    }

    public void setParentesco(Parentesco parentesco) {
        this.parentesco = parentesco;
    }
    
    public Pessoa getDependente(){
        return this.id.getDependente();
    }
}

@Embeddable
class IdDependencia implements Serializable {

    @ManyToOne
    private Pessoa responsavel;

    @ManyToOne
    private Pessoa dependente;

    public IdDependencia() {
    }

    public Pessoa getResponsavel() {
        return responsavel;
    }

    public void setResponsavel(Pessoa responsavel) {
        this.responsavel = responsavel;
    }

    public Pessoa getDependente() {
        return dependente;
    }

    public void setDependente(Pessoa dependente) {
        this.dependente = dependente;
    }

}
