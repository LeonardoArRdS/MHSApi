/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.system.medical.mhs.dominios.app.modelos.agendamento;

/**
 *
 * @author Felipe
 */
public class ConsultaAgendada {
    private Long protocolo;
    private String medico;
    private String local;
    private String especialidade;
    private String dataHora;

    public ConsultaAgendada(Long protocolo, String medico, String local, String especialidade, String dataHora) {
        this.protocolo = protocolo;
        this.medico = medico;
        this.local = local;
        this.especialidade = especialidade;
        this.dataHora = dataHora;
    }
    
    public Long getProtocolo() {
		return protocolo;
	}

	public void setProtocolo(Long protocolo) {
		this.protocolo = protocolo;
	}

	public String getMedico() {
        return medico;
    }

    public String getLocal() {
        return local;
    }

    public String getEspecialidade() {
        return especialidade;
    }

    public String getDataHora() {
        return dataHora;
    }
    
    
}
