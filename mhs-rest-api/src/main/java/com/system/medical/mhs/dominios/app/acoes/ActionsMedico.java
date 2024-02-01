/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.system.medical.mhs.dominios.app.acoes;

import com.system.medical.mhs.dominios.RespostaDomain;
import com.system.medical.mhs.dominios.app.modelos.agendamento.Medico;
import com.system.medical.mhs.dominios.app.modelos.agendamento.MedicoResumo;
import com.system.medical.mhs.utils.Validations;

import java.util.List;
import com.system.medical.mhs.dominios.app.interfaces.agendamento.IMedicosDisponiveisRepository;

/**
 *
 * @author Felipe
 */
public class ActionsMedico {

    private IMedicosDisponiveisRepository medicosRepository;

    public ActionsMedico(IMedicosDisponiveisRepository medicosRepository) {
        this.medicosRepository = medicosRepository;
    }
    
    public RespostaDomain<List<MedicoResumo>> buscar(Long idPlano, Long idEspecialidade, Long idRegiao){
        return this.medicosRepository.getMedicos(idPlano, idEspecialidade, idRegiao);
    }

    public RespostaDomain<List<MedicoResumo>> buscarPorEspecialidade(Long idEspecialidade) {
        return this.medicosRepository.getMedicosPorEspecialidade(idEspecialidade);
    }
    
    public RespostaDomain<List<MedicoResumo>> buscarPorPlano(Long idPlano){
        return this.medicosRepository.getMedicosByPlano(idPlano);
    }
    
    public RespostaDomain<Medico> getDetalhe(Long CRM, String UF, Long idEspecialidade){
    	RespostaDomain response =  new RespostaDomain(null);
    	try {
			Validations.isValidCRM(CRM, UF);
			response = this.medicosRepository.getDetalhe(CRM, UF, idEspecialidade);
		} catch (Exception e) {
			response.adicionarMensagemErro(e.getMessage());
		}
        
    	return response;
    }
}

