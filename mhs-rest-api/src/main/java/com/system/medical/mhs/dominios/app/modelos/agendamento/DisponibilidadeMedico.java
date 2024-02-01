package com.system.medical.mhs.dominios.app.modelos.agendamento;

public class DisponibilidadeMedico {
	private String disponibilidade;
	private String localidade;
	
	public DisponibilidadeMedico(String disponibilidade, String localidade) {
		super();
		this.disponibilidade = disponibilidade;
		this.localidade = localidade;
	}
	public String getDisponibilidade() {
		return disponibilidade;
	}
	public String getLocalidade() {
		return localidade;
	}
	
	
}
