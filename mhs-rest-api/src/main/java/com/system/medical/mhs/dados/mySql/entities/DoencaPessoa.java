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
@Table(name = "MHS.doenca_pessoa")
@Entity
public class DoencaPessoa implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    
    @ManyToOne
    @JoinColumn(name = "id_doenca")
    private Doenca doenca;
    
    @ManyToOne
    @JoinColumn(name = "id_pessoa")
    private Pessoa pessoa;
    
    @ManyToOne
    @JoinColumn(name = "id_status_doenca")
    private StatusDoenca status;
    
    @OneToMany(mappedBy = "id.doencaPessoa")
    public Set<SintomaDoencaPessoa> sintomas = new HashSet<>();

    public DoencaPessoa() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Doenca getDoenca() {
        return doenca;
    }

    public void setDoenca(Doenca doenca) {
        this.doenca = doenca;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public Set<SintomaDoencaPessoa> getSintomas() {
        return sintomas;
    }

    public void setSintomas(Set<SintomaDoencaPessoa> sintomas) {
        this.sintomas = sintomas;
    }

    public StatusDoenca getStatus() {
        return status;
    }

    public void setStatus(StatusDoenca status) {
        this.status = status;
    }
    
    
}
