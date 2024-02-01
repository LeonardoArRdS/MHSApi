/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.system.medical.mhs.dominios.app.acoes.utilidades;

import com.system.medical.mhs.dominios.RespostaDomain;
import com.system.medical.mhs.dominios.app.interfaces.forms.IUtilidadesRepository;
import com.system.medical.mhs.dominios.app.modelos.utilidades.ModelEspecialidade;
import com.system.medical.mhs.dominios.app.modelos.utilidades.ModelPlano;
import com.system.medical.mhs.dominios.app.modelos.utilidades.ModelRegiao;
import java.util.List;

/**
 *
 * @author Felipe
 */
public class AppUtilidades {
    private IUtilidadesRepository utilidadesRepository;

    public AppUtilidades(IUtilidadesRepository utilidadesRepository) {
        this.utilidadesRepository = utilidadesRepository;
    }
    
    public RespostaDomain<List<ModelRegiao>> getRegioes(){
        return this.utilidadesRepository.getRegioes();
    }
    
    public RespostaDomain<List<ModelEspecialidade>> getEspecialidades(){
        return this.utilidadesRepository.getEspecialidade();
    }
    
    public RespostaDomain<List<ModelPlano>> getPlanos(){
        return this.utilidadesRepository.getPlanos();
    }
}
