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
public class ConfirmacaoInicioConsulta {
    private String dataHora;
    private Long protocolo;
    private String status;

    public ConfirmacaoInicioConsulta(String dataHora, Long protocolo, String status) {
        this.dataHora = dataHora;
        this.protocolo = protocolo;
        this.status = status;
    }

    public String getDataHora() {
        return dataHora;
    }

    public Long getProtocolo() {
        return protocolo;
    }
    
    
}
