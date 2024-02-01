/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.system.medical.mhs.dominios.app.modelos.agendamento;

import java.util.List;

/**
 *
 * @author Felipe Mariano
 */
public class MedicoResumo {
    private String nome;
    private String especialidades;
   // private List<String> locais;
    private String preco;

    public MedicoResumo(String nome, String especialidades, String preco) {
        this.nome = nome;
        this.especialidades = especialidades;
        this.preco = preco;
    }

    
    

    public String getNome() {
        return nome;
    }

    public String getEspecialidades() {
        return especialidades;
    }


    public String getPreco() {
        return preco;
    }
        
}
