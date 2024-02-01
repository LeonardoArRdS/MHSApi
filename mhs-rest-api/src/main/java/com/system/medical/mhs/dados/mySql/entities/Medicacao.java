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
@Table(name = "MHS.medicacao")
@Entity
//@AssociationOverrides({
//    @AssociationOverride(name = "id.pessoa", joinColumns = @JoinColumn(name = "id_pessoa")),
//    @AssociationOverride(name = "id.medicamento", joinColumns = @JoinColumn(name = "id_medicamento"))
//})
public class Medicacao implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    
    @ManyToOne
    @JoinColumn(name = "id_pessoa")
    private Pessoa pessoa;
    
    @ManyToOne
    @JoinColumn(name = "id_medicamento")
    private Medicamento medicamento;
    
    @Column(name = "dose")
    private Double dose;
    
    @ManyToOne
    @JoinColumn(name = "id_unidade_medida")
    private UnidadeMedida unidadeMedida;
    
    @ManyToOne
    @JoinColumn(name = "id_frequencia")
    private Frequencia frequencia;

    public Medicacao() {
    }
    
    public Double getDose() {
        return dose;
    }

    public void setDose(Double dose) {
        this.dose = dose;
    }

    public Frequencia getFrequencia() {
        return frequencia;
    }

    public void setFrequencia(Frequencia frequencia) {
        this.frequencia = frequencia;
    }
    
    public UnidadeMedida getUnidadeMedida() {
        return unidadeMedida;
    }

    public void setUnidadeMedida(UnidadeMedida unidadeMedida) {
        this.unidadeMedida = unidadeMedida;
    }

    public String getDoseCompleta(){
        return this.dose + " " + this.getUnidadeMedida().getSimbolo();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public Medicamento getMedicamento() {
        return medicamento;
    }

    public void setMedicamento(Medicamento medicamento) {
        this.medicamento = medicamento;
    }
    
}

//@Embeddable
//class IdMedicacao implements Serializable{
//    @ManyToOne
//    private Pessoa pessoa;
//    
//    @ManyToOne
//    private Medicamento medicamento;
//
//    public IdMedicacao() {
//    }
//
//    public Pessoa getPessoa() {
//        return pessoa;
//    }
//
//    public void setPessoa(Pessoa pessoa) {
//        this.pessoa = pessoa;
//    }
//
//    public Medicamento getMedicamento() {
//        return medicamento;
//    }
//
//    public void setMedicamento(Medicamento medicamento) {
//        this.medicamento = medicamento;
//    }
//    
//  
//}
