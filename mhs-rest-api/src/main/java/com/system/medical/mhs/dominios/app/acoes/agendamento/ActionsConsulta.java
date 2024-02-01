/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.system.medical.mhs.dominios.app.acoes.agendamento;

import com.system.medical.mhs.dominios.RespostaDomain;
import com.system.medical.mhs.dominios.app.modelos.agendamento.AgendamentoConsulta;
import com.system.medical.mhs.dominios.app.modelos.agendamento.ConsultaAgendada;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import com.system.medical.mhs.dominios.app.interfaces.agendamento.IAgendamentoConsultasRepository;

/**
 *
 * @author Felipe
 */
public class ActionsConsulta {
    private IAgendamentoConsultasRepository consultasRepository;
    
    public ActionsConsulta(IAgendamentoConsultasRepository consultasRepository){
        this.consultasRepository = consultasRepository;
    }
    
    public RespostaDomain<ConsultaAgendada> agendar(AgendamentoConsulta agendamento){
    	System.out.println(agendamento);
        Long protocolo = generateProtocolo(agendamento);
        return this.consultasRepository.agendarConsulta(protocolo, agendamento);
    }
    
    private Long generateProtocolo(AgendamentoConsulta agendamento){
        SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMddHHmm");
        Calendar registered = Calendar.getInstance();
        registered.setTimeInMillis(agendamento.getDataHorario());
        
        Long regControl = new Long(fmt.format(registered.getTime()));
        
        Long protocolo = this.protocolo(
                regControl, agendamento.getIdPaciente());
    
        return protocolo;
    }
    
    private Long protocolo(Long... valores){
        String protStr = "";
        for(Long valor : valores){
            protStr += valor.toString();
        }
        
        return new Long(protStr);
    }
}
