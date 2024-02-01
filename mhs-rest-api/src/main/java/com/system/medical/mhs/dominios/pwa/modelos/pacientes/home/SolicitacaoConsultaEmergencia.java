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
public class SolicitacaoConsultaEmergencia {
    private Long crm;
    private String UF;
    private Long cpfPaciente;
    private Long idEspecialidade;
    private Long idPlano;

    public SolicitacaoConsultaEmergencia() {
    }
    
    public SolicitacaoConsultaEmergencia(Long crm, String UF, Long cpfPaciente, Long idEspecialidade, Long idPlano) {                          
        this.crm = crm;
        this.UF = UF;
        this.cpfPaciente = cpfPaciente;
        this.idEspecialidade = idEspecialidade;
        this.idPlano = idPlano;
    }

    public Long getCrm() {
        return crm;
    }

    public String getUF() {
        return UF;
    }

    public Long getCpfPaciente() {
        return cpfPaciente;
    }

    public Long getIdEspecialidade() {
        return idEspecialidade;
    }

    public Long getIdPlano() {
        return idPlano;
    } 
    
}
