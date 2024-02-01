package com.system.medical.mhs.dominios.pwa.modelos.agenda;

import java.util.List;

/**
 * @author Leonardo Ariel
 *
 */
public class ConsultaSemana {
	private String dia;
	private String diaSemana;
	private List<ConsultaAgenda> consultas;
	
	public ConsultaSemana(String dia, String diaSemana, List<ConsultaAgenda> consultas) {
		this.dia = dia;
		this.diaSemana = diaSemana;
		this.consultas = consultas;
	}

	public String getDia() { return dia; }

	public String getDiaSemana() { return diaSemana; }

	public List<ConsultaAgenda> getConsultas() { return consultas; }
}
