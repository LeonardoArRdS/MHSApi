package com.system.medical.mhs.dominios.pwa.modelos;

public class OpcoesContato {
	private boolean email;
	private boolean celular;
	
	public OpcoesContato(boolean email, boolean celular){
		this.email = email;
		this.celular = celular;
	}

	public boolean isEmail() { return email; }
	
	public boolean isCelular() { return celular; }
}
