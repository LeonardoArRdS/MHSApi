package com.system.medical.mhs.dominios.pwa.modelos.pacientes;

import java.util.ArrayList;

public class AlergiasConhecidas {
	private ArrayList<String> medicamentos;
	private ArrayList<String> outras;
	
	public AlergiasConhecidas(ArrayList<String> medicamentos, ArrayList<String> outras){
		this.medicamentos = medicamentos;
		this.outras = outras;
	}
	
	public ArrayList<String> getMedicamentos(){ return medicamentos; }
	public void addMedicamento(String string) { medicamentos.add(string); }
	
	public ArrayList<String> getOutras(){ return outras; }
	public void addOutra(String string) { outras.add(string); }
}
