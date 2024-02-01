/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.system.medical.mhs.dominios.app.modelos.prontuario;

/**
 *
 * @author Felipe
 */
public class ResumoPlanoSaude {
    private String plano;
    private Long carteirinha;

    public ResumoPlanoSaude(String plano, Long carteirinha) {
        this.plano = plano;
        this.carteirinha = carteirinha;
    }

    public String getPlano() {
        return plano;
    }

    public Long getCarteirinha() {
        return carteirinha;
    }
    
    
}
