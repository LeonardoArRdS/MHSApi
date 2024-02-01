/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.system.medical.mhs.dominios.app.interfaces.agendamento;

import com.system.medical.mhs.dominios.RespostaDomain;

/**
 *
 * @author Felipe Mariano
 */
public interface IMedicosDisponiveisRepository {
    RespostaDomain getMedicosPorEspecialidade(Long idEspecialidade);
    RespostaDomain getMedicosNearBy(Long latitude, Long longitude);
    RespostaDomain getMedicos(Long idPlano, Long idEspecialidade, Long idRegiao);
    RespostaDomain getMedicosByRangePrecos(Double inferior, Double superior);
    RespostaDomain getMedicosByPlano(Long idPlano);
    RespostaDomain getMedicosByDisponibilidade(Integer dias);
    RespostaDomain getMedicosBySexo(Long idSexo);
    RespostaDomain getDetalhe(Long CRM, String UF, Long idEspecialidade);
}
