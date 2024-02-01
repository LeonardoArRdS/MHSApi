/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.system.medical.mhs.dados.mySql.entities;

import java.io.Serializable;
import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;
import javax.persistence.Column;
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
@Table(name = "MHS.sintoma_doenca_pessoa")
@Entity
@AssociationOverrides({
    @AssociationOverride(name = "id.sintoma", joinColumns = @JoinColumn(name = "id_sintoma")),
    @AssociationOverride(name = "id.doencaPessoa", joinColumns = @JoinColumn(name = "id_doenca_pessoa"))
})
public class SintomaDoencaPessoa implements Serializable{
    @EmbeddedId
    private IdSintomaDoencaPessoa id;
    
    @ManyToOne
    @JoinColumn(name = "id_intensidade")
    private Intensidade intensidade;

    public SintomaDoencaPessoa() {
    }

    public IdSintomaDoencaPessoa getId() {
        return id;
    }

    public void setId(IdSintomaDoencaPessoa id) {
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
}

@Embeddable
class IdSintomaDoencaPessoa implements Serializable {
    @ManyToOne
    private DoencaPessoa doencaPessoa;
    
    @ManyToOne
    private Sintoma sintoma;

    public IdSintomaDoencaPessoa() {
    }

    public DoencaPessoa getDoencaPessoa() {
        return doencaPessoa;
    }

    public void setDoencaPessoa(DoencaPessoa doencaPessoa) {
        this.doencaPessoa = doencaPessoa;
    }

    public Sintoma getSintoma() {
        return sintoma;
    }

    public void setSintoma(Sintoma sintoma) {
        this.sintoma = sintoma;
    }
    
    
}
