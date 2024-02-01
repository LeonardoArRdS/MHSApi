/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.system.medical.mhs.dados.mySql.repositories.app;

import com.system.medical.mhs.dados.mySql.AbstractRepository;
import com.system.medical.mhs.dados.mySql.entities.Pessoa;
import com.system.medical.mhs.dados.mySql.entities.ProtocoloConsulta;
import com.system.medical.mhs.dados.mySql.entities.StatusConsulta;
import com.system.medical.mhs.dominios.IStatusConsultaRepository;
import org.springframework.stereotype.Repository;
import com.system.medical.mhs.dados.mySql.entities.log.LogConsulta;
import java.util.Calendar;

/**
 *
 * @author Felipe
 */
@Repository
public class StatusConsultaRepositoryMySql extends AbstractRepository implements IStatusConsultaRepository{
        
    @Override
    public LogConsulta updateStatusConsulta(ProtocoloConsulta protocolo, Pessoa solicitante, Long updateStatus) {
        LogConsulta log = new LogConsulta();
        
        log.setConsulta(protocolo.getConsulta());
        log.setRealizacao(Calendar.getInstance().getTime());
        log.setSolicitante(solicitante);
        
        StatusConsulta status = this.findById(StatusConsulta.class, updateStatus);
        log.setStatusAnterior(log.getStatusAtual());
        log.setStatusAtual(status);
        
        this.persist(log);
        return log;
    }
    
}
