package com.system.medical.mhs.dominios.pwa.modelos.dashboard;

/**
*
* @author Leonardo Ariel
*/
public class ProximasConsultas {
	private String imagem;
    private String paciente;
    private Boolean prontuario;
    private String plano;
    private String hora;

    public ProximasConsultas(String imagem, String paciente, Boolean prontuario, String plano, String hora) {
		this.imagem = imagem;
		this.paciente = paciente;
		this.prontuario = prontuario;
		this.plano = plano;
		this.hora = hora;
	}
    
	public String getImagem() { return imagem; }
	public void setImagem(String imagem) { this.imagem = imagem; }

	public String getPaciente() { return paciente; }
    public void setPaciente(String paciente) { this.paciente = paciente; }
    
    public boolean isProntuario() { return prontuario; }
	public void setProntuario(boolean prontuario) { this.prontuario = prontuario; }
	
	public String getPlano() { return plano; }
	public void setPlano(String plano) { this.plano = plano; }

	public String getHora() { return hora; }
    public void setHora(String hora) { this.hora = hora; }
}
