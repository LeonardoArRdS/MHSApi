package com.system.medical.mhs.dominios.pwa.modelos.agenda;

import java.util.Date;
import java.util.List;

/**
 * @author Leonardo Ariel
 *
 */
public class ConsultasRecentes {
	private Date data;
	private String especialidade;
	private List<String> anormalidades;
	
	public ConsultasRecentes(Date data, String especialidade, List<String> anormalidades) {
		this.data = data;
		this.especialidade = especialidade;
		this.anormalidades = anormalidades;
	}

	public Date getData() { return data; }
	public String getEspecialidade() { return especialidade; }
	public List<String> getAnormalidade() { return anormalidades; }
}
