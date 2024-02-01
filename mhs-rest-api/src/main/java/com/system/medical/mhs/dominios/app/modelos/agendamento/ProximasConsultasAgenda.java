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
public class ProximasConsultasAgenda {
    private Long protocolo;
    private String especialidade;
    private String medico;
    private String data;

    public ProximasConsultasAgenda(Long protocolo, String especialidade, String medico, String data) {
        this.protocolo = protocolo;
        this.especialidade = especialidade;
        this.medico = medico;
        this.data = data;
    }

    public Long getProtocolo() {
        return protocolo;
    }

    public String getEspecialidade() {
        return especialidade;
    }

    public String getMedico() {
        return medico;
    }

    public String getData() {
        return data;
    }
    
    
}
