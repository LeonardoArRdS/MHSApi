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
@Table(name = "MHS.status_geral_consulta_pessoa")
public class StatusGeralConsultaPessoa implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @Column(name = "descricao")
    private String descricao;
    
    @Column(name = "data_hora")
    private Date dataHora;
    
    @ManyToOne
    @JoinColumn(name = "id_status_consulta")
    private StatusConsulta status;
    
    @ManyToOne
    @JoinColumn(name = "id_status_geral_consulta")
    private StatusConsultaGeral statusGeral;
    
    @ManyToOne
    @JoinColumn(name = "protocolo_consulta")
    private Consulta consulta;

    public StatusGeralConsultaPessoa() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Date getDataHora() {
        return dataHora;
    }

    public void setDataHora(Date dataHora) {
        this.dataHora = dataHora;
    }

    public StatusConsulta getStatus() {
        return status;
    }

    public void setStatus(StatusConsulta status) {
        this.status = status;
    }

    public StatusConsultaGeral getStatusGeral() {
        return statusGeral;
    }

    public void setStatusGeral(StatusConsultaGeral statusGeral) {
        this.statusGeral = statusGeral;
    }

    public Consulta getConsulta() {
        return consulta;
    }

    public void setConsulta(Consulta consulta) {
        this.consulta = consulta;
    }
    
    
    
}
