/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.system.medical.mhs.dados.mySql.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Felipe
 */
@Entity
@Table(name = "MHS.resultado_exame")
public class ResultadoExame implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    
    @Column(name = "resultado")
    private Double resultado;
    
    @ManyToOne
    @JoinColumn(name = "id_tipo_resultado")
    private TipoResultado tipo;
    
    
    @OneToMany(mappedBy = "resultado")
    private Set<ComentarioResultadoExame> comentarios = new HashSet<>();
    
    @OneToMany(mappedBy = "resultado")
    private Set<ImagemResultadoExame> imagens = new HashSet<>();

    public ResultadoExame() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getResultado() {
        return resultado;
    }

    public void setResultado(Double resultado) {
        this.resultado = resultado;
    }

    public TipoResultado getTipo() {
        return tipo;
    }

    public void setTipo(TipoResultado tipo) {
        this.tipo = tipo;
    }

    public Set<ComentarioResultadoExame> getComentarios() {
        return comentarios;
    }

    public void setComentarios(Set<ComentarioResultadoExame> comentarios) {
        this.comentarios = comentarios;
    }

    public Set<ImagemResultadoExame> getImagens() {
        return imagens;
    }

    public void setImagens(Set<ImagemResultadoExame> imagens) {
        this.imagens = imagens;
    }
    
    
}
