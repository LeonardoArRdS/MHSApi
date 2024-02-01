/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.system.medical.mhs.dominios.app.interfaces.agendamento;

import com.system.medical.mhs.dominios.RespostaDomain;
import com.system.medical.mhs.dominios.app.modelos.agendamento.AgendamentoConsulta;
import com.system.medical.mhs.dominios.app.modelos.agendamento.SolicitacaoCompartilhamento;

/**
 *
 * @author Felipe
 */
public interface IAgendamentoConsultasRepository {
    RespostaDomain agendarConsulta(Long protocolo, AgendamentoConsulta agendamento);
    RespostaDomain shareProntuarioConsulta(SolicitacaoCompartilhamento solicitacao);
}
