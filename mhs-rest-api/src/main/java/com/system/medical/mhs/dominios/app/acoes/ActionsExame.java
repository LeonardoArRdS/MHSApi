/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.system.medical.mhs.dominios.app.acoes;

import com.system.medical.mhs.dominios.RespostaDomain;
import com.system.medical.mhs.dominios.app.interfaces.IExamesRepository;
import com.system.medical.mhs.dominios.app.modelos.ExamePaciente;

import java.util.List;

/**
 *
 * @author Felipe
 */
public class ActionsExame {
    private IExamesRepository examesRepository;

    public ActionsExame(IExamesRepository examesRepository) {
        this.examesRepository = examesRepository;
    }
    
    public RespostaDomain<List<ExamePaciente>> getExamesFuturos(Long CPF, Integer rows){
        return examesRepository.getExamesFuturos(CPF, rows);
    }
    
}
