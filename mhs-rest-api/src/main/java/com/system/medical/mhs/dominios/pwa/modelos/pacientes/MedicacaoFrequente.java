package com.system.medical.mhs.dominios.pwa.modelos.pacientes;

import java.util.ArrayList;

public class MedicacaoFrequente {
	 private ArrayList<String> medicacoes;
	 
	 public MedicacaoFrequente(){
		 medicacoes = new ArrayList<String>();
	 }
	 
	 public MedicacaoFrequente(ArrayList<String> medicacoes){
		 this.medicacoes = medicacoes;
	 }
	 
	public ArrayList<String> getMedicacao(){ return medicacoes; }
	public void addMedicacao(String string) { medicacoes.add(string); }
}
