/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.system.medical.mhs.dominios.app.modelos;

/**
 *
 * @author Felipe
 */
public class ExamePaciente {
    private String exame;
    private String data;

    public ExamePaciente(String exame, String data) {
        this.exame = exame;
        this.data = data;
    }

    public String getExame() {
        return exame;
    }

    public String getData() {
        return data;
    }
    
    
    
}
