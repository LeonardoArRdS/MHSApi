/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.system.medical.mhs.dados.mySql.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.CascadeType;
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
@Table(name = "MHS.andamento_consulta")
public class AndamentoConsulta implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @ManyToOne(cascade = {CascadeType.MERGE})
    @JoinColumn(name = "protocolo_consulta")
    private Consulta consulta;
    
    @Column(name = "data_hora")
    private Date horaAtualizacao;
    
    @ManyToOne
    @JoinColumn(name = "id_status_andamento")
    private StatusAndamentoConsulta status;

    public AndamentoConsulta() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Consulta getConsulta() {
        return consulta;
    }

    public void setConsulta(Consulta consulta) {
        this.consulta = consulta;
    }

    public Date getHoraAtualizacao() {
        return horaAtualizacao;
    }

    public void setHoraAtualizacao(Date horaAtualizacao) {
        this.horaAtualizacao = horaAtualizacao;
    }

    public StatusAndamentoConsulta getStatus() {
        return status;
    }

    public void setStatus(StatusAndamentoConsulta status) {
        this.status = status;
    }
    
    
}
