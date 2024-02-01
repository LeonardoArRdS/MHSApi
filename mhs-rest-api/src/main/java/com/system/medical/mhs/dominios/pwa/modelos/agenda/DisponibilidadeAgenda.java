/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.system.medical.mhs.dominios.pwa.modelos.agenda;

/**
 *
 * @author Felipe
 */
public class DisponibilidadeAgenda {
    private Long data;
    private String dataExtenso;
    private String status;

    public DisponibilidadeAgenda(Long data, String dataExtenso, String status) {
        this.data = data;
        this.dataExtenso = dataExtenso;
        this.status = status;
    }

    public Long getData() {
        return data;
    }
    

    public String getDataExtenso() {
        return dataExtenso;
    }

    public String getStatus() {
        return status;
    }
    
    
}
