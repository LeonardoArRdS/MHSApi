/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.system.medical.mhs.dados.mySql.entities;

import java.io.Serializable;
import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;
import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author Felipe
 */
@Table(name = "MHS.sintoma_consulta")
@Entity
@AssociationOverrides({
    @AssociationOverride(name = "id.sintoma", joinColumns = @JoinColumn(name = "id_sintoma")),
    @AssociationOverride(name = "id.consulta", joinColumns = @JoinColumn(name = "protocolo_consulta"))
})
public class SintomaConsulta implements Serializable{
    @EmbeddedId
    private IdSintomaConsulta id;
    
    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "id_intensidade")
    private Intensidade intensidade;
    
    public SintomaConsulta() {
        this.id = new IdSintomaConsulta();
    }

    public IdSintomaConsulta getId() {
        return id;
    }

    public void setId(IdSintomaConsulta id) {
        this.id = id;
    }

    public Intensidade getIntensidade() {
        return intensidade;
    }

    public void setIntensidade(Intensidade intensidade) {
        this.intensidade = intensidade;
    }
    
    public Sintoma getSintoma(){
        return this.id.getSintoma();
    }
    
    public void setConsulta(Consulta consulta){
        this.id.setConsulta(consulta);
    }
    
    public void setSintoma(Sintoma sintoma){
        this.id.setSintoma(sintoma);
    }
    
}

@Embeddable
class IdSintomaConsulta implements Serializable {
    
    @ManyToOne
    private Consulta consulta;
    
    @ManyToOne(cascade = {CascadeType.ALL})
    private Sintoma sintoma;

    public IdSintomaConsulta() {
    }

    public Consulta getConsulta() {
        return consulta;
    }

    public void setConsulta(Consulta consulta) {
        this.consulta = consulta;
    }

    public Sintoma getSintoma() {
        return sintoma;
    }

    public void setSintoma(Sintoma sintoma) {
        this.sintoma = sintoma;
    }

    
}