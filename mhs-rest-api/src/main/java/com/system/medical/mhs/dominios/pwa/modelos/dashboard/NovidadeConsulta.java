/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.system.medical.mhs.dominios.pwa.modelos.dashboard;

/**
 *
 * @author Felipe
 */
public class NovidadeConsulta extends Novidade{
    
    private String paciente;
    private Boolean prontuario;
    private String tipoConsulta;
    
    public NovidadeConsulta(String titulo, String paciente,
            String tipoConsulta, Boolean prontuario, String mensagem, Long hora, String horaString, String tipo) {
        super(titulo, mensagem, hora, horaString, tipo);
    }

    public String getPaciente() {
        return paciente;
    }

    public void setPaciente(String paciente) {
        this.paciente = paciente;
    }

    public Boolean getProntuario() {
        return prontuario;
    }

    public void setProntuario(Boolean prontuario) {
        this.prontuario = prontuario;
    }

    public String getTipoConsulta() {
        return tipoConsulta;
    }

    public void setTipoConsulta(String tipoConsulta) {
        this.tipoConsulta = tipoConsulta;
    }
    
    
    
}
