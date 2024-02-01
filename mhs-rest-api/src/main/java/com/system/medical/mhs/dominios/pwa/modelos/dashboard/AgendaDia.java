package com.system.medical.mhs.dominios.pwa.modelos.dashboard;

import java.util.ArrayList;

/**
*
* @author Leonardo Ariel
*/
public class AgendaDia {
	private String imagem;
    private String paciente;
    private boolean prontuario;
    private String hora;

    public AgendaDia(String imagem, String paciente, boolean prontuario, String hora) {
		this.imagem = imagem;
		this.paciente = paciente;
		this.prontuario = prontuario;
		this.hora = hora;
	}
    
	/**
	 * @param agenda
	 */
	public AgendaDia(ArrayList<AgendaDia> agenda) {
		// TODO Auto-generated constructor stub
	}

	public String getImagem() { return imagem; }
	public void setImagem(String imagem) { this.imagem = imagem; }

	public String getPaciente() { return paciente; }
    public void setPaciente(String paciente) { this.paciente = paciente; }
    
    

    public boolean isProntuario() { return prontuario; }
	public void setProntuario(boolean prontuario) { this.prontuario = prontuario; }

	public String getHora() { return hora; }
    public void setHora(String hora) { this.hora = hora; }
}
