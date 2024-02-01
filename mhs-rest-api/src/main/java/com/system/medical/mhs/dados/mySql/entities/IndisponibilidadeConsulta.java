/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.system.medical.mhs.dados.mySql.entities;

import java.io.Serializable;
import java.util.Date;
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
@Entity
@Table(name = "MHS.indisponibilidade_consulta")
public class IndisponibilidadeConsulta implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_solicitante")
    private Pessoa solicitante;

    @ManyToOne
    @JoinColumn(name = "id_contratacao")
    private ContratacaoEspecialidade contratacao;

    @Column(name = "data_hora_indisponibilidade")
    private Date indisponibilidade;

    @Column(name = "data_criacao")
    private Date criacao;

    public IndisponibilidadeConsulta() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Pessoa getSolicitante() {
        return solicitante;
    }

    public void setSolicitante(Pessoa solicitante) {
        this.solicitante = solicitante;
    }

    public Date getIndisponibilidade() {
        return indisponibilidade;
    }

    public void setIndisponibilidade(Date indisponibilidade) {
        this.indisponibilidade = indisponibilidade;
    }

    public Date getCriacao() {
        return criacao;
    }

    public void setCriacao(Date criacao) {
        this.criacao = criacao;
    }

    public ContratacaoEspecialidade getContratacao() {
        return contratacao;
    }

    public void setContratacao(ContratacaoEspecialidade contratacao) {
        this.contratacao = contratacao;
    }
    
}
