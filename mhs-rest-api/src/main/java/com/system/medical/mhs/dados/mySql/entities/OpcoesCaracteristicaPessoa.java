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
@Entity
@Table(name = "MHS.opcoes_caracteristica_pessoa")
@AssociationOverrides({
    @AssociationOverride(name = "id.pessoa", joinColumns = @JoinColumn(name = "id_pessoa")),
    @AssociationOverride(name = "id.caracteristica", joinColumns = @JoinColumn(name = "id_caracteristica")),
    @AssociationOverride(name = "id.opcao", joinColumns = @JoinColumn(name  = "id_opcao"))
})
public class OpcoesCaracteristicaPessoa {

    @EmbeddedId
    private IdOpcoesCaracteristicaPessoa id;
    
    
    
    @Column(name = "descricao")
    private String descricao;

    public OpcoesCaracteristicaPessoa() {
    }

    public OpcoesCaracteristicaPessoa(Caracteristica caracteristica, OpcoesCaracteristica opcao, Pessoa pessoa){
        this.id = new IdOpcoesCaracteristicaPessoa(caracteristica, opcao, pessoa);
    }
    
    public IdOpcoesCaracteristicaPessoa getId() {
        return id;
    }

    public void setId(IdOpcoesCaracteristicaPessoa id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    
    

}

@Embeddable
class IdOpcoesCaracteristicaPessoa implements Serializable {

    @ManyToOne
    private Caracteristica caracteristica;
    
    @ManyToOne
    private OpcoesCaracteristica opcao;
    
    @ManyToOne
    private Pessoa pessoa;

    public IdOpcoesCaracteristicaPessoa() {
    }

    IdOpcoesCaracteristicaPessoa(Caracteristica caracteristica, OpcoesCaracteristica opcao, Pessoa pessoa) {
        this.caracteristica = caracteristica;
        this.opcao = opcao;
        this.pessoa = pessoa;
    }

    public Caracteristica getCaracteristica() {
        return caracteristica;
    }

    public void setCaracteristica(Caracteristica caracteristica) {
        this.caracteristica = caracteristica;
    }

    public OpcoesCaracteristica getOpcao() {
        return opcao;
    }

    public void setOpcao(OpcoesCaracteristica opcao) {
        this.opcao = opcao;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }
    
    
    
}
