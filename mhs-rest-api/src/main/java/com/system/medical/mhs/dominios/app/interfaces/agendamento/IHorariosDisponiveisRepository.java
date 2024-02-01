/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.system.medical.mhs.dominios.app.interfaces.agendamento;

import com.system.medical.mhs.dominios.RespostaDomain;
import java.util.Calendar;

/**
 *
 * @author Felipe
 */
public interface IHorariosDisponiveisRepository {
    RespostaDomain getHorariosDisponiveis(Long CRM, String UF);
    RespostaDomain getHorariosDisponiveisPorDia(Long CRM, String UF, Calendar dia, Long idEspecialidade);
}
