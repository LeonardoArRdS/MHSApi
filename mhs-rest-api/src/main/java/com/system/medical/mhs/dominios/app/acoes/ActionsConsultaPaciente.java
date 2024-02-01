/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.system.medical.mhs.dominios.app.acoes;

import com.system.medical.mhs.dominios.RespostaDomain;
import com.system.medical.mhs.dominios.app.interfaces.IConsultasPacienteRepository;
import com.system.medical.mhs.dominios.app.modelos.ConsultasPaciente;

import java.util.List;

/**
 *
 * @author Felipe
 */
public class ActionsConsultaPaciente {
    private IConsultasPacienteRepository consultasRepository;

    public ActionsConsultaPaciente(IConsultasPacienteRepository consultasRepository) {
        this.consultasRepository = consultasRepository;
    }

    public RespostaDomain<List<ConsultasPaciente>> getConsultasFuturas(Long CPF, Integer rows){
        return this.consultasRepository.getConsultasFuturas(CPF, rows);
    }
}
