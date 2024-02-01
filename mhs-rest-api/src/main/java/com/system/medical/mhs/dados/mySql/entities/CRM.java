/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.system.medical.mhs.dados.mySql.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author Felipe
 */
@Table(name = "MHS.crm")
@Entity
public class CRM implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @OneToOne
    @JoinColumn(name = "id_pessoa")
    private Pessoa pessoa;

    @Column(name = "valor")
    private Long valor;
    
    @ManyToOne
    @JoinColumn(name = "id_estado")
    private Estado estado;
    
    @OneToMany(mappedBy = "id.crm")
    private Set<ProtocoloConsulta> consultas = new HashSet<>();
   
    @ManyToMany
    @JoinTable(name = "crm_especialidade", joinColumns = @JoinColumn(name = "id_crm"), inverseJoinColumns = @JoinColumn(name = "id_especialidade"))
    public Set<Especialidade> especialidades = new HashSet<>();
    
    @OneToMany(mappedBy = "crm")
    private List<Formacao> formacoes = new ArrayList<>();

	public CRM() {
    }
	
    public List<Formacao> getFormacoes() {
		return formacoes;
	}

	public void setFormacoes(List<Formacao> formacoes) {
		this.formacoes = formacoes;
	}


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getValor() {
        return valor;
    }

    public void setValor(Long valor) {
        this.valor = valor;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }
    
    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public Set<ProtocoloConsulta> getConsultas() {
        return consultas;
    }

    public void setConsultas(Set<ProtocoloConsulta> consultas) {
        this.consultas = consultas;
    }
    
    public Set<Especialidade> getEspecialidades() {
        return especialidades;
    }

    public void setEspecialidades(Set<Especialidade> especialidades) {
        this.especialidades = especialidades;
    }
    
}
