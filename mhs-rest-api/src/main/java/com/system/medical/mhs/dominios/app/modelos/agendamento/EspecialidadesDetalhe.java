/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.system.medical.mhs.dominios.app.modelos.agendamento;

/**
 *
 * @author Felipe Mariano
 */
public class EspecialidadesDetalhe {
    private String especialidade;
    private String preco;
    private String local;

    public EspecialidadesDetalhe(String especialidade, String preco, String local) {
        this.especialidade = especialidade;
        this.preco = preco;
        this.local = local;
    }

    public String getEspecialidade() {
        return especialidade;
    }

    public String getPreco() {
        return preco;
    }

    public String getLocal() {
        return local;
    }
    
    
}
