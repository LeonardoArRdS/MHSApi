package com.system.medical.mhs.dominios.pwa.interfaces;

import com.system.medical.mhs.dominios.RespostaDomain;
import com.system.medical.mhs.dominios.pwa.modelos.pacientes.home.SolicitacaoConsultaEmergencia;
import com.system.medical.mhs.dominios.pwa.modelos.pacientes.home.SolicitacaoConsultaImediata;

/**
 * @author Leonardo Ariel
 *
 */
public interface IPacientesHomeRepository {
	RespostaDomain buscarMeusPacientes(Long crm, String UF);
	RespostaDomain obterPacienteConsulta(Long crm, Long cpf, String UF);
        RespostaDomain iniciarConsultaEmergencia(Long protocolo, SolicitacaoConsultaEmergencia solicitacao);
        RespostaDomain iniciarConsulta(Long protocoloConsulta);
        RespostaDomain iniciarConsultaImediata(Long protocolo, SolicitacaoConsultaImediata solicitacao);
}
