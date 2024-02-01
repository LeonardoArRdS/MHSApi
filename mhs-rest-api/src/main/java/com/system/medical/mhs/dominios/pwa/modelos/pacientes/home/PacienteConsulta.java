package com.system.medical.mhs.dominios.pwa.modelos.pacientes.home;

import java.util.Date;

/**
 * @author Leonardo Ariel
 *
 */
public class PacienteConsulta {
	private String paciente;
	private boolean prontuario;
	private String horaConsulta;
	
	public PacienteConsulta(String paciente, boolean prontuario, String horaConsulta) {
		this.paciente = paciente;
		this.prontuario = prontuario;
		this.horaConsulta = horaConsulta;
	}

	public String getPaciente() { return paciente; }

	public boolean isProntuario() { return prontuario; }

	public String getHoraConsulta() { return horaConsulta; }	
}
