/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.system.medical.mhs.dados.mySql.entities;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Felipe
 */
@Entity
@Table(name = "MHS.clinica_especialidade")
public class ClinicaEspecialidade implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    
    @ManyToOne
    @JoinColumn(name = "id_clinica")
    private Clinica clinica;
    
    @ManyToOne
    @JoinColumn(name = "id_especialidade")
    private Especialidade especialidade;
    
    @OneToMany(mappedBy = "id.clinicaEspecialidade")
    private Set<ClinicaEspecialidadePreco> precos;

    public ClinicaEspecialidade() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Clinica getClinica() {
        return clinica;
    }

    public void setClinica(Clinica clinica) {
        this.clinica = clinica;
    }

    public Especialidade getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(Especialidade especialidade) {
        this.especialidade = especialidade;
    }

    public Set<ClinicaEspecialidadePreco> getPrecos() {
        return precos;
    }

    public void setPrecos(Set<ClinicaEspecialidadePreco> precos) {
        this.precos = precos;
    }
    
    
}
