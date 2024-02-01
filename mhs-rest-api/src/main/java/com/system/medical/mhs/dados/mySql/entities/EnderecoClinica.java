/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.system.medical.mhs.dados.mySql.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author Felipe
 */
@Entity
@Table(name = "MHS.endereco_clinica")
public class EnderecoClinica extends Endereco implements Serializable {

    @OneToOne
    @JoinColumn(name = "id_clinica")
    private Clinica clinica;

    public Clinica getClinica() {
        return clinica;
    }

    public void setClinica(Clinica clinica) {
        this.clinica = clinica;
    }

    @Override
    public String toString() {
        return this.getRua() + ", nº " + this.getNumero()
                + ", " + this.getBairro() + " - CEP: " + this.getCEP();
    }

}
