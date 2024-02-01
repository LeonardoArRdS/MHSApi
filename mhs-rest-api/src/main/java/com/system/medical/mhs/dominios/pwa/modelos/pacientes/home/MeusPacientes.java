package com.system.medical.mhs.dominios.pwa.modelos.pacientes.home;

import java.util.Date;

/**
 * @author Leonardo Ariel
 *
 */
public class MeusPacientes {
	private String imagem;
	private String paciente;
	private String sexo;
	private int idade;
	private double altura;
	private double peso;
	private boolean prontuario;
	private Date ultimaConsulta;
	
	public MeusPacientes(String imagem, String paciente, String sexo, int idade, double altura, double peso, boolean prontuario, Date ultimaConsulta) {
		this.imagem = imagem;
		this.paciente = paciente;
		this.sexo = sexo;
		this.idade = idade;
		this.altura = altura;
		this.peso = peso;
		this.prontuario = prontuario;
		this.ultimaConsulta = ultimaConsulta;
	}

	public String getImagem() { return imagem; }
	public String getPaciente() { return paciente; }
	public String getSexo() { return sexo; }
	public int getIdade() { return idade; }
	public double getAltura() { return altura; }
	public double getPeso() { return peso; }
	public boolean isProntuario() { return prontuario; }
	public Date getUltimaConsulta() { return ultimaConsulta; }
}
