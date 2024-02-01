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
@Table(name = "MHS.imagem_resultado_exame")
public class ImagemResultadoExame implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "id_resultado_exame")
    private ResultadoExame resultado;
    
    @Column(name = "data_hora_upload")
    private Date dataHoraUpload;
    
    @Column(name = "caminho")
    private String caminho;

    public ImagemResultadoExame() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ResultadoExame getResultado() {
        return resultado;
    }

    public void setResultado(ResultadoExame resultado) {
        this.resultado = resultado;
    }

    public Date getDataHoraUpload() {
        return dataHoraUpload;
    }

    public void setDataHoraUpload(Date dataHoraUpload) {
        this.dataHoraUpload = dataHoraUpload;
    }

    public String getCaminho() {
        return caminho;
    }

    public void setCaminho(String caminho) {
        this.caminho = caminho;
    }
    
    
}
