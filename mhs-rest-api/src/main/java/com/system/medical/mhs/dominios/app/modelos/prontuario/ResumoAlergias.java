/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.system.medical.mhs.dominios.app.modelos.prontuario;

import java.util.List;

/**
 *
 * @author Felipe
 */
public class ResumoAlergias {
     private List<String> medicamentos;
    private List<String> outros;

    public ResumoAlergias(List<String> medicamentos, List<String> outros) {
        this.medicamentos = medicamentos;
        this.outros = outros;
    }

    public List<String> getMedicamentos() {
        return medicamentos;
    }

    public List<String> getOutros() {
        return outros;
    }
}
