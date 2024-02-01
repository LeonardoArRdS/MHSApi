package com.system.medical.mhs.dominios.pwa.modelos.pacientes;

import java.util.Date;

public class ExamesRecentes {
	private Date data;
	private String exame;
	private boolean anormalidade;
	
	public ExamesRecentes(Date data, String exame, boolean anormalidade) {
		this.data = data;
		this.exame = exame;
		this.anormalidade = anormalidade;
	}

	public Date getData() { return data; }

	public String getExame() { return exame; }

	public boolean isAnormalidade() { return anormalidade; }
}
