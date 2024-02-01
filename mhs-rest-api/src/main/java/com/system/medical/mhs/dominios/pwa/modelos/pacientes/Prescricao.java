package com.system.medical.mhs.dominios.pwa.modelos.pacientes;

public class Prescricao {
	private boolean status;
	private String data;
	private String medicamento;
	private String dose;
	private String via;
	private String frequencia;
	
	public Prescricao(boolean status,String data, String medicamento, String dose, String via, String frequencia){
		this.status = status;
		this.data = data;
		this.medicamento = medicamento;
		this.dose = dose;
		this.via = via;
		this.frequencia = frequencia;
	}

	public boolean isStatus() { return status; }

	public String getData() { return data; }

	public String getMedicamento() { return medicamento; }

	public String getDose() { return dose; }

	public String getVia() { return via; }

	public String getFrequencia() { return frequencia; }
}
