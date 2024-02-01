package com.system.medical.mhs.dominios.app.modelos;

/**
*
* @author Felipe
*/

public class Consulta {
    private String paciente;
    private String data;
    private String hora;
    private String especialidade;

    public Consulta(String paciente, String data, String hora) {
    	this.paciente = paciente;
        this.data = data;
        this.hora = hora;
    }
    
	public String getPaciente() { return paciente; }
    public void setPaciente(String paciente) { this.paciente = paciente; }

    public String getData() { return data; }
    public void setData(String data) { this.data = data; }

    public String getHora() { return hora; }
    public void setHora(String hora) { this.hora = hora; }

    public String getEspecialidade() { return especialidade; }
    public void setEspecialidade(String especialidade) { this.especialidade = especialidade; }
}
