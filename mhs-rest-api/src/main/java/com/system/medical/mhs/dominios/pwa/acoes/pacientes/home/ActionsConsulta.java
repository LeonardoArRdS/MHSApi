/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.system.medical.mhs.dominios.pwa.acoes.pacientes.home;

import com.system.medical.mhs.dominios.RespostaDomain;
import com.system.medical.mhs.dominios.pwa.interfaces.IPacientesHomeRepository;
import com.system.medical.mhs.dominios.pwa.modelos.pacientes.home.ConfirmacaoConsultaImediata;
import com.system.medical.mhs.dominios.pwa.modelos.pacientes.home.ConfirmacaoInicioConsulta;
import com.system.medical.mhs.dominios.pwa.modelos.pacientes.home.SolicitacaoConsultaEmergencia;
import com.system.medical.mhs.dominios.pwa.modelos.pacientes.home.SolicitacaoConsultaImediata;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 *
 * @author Felipe
 */
public class ActionsConsulta {

    private IPacientesHomeRepository pacientesHomeRepository;

    public ActionsConsulta(IPacientesHomeRepository pacientesHomeRepository) {
        this.pacientesHomeRepository = pacientesHomeRepository;
    }

    public RespostaDomain iniciarConsultaEmergencia(SolicitacaoConsultaEmergencia solicitacao){
        Long protocolo = generateProtocolo(0001L, Calendar.getInstance());
        return this.pacientesHomeRepository.iniciarConsultaEmergencia(protocolo, solicitacao);
    }
    
    public RespostaDomain<ConfirmacaoInicioConsulta> iniciarConsulta(Long protocolo){
        //TODO validar o protocolo com o request do médico!
        return this.pacientesHomeRepository.iniciarConsulta(protocolo);
    }
    
    public RespostaDomain<ConfirmacaoConsultaImediata> iniciarConsultaImediata(SolicitacaoConsultaImediata solicitacao){
        Calendar now = Calendar.getInstance();
        Long protocolo = this.generateProtocolo(2L, now);
        return this.pacientesHomeRepository.iniciarConsultaImediata(protocolo, solicitacao);
    }

    private Long generateProtocolo(Long idPaciente, Calendar horario) {
        SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMddHHmm");
        Long regControl = new Long(fmt.format(horario.getTime()));

        Long protocolo = this.protocolo(
                regControl, idPaciente);

        return protocolo;
    }

    private Long protocolo(Long... valores) {
        String protStr = "";
        for (Long valor : valores) {
            protStr += valor.toString();
        }

        return new Long(protStr);
    }
}
