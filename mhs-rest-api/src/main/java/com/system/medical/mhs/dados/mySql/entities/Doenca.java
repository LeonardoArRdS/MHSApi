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
import javax.persistence.Table;

/**
 *
 * @author Felipe
 */
@Table(name = "MHS.doenca")
@Entity
public class Doenca implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "CID")
    private String CID;

    @Column(name = "nome")
    private String nome;

    @Column(name = "nome_popular")
    private String nomePopular;

    @ManyToMany
    @JoinTable(name = "doenca_sintoma", joinColumns = @JoinColumn(name = "id_doenca"), inverseJoinColumns = @JoinColumn(name = "id_sintoma"))
    private Set<Sintoma> sintomas = new HashSet<>();

    @ManyToMany
    @JoinTable(name = "doenca_categoria", joinColumns = @JoinColumn(name = "id_doenca"), inverseJoinColumns = @JoinColumn(name = "id_categoria"))
    private Set<CategoriaDoenca> categorias = new HashSet<>();

    @ManyToMany
    @JoinTable(name = "doenca_fator", joinColumns = @JoinColumn(name = "id_doenca"), inverseJoinColumns = @JoinColumn(name = "id_fator"))
    private Set<FatorDoenca> fatores = new HashSet<>();

    public Doenca() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCID() {
        return CID;
    }

    public void setCID(String CID) {
        this.CID = CID;
    }
    
    
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNomePopular() {
        return nomePopular;
    }

    public void setNomePopular(String nomePopular) {
        this.nomePopular = nomePopular;
    }

    public Set<CategoriaDoenca> getCategorias() {
        return categorias;
    }

    public void setCategorias(Set<CategoriaDoenca> categorias) {
        this.categorias = categorias;
    }

    public Set<FatorDoenca> getFatores() {
        return fatores;
    }

    public void setFatores(Set<FatorDoenca> fatores) {
        this.fatores = fatores;
    }

    public Set<Sintoma> getSintomas() {
        return sintomas;
    }

    public void setSintomas(Set<Sintoma> sintomas) {
        this.sintomas = sintomas;
    }

}
