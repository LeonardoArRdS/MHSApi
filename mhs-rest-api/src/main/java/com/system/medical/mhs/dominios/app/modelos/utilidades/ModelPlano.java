/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.system.medical.mhs.dominios.app.modelos.utilidades;

/**
 *
 * @author Felipe
 */
public class ModelPlano {
    private Long id;
    private String nome;
    private String convenio;

    public ModelPlano(Long id, String nome, String convenio) {
        this.id = id;
        this.nome = nome;
        this.convenio = convenio;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getConvenio() {
        return convenio;
    }
    
    
}
