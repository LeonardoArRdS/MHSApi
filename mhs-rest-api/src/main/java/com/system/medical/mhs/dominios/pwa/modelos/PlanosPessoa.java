package com.system.medical.mhs.dominios.pwa.modelos;

/**
 * @author Leonardo Ariel
 *
 */
public class PlanosPessoa {
	private Long idPlano;
	private String plano;
	private Long carteirinha;
	
	public PlanosPessoa(Long idPlano, String plano, Long carteirinha) {
		this.idPlano = idPlano;
		this.plano = plano;
		this.carteirinha = carteirinha;
	}
	public Long getIdPlano() { return idPlano; }
	public String getPlano() { return plano; }
	public Long getCarteirinha() { return carteirinha; }
}
