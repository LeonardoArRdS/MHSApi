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
public class ConfirmacaoAlteracao {
    private String dataAnterior;
    private String dataAtual;
    private Long minutosDiferenca;
    private Long protocolo;

    public ConfirmacaoAlteracao(String dataAnterior, String dataAtual, Long minutosDiferenca, Long protocolo) {
        this.dataAnterior = dataAnterior;
        this.dataAtual = dataAtual;
        this.minutosDiferenca = minutosDiferenca;
        this.protocolo = protocolo;
    }

    public String getDataAnterior() {
        return dataAnterior;
    }

    public String getDataAtual() {
        return dataAtual;
    }

    public Long getMinutosDiferenca() {
        return minutosDiferenca;
    }

    public Long getProtocolo() {
        return protocolo;
    }
    
    
}
