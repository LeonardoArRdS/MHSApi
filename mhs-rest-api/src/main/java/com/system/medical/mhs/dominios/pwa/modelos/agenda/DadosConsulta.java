package com.system.medical.mhs.dominios.pwa.modelos.agenda;

import java.util.List;

import com.system.medical.mhs.dominios.pwa.modelos.PlanosPessoa;

/**
 * @author Leonardo Ariel
 *
 */
public class DadosConsulta {
	private Long idPaciente;
    private String nome;
    private String email;
    private Long celular;
    private List<PlanosPessoa> planos;
    
	public DadosConsulta(Long idPaciente, String nome, String email, Long celular, List<PlanosPessoa> planos) {
		this.idPaciente = idPaciente;
		this.nome = nome;
		this.email = email;
		this.celular = celular;
		this.planos = planos;
	}
	
	public Long getIdPaciente() { return idPaciente; }
	public String getNome() { return nome; }
	public String getEmail() { return email; }
	public Long getCelular() { return celular; }
	public List<PlanosPessoa> getPlanos() { return planos; }
}
