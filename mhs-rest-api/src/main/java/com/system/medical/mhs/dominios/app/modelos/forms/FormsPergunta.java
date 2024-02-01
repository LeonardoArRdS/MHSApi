/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.system.medical.mhs.dominios.app.modelos.forms;

import java.util.List;

/**
 *
 * @author Felipe
 */
public class FormsPergunta {
    private Long id;
    private String pergunta;
    private Boolean caracteristicaFisica;
    private List<FormsOpcaoPergunta> opcoes;

    public FormsPergunta(Long id, String pergunta, Boolean caracteristicaFisica, List<FormsOpcaoPergunta> opcoes) {
        this.id = id;
        this.pergunta = pergunta;
        this.caracteristicaFisica = caracteristicaFisica;
        this.opcoes = opcoes;
    }

    public Long getId() {
        return id;
    }

    public String getPergunta() {
        return pergunta;
    }

    public Boolean getCaracteristicaFisica() {
        return caracteristicaFisica;
    }

    public List<FormsOpcaoPergunta> getOpcoes() {
        return opcoes;
    }
    
    
}
