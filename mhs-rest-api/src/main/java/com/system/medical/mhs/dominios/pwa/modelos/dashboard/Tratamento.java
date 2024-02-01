package com.system.medical.mhs.dominios.pwa.modelos.dashboard;

public class Tratamento {
	private String tratamento;
	private String dosagem;
	private String duracao;
	private String frequencia;
	
	public Tratamento(
			String tratamento,
			String dosagem,
			String duracao,
			String frequencia){
		this.tratamento = tratamento;
		this.dosagem = dosagem;
		this.duracao = duracao;
		this.frequencia = frequencia;
	}
	
	public String getTratamento() { return tratamento; }

	public String getDosagem() { return dosagem; }

	public String getDuracao() { return duracao; }

	public String getFrequencia() { return frequencia; }
}
