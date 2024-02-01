/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.system.medical.mhs.dominios;

import com.system.medical.mhs.dados.mySql.entities.Pessoa;
import com.system.medical.mhs.dados.mySql.entities.ProtocoloConsulta;
import com.system.medical.mhs.dados.mySql.entities.log.LogConsulta;

/**
 *
 * @author Felipe
 */
public interface IStatusConsultaRepository {
    
    static Long AGUARDANDO_CONFIRMACAO_MEDICO = 4L;
    
    public LogConsulta updateStatusConsulta(ProtocoloConsulta protocolo, Pessoa solicitante, Long status);
}
