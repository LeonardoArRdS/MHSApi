/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.system.medical.mhs.dados.mySql.entities;

import java.io.Serializable;
import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;
import javax.persistence.Column;
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

@Table(name = "MHS.caracteristica_fisica_pessoa")
@Entity
@AssociationOverrides({
    @AssociationOverride(name = "id.pessoa", joinColumns = @JoinColumn(name = "id_pessoa")),
    @AssociationOverride(name = "id.caracteristica", joinColumns = @JoinColumn(name = "id_caracteristica"))
})
public class CaracteristicaFisicaPessoa implements Serializable{
    @EmbeddedId
    private IdCaracteristicaPessoa id;
    
    @Column(name = "valor")
    private Double valor;
    
    
    public CaracteristicaFisicaPessoa() { 
    }
    
    public CaracteristicaFisicaPessoa(Pessoa pessoa, CaracteristicaFisica caracteristica){
        this.id = new IdCaracteristicaPessoa(pessoa, caracteristica);
    }
    
    public CaracteristicaFisica getCaracteristica(){
        return this.id.getCaracteristica();
    }

    public IdCaracteristicaPessoa getId() {
        return id;
    }

    public void setId(IdCaracteristicaPessoa id) {
        this.id = id;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    @Override
    public String toString() {
        return valor + " " + getCaracteristica().getUnidadeMedida().getSimbolo();
    }
    
    
    
    
}
