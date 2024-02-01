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
public class ConfirmacaoCompartilhamento {
    private Long inicio;
    private Long fim;
    private String dataConsulta;
    private Long protocoloConsulta;

    public ConfirmacaoCompartilhamento(Long inicio, Long fim, String dataConsulta, Long protocoloConsulta) {
        this.inicio = inicio;
        this.fim = fim;
        this.dataConsulta = dataConsulta;
        this.protocoloConsulta = protocoloConsulta;
    }

    public Long getInicio() {
        return inicio;
    }

    public Long getFim() {
        return fim;
    }

    public String getDataConsulta() {
        return dataConsulta;
    }

    public Long getProtocoloConsulta() {
        return protocoloConsulta;
    }
    
    
}
