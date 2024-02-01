package com.system.medical.mhs.dominios.pwa.modelos.dashboard;

import java.util.Date;
import java.util.List;

/**
*
* @author Leonardo Ariel
*/
public class UltimasConsultas {
	private String imagem;
	private String paciente;
	private Date data;
	private String conclusoes;
	private List<String> sintomas;
	
	public UltimasConsultas(String imagem, String paciente, Date data, String conclusoes, List<String> sintomas) {
		this.imagem = imagem;
		this.paciente = paciente;
		this.data = data;
		this.conclusoes = conclusoes;
		this.sintomas = sintomas;
	}
	
	public String getImagem() { return imagem; }

	public String getPaciente() { return paciente; }

	public Date getData() { return data; }

	public String getConclusoes() { return conclusoes; }

	public List<String> getSintomas() { return sintomas; }
}
