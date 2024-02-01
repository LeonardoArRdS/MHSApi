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
@Entity
@Table(name = "MHS.categoria_caracteristica")
public class CategoriaCaracteristica implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "nome")
    private String nome;

    @ManyToMany
    @JoinTable(name = "categorias_caracteristicas", joinColumns = @JoinColumn(name = "id_categoria"), inverseJoinColumns = @JoinColumn(name = "id_caracteristica"))
    private Set<Caracteristica> caracteristicas = new HashSet<>();
    
    @ManyToMany
    @JoinTable(name = "categorias_caracteristicas_fisica", joinColumns = @JoinColumn(name = "id_categoria"), inverseJoinColumns = @JoinColumn(name = "id_caracteristica_fisica"))
    private Set<CaracteristicaFisica> caracteristicasFisica = new HashSet<>();
    
    public CategoriaCaracteristica() {
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

    public Set<Caracteristica> getCaracteristicas() {
        return caracteristicas;
    }

    public void setCaracteristicas(Set<Caracteristica> caracteristicas) {
        this.caracteristicas = caracteristicas;
    }

    public Set<CaracteristicaFisica> getCaracteristicasFisica() {
        return caracteristicasFisica;
    }

    public void setCaracteristicasFisica(Set<CaracteristicaFisica> caracteristicasFisica) {
        this.caracteristicasFisica = caracteristicasFisica;
    }
    
}
