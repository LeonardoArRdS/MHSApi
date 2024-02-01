package com.system.medical.mhs.dominios.pwa.modelos.agenda;

/**
 * @author Leonardo Ariel
 *
 */
public class PlanosClinica {
	private Long idPlano;
	private String plano;
	
	public PlanosClinica(Long idPlano, String plano) {
		this.idPlano = idPlano;
		this.plano = plano;
	}
	
	public Long getIdPlano() { return idPlano; }
	public String getPlano() { return plano; }
}
