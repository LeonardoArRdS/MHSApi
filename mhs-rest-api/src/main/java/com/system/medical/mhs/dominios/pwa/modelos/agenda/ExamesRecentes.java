package com.system.medical.mhs.dominios.pwa.modelos.agenda;

import java.util.Date;

public class ExamesRecentes {
	private Date data;
	private String exame;
	private String anormalidade;
	
	public ExamesRecentes(Date data, String exame, String anormalidade) {
		this.data = data;
		this.exame = exame;
		this.anormalidade = anormalidade;
	}

	public Date getData() { return data; }

	public String getExame() { return exame; }

	public String getAnormalidade() { return anormalidade; }
}
