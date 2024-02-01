/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.system.medical.mhs.dominios.app.acoes.agendamento;

import com.system.medical.mhs.dominios.RespostaDomain;
import com.system.medical.mhs.dominios.app.interfaces.agendamento.IAgendamentoConsultasRepository;
import com.system.medical.mhs.dominios.app.modelos.agendamento.ConfirmacaoCompartilhamento;
import com.system.medical.mhs.dominios.app.modelos.agendamento.SolicitacaoCompartilhamento;

/**
 *
 * @author Felipe
 */
public class ActionsCompartilhamentoProntuario {
   private IAgendamentoConsultasRepository agendamentoConsultasRepository;

    public ActionsCompartilhamentoProntuario(IAgendamentoConsultasRepository agendamentoConsultasRepository) {
        this.agendamentoConsultasRepository = agendamentoConsultasRepository;
    }
   
   public RespostaDomain<ConfirmacaoCompartilhamento> compartilhar(SolicitacaoCompartilhamento solicitacao){
       //TODO Calculo para fazer protocolo
       return this.agendamentoConsultasRepository.shareProntuarioConsulta(solicitacao);
   }
}
