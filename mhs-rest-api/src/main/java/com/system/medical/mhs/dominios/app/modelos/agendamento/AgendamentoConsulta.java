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
public class AgendamentoConsulta {
    private Long idPaciente;
    private Long crm;
    private String uf;
    private Long dataHorario;
    private Long idClinica;
    private Long idEspecialidade;
    private Long retornoDe;
    private Long idPlano;
    
    public Long getCrm() {
        return crm;
    }

    public String getUf() {
        return uf;
    }

    public Long getDataHorario() {
        return dataHorario;
    }

    public Long getIdClinica() {
        return idClinica;
    }

    public Long getIdEspecialidade() {
        return idEspecialidade;
    }

    public Long getRetornoDe() {
        return retornoDe;
    }

    public Long getIdPaciente() {
        return idPaciente;
    }

    public Long getIdPlano() {
        return idPlano;
    }

    public void setIdPlano(Long idPlano) {
        this.idPlano = idPlano;
    }

	@Override
	public String toString() {
		return "AgendamentoConsulta [idPaciente=" + idPaciente + ", crm=" + crm + ", uf=" + uf + ", dataHorario="
				+ dataHorario + ", idClinica=" + idClinica + ", idEspecialidade=" + idEspecialidade + ", retornoDe="
				+ retornoDe + ", idPlano=" + idPlano + "]";
	}
    
}
