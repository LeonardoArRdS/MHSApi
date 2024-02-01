/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.system.medical.mhs.dominios.app.interfaces.prontuario;

import com.system.medical.mhs.dominios.RespostaDomain;

/**
 *
 * @author Felipe
 */
public interface IResumoProntuarioRepository {
    RespostaDomain getDados(Long idPaciente);
    RespostaDomain getPlanosSaude(Long idPaciente);
    RespostaDomain getAlergias(Long idPaciente);
    RespostaDomain getTratamentos(Long idPaciente);
    RespostaDomain getHistoricoDoencas(Long idPaciente);
    RespostaDomain getConsultas(Long idPaciente);
}
