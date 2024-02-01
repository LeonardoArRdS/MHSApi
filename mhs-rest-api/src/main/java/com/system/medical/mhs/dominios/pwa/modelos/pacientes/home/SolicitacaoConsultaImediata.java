/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.system.medical.mhs.dominios.pwa.modelos.pacientes.home;

import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Felipe
 */
public class SolicitacaoConsultaImediata {
    private Long cpfPaciente;
    private Long crm;
    private String uf;
    private Long idEspecialidade;
    private Set<Long> sintomas = new HashSet<>();
    private Long idPlano;

    public SolicitacaoConsultaImediata(Long cpfPaciente, Long crm, String uf, Long idEspecialidade, Long idPlano) {
        this.cpfPaciente = cpfPaciente;
        this.crm = crm;
        this.uf = uf;
        this.idEspecialidade = idEspecialidade;
        this.idPlano = idPlano;
    }

    public SolicitacaoConsultaImediata() {
    }
    
    public Long getCpfPaciente() {
        return cpfPaciente;
    }

    public Long getCrm() {
        return crm;
    }

    public String getUf() {
        return uf;
    }

    public Long getIdEspecialidade() {
        return idEspecialidade;
    }

    public Set<Long> getSintomas() {
        return sintomas;
    }

    public Long getIdPlano() {
        return idPlano;
    }
    
    
}
