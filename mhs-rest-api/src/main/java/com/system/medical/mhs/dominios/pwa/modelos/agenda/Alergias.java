/**
 * 
 */
package com.system.medical.mhs.dominios.pwa.modelos.agenda;

import java.util.ArrayList;

/**
 * @author Leonardo Ariel
 *
 */
public class Alergias {
	private ArrayList<String> medicamentos;
	private ArrayList<String> outras;
	
	public Alergias(ArrayList<String> medicamentos, ArrayList<String> outras){
		this.medicamentos = medicamentos;
		this.outras = outras;
	}
	
	public ArrayList<String> getMedicamentos(){ return medicamentos; }
	public void addMedicamento(String string) { medicamentos.add(string); }
	
	public ArrayList<String> getOutras(){ return outras; }
	public void addOutra(String string) { outras.add(string); }
}
