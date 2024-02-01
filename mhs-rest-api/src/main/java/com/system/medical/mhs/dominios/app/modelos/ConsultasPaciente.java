/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.system.medical.mhs.dominios.app.modelos;

/**
 *
 * @author Felipe Mariano
 */
public class ConsultasPaciente {
    private Long protocolo;
    private String especialidade;
    private String medico;
    private String data;
    private String hora;
    private String local;

    public ConsultasPaciente(Long protocolo, String especialidade, String medico, String data, String hora, String local) {
        this.protocolo = protocolo;
        this.especialidade = especialidade;
        this.medico = medico;
        this.data = data;
        this.hora = hora;
        this.local = local;
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

    public String getHora() {
        return hora;
    }

    public String getLocal() {
        return local;
    }

    
}
