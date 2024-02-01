/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.system.medical.mhs.dominios.app.modelos.agendamento;

import java.util.List;

/**
 *
 * @author Felipe Mariano
 */
public class Medico {
    private String nome;
    private String especialidades;
    private List<String> formacoes;
    private List<String> localidades;
    private DisponibilidadeMedico proximaDisponibilidade;
    private String precoMedio;
     

    public Medico(String nome, String especialidades, List<String> formacoes, List<String> localidades,
    		DisponibilidadeMedico proximaDisponibilidade, String precoMedio) {
		super();
		this.nome = nome;
		this.especialidades = especialidades;
		this.formacoes = formacoes;
		this.localidades = localidades;
		this.proximaDisponibilidade = proximaDisponibilidade;
		this.precoMedio = precoMedio;
	}
    
	public String getNome() {
        return nome;
    }

    public List<String> getFormacoes() {
		return formacoes;
	}

	public List<String> getLocalidades() {
		return localidades;
	}

	public String getEspecialidades() {
        return especialidades;
    }

    public DisponibilidadeMedico getProximaDisponibilidade() {
        return proximaDisponibilidade;
    }

    public String getPrecoMedio() {
        return precoMedio;
    }
    
    
}
