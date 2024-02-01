package com.system.medical.mhs.dominios.pwa.modelos.agenda;

import java.util.List;

public class HistoricoDoencas {
	private List<String> doencasAtuais;
	private List<String> doencasPassadas;
	private List<String> doencasFamilia;
	
	public HistoricoDoencas(List<String> doencasAtuais, List<String> doencasPassadas, List<String> doencasFamilia) {
		this.doencasAtuais = doencasAtuais;
		this.doencasPassadas = doencasPassadas;
		this.doencasFamilia = doencasFamilia;
	}

	public List<String> getStringAtuais() { return doencasAtuais; }
	
	public List<String> getStringPassadas() { return doencasPassadas; }
	
	public List<String> getStringFamilia() { return doencasFamilia; }
}
