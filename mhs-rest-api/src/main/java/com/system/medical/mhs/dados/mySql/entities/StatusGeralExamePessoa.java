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
@Table(name = "MHS.status_geral_exame_pessoa")
public class StatusGeralExamePessoa implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "id_exame_pessoa")
    private ExamePessoa exame;
    
    @ManyToOne
    @JoinColumn(name = "id_status_realizacao")
    private StatusRealizacao statusRealizacao;
    
    @ManyToOne
    @JoinColumn(name = "id_status_geral_exame")
    private StatusExameGeral statusGeral;
    
    @Column(name = "descricao")
    private String descricao;
    
    @Column(name = "data_hora")
    private Date dataHora;

    public StatusGeralExamePessoa() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ExamePessoa getExame() {
        return exame;
    }

    public void setExame(ExamePessoa exame) {
        this.exame = exame;
    }

    public StatusRealizacao getStatusRealizacao() {
        return statusRealizacao;
    }

    public void setStatusRealizacao(StatusRealizacao statusRealizacao) {
        this.statusRealizacao = statusRealizacao;
    }

    public StatusExameGeral getStatusGeral() {
        return statusGeral;
    }

    public void setStatusGeral(StatusExameGeral statusGeral) {
        this.statusGeral = statusGeral;
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
    
    
}
