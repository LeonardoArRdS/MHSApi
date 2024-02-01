/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.system.medical.mhs.dominios.app.modelos.forms;

import com.system.medical.mhs.dados.mySql.AbstractRepository;
import com.system.medical.mhs.dominios.RespostaDomain;
import com.system.medical.mhs.dominios.app.interfaces.forms.IFormsRepository;

import java.util.List;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Felipe
 */

public class FormsResposta {
    private Long idPaciente;
    private Long idPergunta;
    private Long idOpcao;
    private String valorado;
    private Boolean caracteristicaFisica;
    private Double valorCaractFisica;
    
    public FormsResposta() {
    }

    public Long getIdPergunta() {
        return idPergunta;
    }

    public Long getIdOpcao() {
        return idOpcao;
    }

    public String getValorado() {
        return valorado;
    }

    public Boolean getCaracteristicaFisica() {
        return caracteristicaFisica;
    }

    public Double getValorCaractFisica() {
        return valorCaractFisica;
    }    

    public Long getIdPaciente() {
        return idPaciente;
    }
    
}
