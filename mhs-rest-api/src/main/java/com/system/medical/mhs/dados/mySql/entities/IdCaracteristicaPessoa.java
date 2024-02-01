/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.system.medical.mhs.dados.mySql.entities;

import java.io.Serializable;
import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

/**
 *
 * @author Felipe
 */

@Embeddable
public class IdCaracteristicaPessoa implements Serializable {
    @ManyToOne
    private CaracteristicaFisica caracteristica;
    
    @ManyToOne
    private Pessoa pessoa;

    public IdCaracteristicaPessoa() {
    }

    public IdCaracteristicaPessoa(Pessoa pessoa, CaracteristicaFisica caracteristica) {
        this.pessoa = pessoa;
        this.caracteristica = caracteristica;
    }

    public CaracteristicaFisica getCaracteristica() {
        return caracteristica;
    }

    public void setCaracteristica(CaracteristicaFisica caracteristica) {
        this.caracteristica = caracteristica;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }
    
    
}
