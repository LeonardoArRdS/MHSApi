package com.system.medical.mhs.dominios.pwa.modelos.agenda;

/**
 * @author Leonardo Ariel
 *
 */
public class MedicoEspecialidade {
	private Long idEspecialidade;
	private String nome;
	
	public MedicoEspecialidade(Long idEspecialidade, String nome) {
		this.idEspecialidade = idEspecialidade;
		this.nome = nome;
	}
	
	public Long getIdEspecialidade() { return idEspecialidade; }
	public String getNome() { return nome; }
}
