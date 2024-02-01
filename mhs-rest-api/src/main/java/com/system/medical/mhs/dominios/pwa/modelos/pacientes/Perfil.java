package com.system.medical.mhs.dominios.pwa.modelos.pacientes;

import java.util.Date;
import java.util.List;

public class Perfil {
	private String foto;
    private String nome;
    private String sobrenome;
    private Date dataNascimento;
    private String sexo;
    private String nacionalidade;

    public Perfil(String foto, String nome, String sobrenome, Date dataNascimento, String sexo, String nacionalidade) {
        this.foto = foto;
    	this.nome = nome;
        this.sobrenome = sobrenome;
        this.dataNascimento = dataNascimento;
        this.sexo = sexo;
        this.nacionalidade = nacionalidade;
    }

    public String getFoto() {
		return foto;
	}

	public String getNome() {
        return nome;
    }
    
    public String getSobrenome() {
        return sobrenome;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public String getSexo() {
        return sexo;
    }

    public String getNacionalidade() {
        return nacionalidade;
    }
}
