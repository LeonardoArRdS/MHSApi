/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.system.medical.mhs.dados.mySql.entities.log;

import com.system.medical.mhs.dados.mySql.entities.Consulta;
import com.system.medical.mhs.dados.mySql.entities.Pessoa;
import com.system.medical.mhs.dados.mySql.entities.ProtocoloConsulta;
import com.system.medical.mhs.dados.mySql.entities.StatusConsulta;
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
@Table(name = "MHS.log_consulta")
public class LogConsulta implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "protocolo_consulta")
    private Consulta consulta;
    
    @ManyToOne
    @JoinColumn(name = "id_status_anterior")
    private StatusConsulta statusAnterior;
    
    @ManyToOne
    @JoinColumn(name = "id_status_atual")
    private StatusConsulta statusAtual;
    
    @ManyToOne
    @JoinColumn(name = "id_solicitante")
    private Pessoa solicitante;
    
    @Column(name = "data_hora_realizacao")
    private Date realizacao;

    public LogConsulta() {
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
    
    public StatusConsulta getStatusAnterior() {
        return statusAnterior;
    }

    public void setStatusAnterior(StatusConsulta statusAnterior) {
        this.statusAnterior = statusAnterior;
    }

    public StatusConsulta getStatusAtual() {
        return statusAtual;
    }

    public void setStatusAtual(StatusConsulta statusAtual) {
        this.statusAtual = statusAtual;
    }

    public Pessoa getSolicitante() {
        return solicitante;
    }

    public void setSolicitante(Pessoa solicitante) {
        this.solicitante = solicitante;
    }

    public Date getRealizacao() {
        return realizacao;
    }

    public void setRealizacao(Date realizacao) {
        this.realizacao = realizacao;
    }
    
    
}
