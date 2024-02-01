/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.system.medical.mhs.dominios.app.modelos.prontuario;

import java.util.List;
import java.util.Set;

/**
 *
 * @author Felipe
 */
public class ResumoHistoricoDoencas {
    private List<String> atuais;
    private List<String> passadas;
    private Set<String> familia;

    public ResumoHistoricoDoencas(List<String> atuais, List<String> passadas, Set<String> familia) {
        this.atuais = atuais;
        this.passadas = passadas;
        this.familia = familia;
    }

    public List<String> getAtuais() {
        return atuais;
    }

    public List<String> getPassadas() {
        return passadas;
    }

    public Set<String> getFamilia() {
        return familia;
    }
    
    
}
