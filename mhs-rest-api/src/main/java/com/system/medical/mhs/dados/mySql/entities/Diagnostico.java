/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.system.medical.mhs.dados.mySql.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author Felipe
 */
@Entity
@Table(name = "MHS.diagnostico_consulta")
public class Diagnostico implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @OneToOne
    @JoinColumn(name = "protocolo_consulta")
    private Consulta consulta;
    
    @OneToMany(mappedBy = "diagnostico")
    private Set<DiagnosticoExame> exames = new HashSet<>();
    
    @ManyToMany
    @JoinTable(name = "diagnostico_medicacao", joinColumns = @JoinColumn(name = "id_diagnostico"), inverseJoinColumns = @JoinColumn(name = "id_medicacao"))
    private Set<Medicacao> medicacoes = new HashSet<>();

    @ManyToMany
    @JoinTable(name = "diagnostico_tratamento_pessoa", joinColumns = @JoinColumn(name = "id_diagnostico"), inverseJoinColumns = @JoinColumn(name = "id_tratamento_pessoa"))
    private Set<TratamentoPessoa> tratamentos = new HashSet<>();
        
    
    public Diagnostico() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Consulta getConsulta() {
        return consulta;
    }

    public void setConsulta(Consulta consulta) {
        this.consulta = consulta;
    }

    public Set<DiagnosticoExame> getExames() {
        return exames;
    }

    public void setExames(Set<DiagnosticoExame> exames) {
        this.exames = exames;
    }

    public Set<Medicacao> getMedicacoes() {
        return medicacoes;
    }

    public void setMedicacoes(Set<Medicacao> medicacoes) {
        this.medicacoes = medicacoes;
    }

    public Set<TratamentoPessoa> getTratamentos() {
        return tratamentos;
    }

    public void setTratamentos(Set<TratamentoPessoa> tratamentos) {
        this.tratamentos = tratamentos;
    }
    
}
