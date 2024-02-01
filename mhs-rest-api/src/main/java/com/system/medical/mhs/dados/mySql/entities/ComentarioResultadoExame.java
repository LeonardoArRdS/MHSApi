/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.system.medical.mhs.dados.mySql.entities;

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
@Table(name = "")
public class ComentarioResultadoExame {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "id_resultado_exame")
    private ResultadoExame resultado;
    
    @ManyToOne
    @JoinColumn(name = "id_tipo_comentario")
    private TipoComentario tipo;
    
    @Column(name = "comentario")
    private String comentario;

    public ComentarioResultadoExame() {
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

    public TipoComentario getTipo() {
        return tipo;
    }

    public void setTipo(TipoComentario tipo) {
        this.tipo = tipo;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }
    
    
}
