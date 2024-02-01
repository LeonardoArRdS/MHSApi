package com.system.medical.mhs.dominios.pwa.modelos;

public class Doencas {
	private String doenca;
	private String CID;
	
	public Doencas(String doenca, String CID){
		this.doenca = doenca;
		this.CID = CID;
	}
	
	public String getDoenca() { return doenca; }

	public String getCID() { return CID; }
}
