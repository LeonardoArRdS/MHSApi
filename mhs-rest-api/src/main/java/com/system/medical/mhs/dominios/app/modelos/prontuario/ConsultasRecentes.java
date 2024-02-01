package com.system.medical.mhs.dominios.app.modelos.prontuario;

import java.util.Date;
import java.util.List;

public class ConsultasRecentes {
	private Date data;
	private String especialidade;
	private List<String> anormalidades;
	
	public ConsultasRecentes(Date data, String especialidade, List<String> anormalidade) {
		this.data = data;
		this.especialidade = especialidade;
		this.anormalidades = anormalidades;
	}

	public Date getData() { return data; }

	public String getEspecialidade() { return especialidade; }

	public List<String> getAnormalidades() { return anormalidades; }
}
