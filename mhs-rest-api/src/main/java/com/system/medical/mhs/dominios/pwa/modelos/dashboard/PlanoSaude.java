package com.system.medical.mhs.dominios.pwa.modelos.dashboard;

/**
 * @author Leonardo Ariel
 *
 */
public class PlanoSaude {
	private String empresa;
	private Long numero;
	
	public PlanoSaude(String empresa, Long numero) {
		this.empresa = empresa;
		this.numero = numero;
	}
	
	public String getEmpresa() { return empresa; }
	public Long getNumero() { return numero; }
}
