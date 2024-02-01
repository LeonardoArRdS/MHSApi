/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.system.medical.mhs.dominios.app.modelos.prontuario;

/**
 *
 * @author Felipe
 */
public class ResumoTratamentos {
    private String nome;
    private String medicacoes;

    public ResumoTratamentos(String nome, String medicacoes) {
        this.nome = nome;
        this.medicacoes = medicacoes;
    }

    public String getNome() {
        return nome;
    }

    public String getMedicacoes() {
        return medicacoes;
    }
    
    
}
