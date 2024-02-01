/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.system.medical.mhs.dominios.app.modelos.prontuario;

import com.system.medical.mhs.dominios.RespostaDomain;
import com.system.medical.mhs.dominios.app.interfaces.prontuario.IResumoProntuarioRepository;
import java.util.List;

/**
 *
 * @author Felipe
 */
public class ResumoDadosPessoais {
   private String nome;
   private String dataNascimento;
   private String altura;
   private String raca;
   private String sexo;
   private String peso;
   private String nacionalidade;

    public ResumoDadosPessoais(String nome, String dataNascimento, String altura, String raca, String sexo, String peso, String nacionalidade) {
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.altura = altura;
        this.raca = raca;
        this.sexo = sexo;
        this.peso = peso;
        this.nacionalidade = nacionalidade;
    }

   
    public String getDataNascimento() {
        return dataNascimento;
    }

    public String getAltura() {
        return altura;
    }

    public String getRaca() {
        return raca;
    }

    public String getSexo() {
        return sexo;
    }

    public String getPeso() {
        return peso;
    }

    public String getNacionalidade() {
        return nacionalidade;
    }
   
   
}
