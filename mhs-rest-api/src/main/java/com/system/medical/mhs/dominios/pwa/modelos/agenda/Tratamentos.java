package com.system.medical.mhs.dominios.pwa.modelos.agenda;

/**
 * @author Leonardo Ariel
 *
 */
public class Tratamentos {
	private String medicacaoFrequente;
	private String nivelAtividadeFisica;
	
	public Tratamentos(String medicacaoFrequente, String nivelAtividadeFisica) {
		this.medicacaoFrequente = medicacaoFrequente;
		this.nivelAtividadeFisica = nivelAtividadeFisica;
	}
	public String getMedicacaoFrequente() { return medicacaoFrequente; }
	public String getNivelAtividadeFisica() { return nivelAtividadeFisica; }
}
