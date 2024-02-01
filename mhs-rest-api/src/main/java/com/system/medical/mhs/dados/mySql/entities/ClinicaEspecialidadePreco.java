/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.system.medical.mhs.dados.mySql.entities;

import java.io.Serializable;
import java.text.DecimalFormat;
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
@Entity
@Table(name = "MHS.clinica_especialidade_Preco")
@AssociationOverrides({
    @AssociationOverride(name = "id.moeda", joinColumns = @JoinColumn(name = "id_moeda"))
    ,
    @AssociationOverride(name = "id.clinicaEspecialidade", joinColumns = @JoinColumn(name = "id_clinica_especialidade"))
})
public class ClinicaEspecialidadePreco implements Serializable{
    
    @EmbeddedId
    private IdClinicaEspecialidadePreco id;

    @Column(name = "valor")
    private Double valor;

    
    public ClinicaEspecialidadePreco() {
    }

    @Override
    public String toString() {
        return this.getMoeda().getSimbolo() + " " + String.format("%.2f", this.valor);
    }

    public IdClinicaEspecialidadePreco getId() {
        return id;
    }

    public void setId(IdClinicaEspecialidadePreco id) {
        this.id = id;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public ClinicaEspecialidade getClinicaEspecialidade() {
        return this.getId().getClinicaEspecialidade();
    }

    public Moeda getMoeda() {
        return this.getId().getMoeda();
    }
}

@Embeddable
class IdClinicaEspecialidadePreco implements Serializable {

    @ManyToOne
    private ClinicaEspecialidade clinicaEspecialidade;

    @ManyToOne
    private Moeda moeda;

    public IdClinicaEspecialidadePreco() {
    }

    public ClinicaEspecialidade getClinicaEspecialidade() {
        return clinicaEspecialidade;
    }

    public void setClinicaEspecialidade(ClinicaEspecialidade clinicaEspecialidade) {
        this.clinicaEspecialidade = clinicaEspecialidade;
    }

    public Moeda getMoeda() {
        return moeda;
    }

    public void setMoeda(Moeda moeda) {
        this.moeda = moeda;
    }

}
