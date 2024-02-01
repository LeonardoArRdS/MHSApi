package com.system.medical.mhs.dominios.pwa.modelos.agenda;

/**
 * @author Leonardo Ariel
 *
 */
public class PlanoSaude {
	private String tipoSanguineo;
	private String planoSaude;
	private Long numeroCarteirinha;
	private boolean doadorOrgaos;
	
	public PlanoSaude(String tipoSanguineo, String planoSaude, Long numeroCarteirinha, boolean doadorOrgaos) {
		this.tipoSanguineo = tipoSanguineo;
		this.planoSaude = planoSaude;
		this.numeroCarteirinha = numeroCarteirinha;
		this.doadorOrgaos = doadorOrgaos;
	}

	public String getTipoSanguineo() { return tipoSanguineo; }

	public String getPlanoSaude() { return planoSaude; }

	public Long getNumeroCarteirinha() { return numeroCarteirinha; }

	public boolean isDoadorOrgaos() { return doadorOrgaos; }
}
