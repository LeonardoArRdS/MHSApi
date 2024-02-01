/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.system.medical.mhs.dados.mySql.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author Felipe
 */
@Entity
@Table(name = "MHS.familiar")
public class Familiar implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @Column(name = "descricao")
    private String descricao;
    
    @ManyToOne
    @JoinColumn(name = "id_parentesco")
    private Parentesco parentesco;
    
    @ManyToOne
    @JoinColumn(name = "id_pessoa")
    private Pessoa pessoa;
    
    @ManyToMany
    @JoinTable(name = "doenca_familiar", joinColumns = @JoinColumn(name = "id_familiar"), inverseJoinColumns = @JoinColumn(name = "id_doenca"))
    private List<Doenca> doencas = new ArrayList<>();
    
    public Familiar() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Parentesco getParentesco() {
        return parentesco;
    }

    public void setParentesco(Parentesco parentesco) {
        this.parentesco = parentesco;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public List<Doenca> getDoencas() {
        return doencas;
    }

    public void setDoencas(List<Doenca> doencas) {
        this.doencas = doencas;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    
}
