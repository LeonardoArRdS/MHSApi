package com.system.medical.mhs.dominios.pwa.modelos.dashboard;

/**
*
* @author Leonardo Ariel
*/
public class Novidade {
	private String titulo;
	private String mensagem;
	private Long hora;
        private Long horaString;
	private String tipo;
	
	public Novidade(String titulo, String mensagem, Long hora, String horaString, String tipo) {
		this.titulo = titulo;
		this.mensagem = mensagem;
		this.hora = hora;
		this.tipo = tipo;
	}

	public String getTitulo() { return titulo; }

	public String getMensagem() { return mensagem; }

	public Long getHora() { return hora; }

	public String getTipo() { return tipo; }
}
