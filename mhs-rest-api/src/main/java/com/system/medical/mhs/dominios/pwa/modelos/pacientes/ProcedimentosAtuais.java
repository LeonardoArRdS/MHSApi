package com.system.medical.mhs.dominios.pwa.modelos.pacientes;

import com.system.medical.mhs.dominios.pwa.modelos.Doencas;

public class ProcedimentosAtuais {
	private Doencas processoAtual;
	private Doencas processoConstante;
	
	public ProcedimentosAtuais(Doencas processoAtual, Doencas processoConstante){
		this.processoAtual = processoAtual;
		this.processoConstante = processoConstante;
	}

	public Doencas getProcessoAtual() { return processoAtual; }

	public Doencas getProcessoConstante() { return processoConstante; }
}
