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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Felipe
 */
@Entity
@Table(name = "MHS.contratacao")
public class Contratacao implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    
    @ManyToOne
    @JoinColumn(name = "id_clinica")
    private Clinica clinica;
    
    @ManyToOne
    @JoinColumn(name = "id_pessoa")
    private Pessoa pessoa;
    
    @ManyToOne
    @JoinColumn(name = "id_profissao")
    private Profissao profissao;
    
    @OneToMany(mappedBy = "contratacao")
    //@JoinTable(name = "Contratacao_Especialidade", joinColumns = @JoinColumn(name = "id_contratacao"), inverseJoinColumns = @JoinColumn(name = "@id_especialidade"))
    private Set<ContratacaoEspecialidade> especialidades = new HashSet<>();

    public Contratacao() {
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

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public Profissao getProfissao() {
        return profissao;
    }

    public void setProfissao(Profissao profissao) {
        this.profissao = profissao;
    }

    public Set<ContratacaoEspecialidade> getEspecialidades() {
        return especialidades;
    }

    public void setEspecialidades(Set<ContratacaoEspecialidade> especialidades) {
        this.especialidades = especialidades;
    }
}
