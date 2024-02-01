/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.system.medical.mhs.dominios.app.acoes;

import com.system.medical.mhs.dominios.RespostaDomain;
import com.system.medical.mhs.dominios.app.modelos.agendamento.HorariosDisponiveis;

import java.util.Calendar;
import java.util.List;
import com.system.medical.mhs.dominios.app.interfaces.agendamento.IHorariosDisponiveisRepository;

/**
 *
 * @author Felipe
 */
public class ActionsConsultaMedico {
    private IHorariosDisponiveisRepository consultasRepository;

    public ActionsConsultaMedico(IHorariosDisponiveisRepository consultasRepository) {
        this.consultasRepository = consultasRepository;
    }
    
    public RespostaDomain<List<HorariosDisponiveis>> getHorariosDisponiveis(Long CRM, String UF){
        return this.consultasRepository.getHorariosDisponiveis(CRM, UF);
    }
    
    public RespostaDomain<List<HorariosDisponiveis>> getHorariosDisponiveisPorDia(Long CRM, String UF, Long diaMilli, Long idEspecialidade){
        
        Calendar dia = Calendar.getInstance();
        dia.setTimeInMillis(diaMilli);        
        
        return this.consultasRepository.getHorariosDisponiveisPorDia(CRM, UF, dia, idEspecialidade);
    }
}
