/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.system.medical.mhs.dados.mySql.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Felipe
 */
@Table(name = "MHS.especialidade")
@Entity
public class Especialidade implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "nome")
    private String nome;

    @ManyToMany
    @JoinTable(name = "crm_especialidade", joinColumns = @JoinColumn(name = "id_especialidade"), inverseJoinColumns = @JoinColumn(name = "id_crm"))
    public Set<CRM> crms = new HashSet<>();

    @OneToMany(mappedBy = "especialidade")
    //@JoinTable(name = "contratacao_especialidade", joinColumns = @JoinColumn(name = "id_especialidade"), inverseJoinColumns = @JoinColumn(name = "id_contratacao"))
    private Set<ContratacaoEspecialidade> contratacoes = new HashSet<>();

    public Especialidade() {
    }

    public Especialidade(Long id) {
        this.id = id;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Set<CRM> getCrms() {
        return crms;
    }

    public void setCrms(Set<CRM> crms) {
        this.crms = crms;
    }

    public Set<ContratacaoEspecialidade> getContratacoes() {
        return contratacoes;
    }

    public void setContratacoes(Set<ContratacaoEspecialidade> contratacoes) {
        this.contratacoes = contratacoes;
    }

}
