/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.system.medical.mhs.dominios.pwa.modelos;

/**
 *
 * @author Felipe
 */
public class MedicacaoTratamento {
    private String nome;
    private String dosagem;
    private String frequencia;

    public MedicacaoTratamento(String nome, String dosagem, String frequencia) {
        this.nome = nome;
        this.dosagem = dosagem;
        this.frequencia = frequencia;
    }

    public String getNome() {
        return nome;
    }

    public String getDosagem() {
        return dosagem;
    }

    public String getFrequencia() {
        return frequencia;
    }
    
    
}
