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
public class ResumoConsulta {
    private String data;
    private String especialidade;
    private String resumoComentario;

    public ResumoConsulta(String data, String especialidade, String resumoComentario) {
        this.data = data;
        this.especialidade = especialidade;
        this.resumoComentario = resumoComentario;
    }

    public String getData() {
        return data;
    }

    public String getEspecialidade() {
        return especialidade;
    }

    public String getResumoComentario() {
        return resumoComentario;
    }
    
    
}
