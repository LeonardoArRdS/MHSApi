package com.system.medical.mhs.dominios.pwa.modelos.agenda;

import java.util.List;

/**
 * @author Leonardo Ariel
 *
 */
public class MedicosClinica {
	private Long crm;
	private String nome;
	private List<MedicoEspecialidade> especialidades;
	
	public MedicosClinica(Long crm, String nome, List<MedicoEspecialidade> especialidades) {
		this.crm = crm;
		this.nome = nome;
		this.especialidades = especialidades;
	}
	public Long getCrm() { return crm; }
	public String getNome() { return nome; }
	public List<MedicoEspecialidade> getEspecialidades() { return especialidades; }
}
