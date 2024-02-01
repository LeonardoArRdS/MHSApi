/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.system.medical.mhs.dominios.app.modelos.forms;

/**
 *
 * @author Felipe
 */
public class FormsOpcaoPergunta {
    private Long id;
    private String opcao;

    public FormsOpcaoPergunta(Long id, String opcao) {
        this.id = id;
        this.opcao = opcao;
    }

    public Long getId() {
        return id;
    }

    public String getOpcao() {
        return opcao;
    }
    
    
}
