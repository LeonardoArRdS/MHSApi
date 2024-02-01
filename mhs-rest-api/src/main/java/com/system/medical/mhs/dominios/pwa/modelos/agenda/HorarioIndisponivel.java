/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.system.medical.mhs.dominios.pwa.modelos.agenda;

import java.util.List;

/**
 *
 * @author Felipe
 */
public class HorarioIndisponivel {
    private Long crm;
    private String uf;
    private List<Long> horarios;

    public HorarioIndisponivel() {
    }
    
    public HorarioIndisponivel(Long crm, String uf, List<Long> horarios) {
        this.crm = crm;
        this.uf = uf;
        this.horarios = horarios;
    }

    public Long getCrm() {
        return crm;
    }

    public String getUf() {
        return uf;
    }

    public List<Long> getHorarios() {
        return horarios;
    }
    
    
}
