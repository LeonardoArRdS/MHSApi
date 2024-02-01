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
 * @author Felipe Mariano
 */
@Entity
@Table(name = "MHS.contratacao_especialidade")
public class ContratacaoEspecialidade implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "id_especialidade")
    private Especialidade especialidade;
    
    @ManyToOne
    @JoinColumn(name = "id_contratacao")
    private Contratacao contratacao;
    
    @Column(name = "inicio_atendimento")
    private Date inicioAtendimento;
    
    @Column(name = "fim_atendimento")
    private Date fimAtendimento;
    
    @Column(name = "intervalo_atendimento")
    private Date intervaloAtendimento;

    public ContratacaoEspecialidade() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Especialidade getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(Especialidade especialidade) {
        this.especialidade = especialidade;
    }

    public Contratacao getContratacao() {
        return contratacao;
    }

    public void setContratacao(Contratacao contratacao) {
        this.contratacao = contratacao;
    }
    

    public Date getInicioAtendimento() {
        return inicioAtendimento;
    }

    public void setInicioAtendimento(Date inicioAtendimento) {
        this.inicioAtendimento = inicioAtendimento;
    }

    public Date getFimAtendimento() {
        return fimAtendimento;
    }

    public void setFimAtendimento(Date fimAtendimento) {
        this.fimAtendimento = fimAtendimento;
    }

    public Date getIntervaloAtendimento() {
        return intervaloAtendimento;
    }

    public void setIntervaloAtendimento(Date intervaloAtendimento) {
        this.intervaloAtendimento = intervaloAtendimento;
    }
    
    
}
