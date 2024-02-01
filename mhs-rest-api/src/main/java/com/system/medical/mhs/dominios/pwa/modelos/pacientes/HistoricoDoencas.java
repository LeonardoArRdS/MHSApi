package com.system.medical.mhs.dominios.pwa.modelos.pacientes;

import java.util.List;

import com.system.medical.mhs.dominios.pwa.modelos.Doencas;

public class HistoricoDoencas {
	private List<Doencas> doencasAtuais;
	private List<Doencas> doencasPassadas;
	private List<Doencas> doencasFamilia;

	public HistoricoDoencas(List<Doencas> doencasAtuais, List<Doencas> doencasPassadas, List<Doencas> doencasFamilia) {
		this.doencasAtuais = doencasAtuais;
		this.doencasPassadas = doencasPassadas;
		this.doencasFamilia = doencasFamilia;
	}

	public List<Doencas> getDoencasAtuais() { return doencasAtuais; }
	
	public List<Doencas> getDoencasPassadas() { return doencasPassadas; }
	
	public List<Doencas> getDoencasFamilia() { return doencasFamilia; }
}
