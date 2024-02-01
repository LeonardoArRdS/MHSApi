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
public class SolicitacaoCompartilhamento {
    private Long idPaciente;
    private Long crm;
    private String uf;
    private Long protocoloConsulta;
    private Long dataHoraConsulta;

    public SolicitacaoCompartilhamento() {
    }
    
    

    public SolicitacaoCompartilhamento(Long idPaciente, Long crm, String uf, Long protocoloConsulta,Long dataHoraConsulta) {
        this.idPaciente = idPaciente;
        this.crm = crm;
        this.uf = uf;
        this.protocoloConsulta = protocoloConsulta;
        this.dataHoraConsulta = dataHoraConsulta;
    }

    public Long getIdPaciente() {
        return idPaciente;
    }

    public Long getCrm() {
        return crm;
    }

    public String getUf() {
        return uf;
    }

    public Long getProtocoloConsulta() {
        return protocoloConsulta;
    }

    public Long getDataHoraConsulta() {
        return dataHoraConsulta;
    }
    
    
    
}
