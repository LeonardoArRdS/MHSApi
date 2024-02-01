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
public class SolicitacaoAlteracaoHorarioConsulta {
    private Long protocolo;
    private Long novaData;

    public SolicitacaoAlteracaoHorarioConsulta() {
    }
    
    public SolicitacaoAlteracaoHorarioConsulta(Long protocolo, Long novaData) {
        this.protocolo = protocolo;
        this.novaData = novaData;
    }

    public Long getProtocolo() {
        return protocolo;
    }

    public Long getNovaData() {
        return novaData;
    }
    
    
}
