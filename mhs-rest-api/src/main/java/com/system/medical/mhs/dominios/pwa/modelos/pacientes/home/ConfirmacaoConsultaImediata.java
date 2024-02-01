/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.system.medical.mhs.dominios.pwa.modelos.pacientes.home;

/**
 *
 * @author Felipe
 */
public class ConfirmacaoConsultaImediata {
    private Long protocolo;
    private String paciente;
    private String mensagem;

    public ConfirmacaoConsultaImediata(Long protocolo, String paciente, String mensagem) {
        this.protocolo = protocolo;
        this.paciente = paciente;
        this.mensagem = mensagem;
    }

    public Long getProtocolo() {
        return protocolo;
    }

    public String getPaciente() {
        return paciente;
    }

    public String getMensagem() {
        return mensagem;
    }
    
    
}
