package com.system.medical.mhs.dominios.pwa.modelos.pacientes;

import java.util.Date;


public class Dados {
	private String nome;
	private Date dataNascimento;
	private double altura;
	private String cor;
	private String sexo;
	private double peso;
	private String nacionalidade;
	
	public Dados(String nome, Date dataNascimento, double altura, String cor, String sexo, double peso, String nacionalidade) {
		this.nome = nome;
		this.dataNascimento = dataNascimento;
		this.altura = altura;
		this.cor = cor;
		this.sexo = sexo;
		this.peso = peso;
		this.nacionalidade = nacionalidade;
	}
	
	public String getNome() { return nome; }

	public Date getDataNascimento() { return dataNascimento; }

	public double getAltura() { return altura; }

	public String getCor() { return cor; }

	public String getSexo() { return sexo; }

	public double getPeso() { return peso; }

	public String getNacionalidade() { return nacionalidade; }

}
