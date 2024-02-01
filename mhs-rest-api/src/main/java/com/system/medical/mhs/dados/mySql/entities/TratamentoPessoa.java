/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.system.medical.mhs.dados.mySql.entities;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
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
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 *
 * @author Felipe
 */
@Entity
@Table(name = "MHS.tratamento_pessoa")
public class TratamentoPessoa implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "descricao")
    private String descricao;
    
    @ManyToOne
    @JoinColumn(name = "id_pessoa")
    private Pessoa pessoa;

    @ManyToOne
    @JoinColumn(name = "id_tratamento_doenca")
    private TratamentoDoenca tratamentoDoenca;

    @Column(name = "data_inicio")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date inicio;

    @Column(name = "data_termino")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date termino;

    @ManyToOne
    @JoinColumn(name = "id_frequencia")
    private Frequencia frequencia;

    @ManyToMany
    @JoinTable(name = "tratamento_medicacao_medicacao", joinColumns = @JoinColumn(name = "id_tratamento_pessoa"), inverseJoinColumns = @JoinColumn(name = "id_medicacao"))
    private Set<Medicacao> medicacoes = new HashSet<>();

    public TratamentoPessoa() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public TratamentoDoenca getTratamentoDoenca() {
        return tratamentoDoenca;
    }

    public void setTratamentoDoenca(TratamentoDoenca tratamentoDoenca) {
        this.tratamentoDoenca = tratamentoDoenca;
    }

    public Date getInicio() {
        return inicio;
    }

    public String getDataInicio() {
        return new SimpleDateFormat("dd/MM/yyyy").format(this.getInicio());
    }

    public void setInicio(Date inicio) {
        this.inicio = inicio;
    }

    public Date getTermino() {
        return termino;
    }

    public String getDataTermino() {
        return new SimpleDateFormat("dd/MM/yyyy").format(this.getTermino());
    }

    public void setTermino(Date termino) {
        this.termino = termino;
    }

    public Frequencia getFrequencia() {
        return frequencia;
    }

    public void setFrequencia(Frequencia frequencia) {
        this.frequencia = frequencia;
    }

    public Set<Medicacao> getMedicacoes() {
        return medicacoes;
    }

    public void setMedicacoes(Set<Medicacao> medicacoes) {
        this.medicacoes = medicacoes;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    
}
