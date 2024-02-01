/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.system.medical.mhs.dominios.app.modelos.agendamento;

/**
 *
 * @author Felipe Mariano
 */
public class HorariosDisponiveis {
    private String turno;
    private String horario;
    private String especialidade;
    private Boolean disponivel;
    private String data;
    private String localidade;

    public HorariosDisponiveis(String turno, String horario, String especialidade, Boolean disponivel, String data, String localidade) {
        this.turno = turno;
        this.horario = horario;
        this.especialidade = especialidade;
        this.disponivel = disponivel;
        this.data = data;
        this.localidade = localidade;
    }
    
    public String getData() {
        return data;
    }
    

    public String getTurno() {
        return turno;
    }

    public String getHorario() {
        return horario;
    }

    public Boolean getDisponivel() {
        return disponivel;
    }

	public String getLocalidade() {
		return localidade;
	}

	public String getEspecialidade() {
		return especialidade;
	}
    
}
