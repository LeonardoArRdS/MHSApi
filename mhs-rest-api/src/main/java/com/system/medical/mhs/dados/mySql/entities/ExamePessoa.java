/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.system.medical.mhs.dados.mySql.entities;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
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
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author Felipe
 */
@Entity
@Table(name = "MHS.exame_pessoa")
public class ExamePessoa implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "data_realizacao")
    private Date dataRealizacao;

    @ManyToOne
    @JoinColumn(name = "id_exame")
    private Exame exame;

    @ManyToOne
    @JoinColumn(name = "id_pessoa")
    private Pessoa pessoa;

    @OneToOne
    @JoinColumn(name = "id_status_realizacao")
    private StatusRealizacao status;

    @ManyToMany
    @JoinTable(name = "resultado_exame_pessoa", joinColumns = @JoinColumn(name = "id_exame_pessoa"), inverseJoinColumns = @JoinColumn(name = "id_id_resultado_exame"))
    public Set<ResultadoExame> resultados = new HashSet<>();

    private Boolean anormalidade;

    public ExamePessoa() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<ResultadoExame> getResultados() {
        return resultados;
    }

    public void setResultados(Set<ResultadoExame> resultados) {
        this.resultados = resultados;
    }
    
    public Date getDataRealizacao() {
        return dataRealizacao;
    }

    public void setDataRealizacao(Date dataRealizacao) {
        this.dataRealizacao = dataRealizacao;
    }

    public Exame getExame() {
        return exame;
    }

    public void setExame(Exame exame) {
        this.exame = exame;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public StatusRealizacao getStatus() {
        return status;
    }

    public void setStatus(StatusRealizacao status) {
        this.status = status;
    }

    public Boolean getAnormalidade() {
        return anormalidade;
    }

    public void setAnormalidade(Boolean anormalidade) {
        this.anormalidade = anormalidade;
    }

    public String getData() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return sdf.format(dataRealizacao);
    }
}
