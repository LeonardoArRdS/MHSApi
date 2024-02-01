package com.system.medical.mhs.dominios.pwa.modelos.pacientes;

import java.util.ArrayList;

import com.system.medical.mhs.dominios.pwa.modelos.Doencas;

public class DoencaFrequente {
	private ArrayList<Doencas> doencas;
	
	public DoencaFrequente(ArrayList<Doencas> doencas){
		this.doencas = doencas;
	}

	public ArrayList<Doencas> getDoencas() { return doencas; }
}
