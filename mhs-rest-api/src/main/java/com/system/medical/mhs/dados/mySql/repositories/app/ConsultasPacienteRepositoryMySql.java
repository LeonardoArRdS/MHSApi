/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.system.medical.mhs.dados.mySql.repositories.app;

import com.system.medical.mhs.dados.mySql.AbstractRepository;
import com.system.medical.mhs.dados.mySql.entities.ProtocoloConsulta;
import com.system.medical.mhs.dominios.RespostaDomain;
import com.system.medical.mhs.dominios.app.interfaces.IConsultasPacienteRepository;
import com.system.medical.mhs.dominios.app.modelos.ConsultasPaciente;

import java.util.List;
import java.util.stream.Collectors;
import javax.persistence.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Felipe
 */
@Repository
public class ConsultasPacienteRepositoryMySql extends AbstractRepository implements IConsultasPacienteRepository{

    @Override
    public RespostaDomain<List<ConsultasPaciente>> getConsultasFuturas(Long CPF, Integer rows) {
       return new RespostaDomain(this.obterConsultasPorStatus(CPF, new Long(1), rows));
    }
    
    private List<ConsultasPaciente> obterConsultasPorStatus(Long CPF,
            Long status, Integer quantidade) {
        String q = "select procto from ProtocoloConsulta procto join fetch procto.consulta cons join fetch cons.status status where procto.id.pessoa.cpf = :cpf and status.id = :idStatus";
        
        
        AbstractRepository.Parametros parametros = new AbstractRepository.Parametros();;
        parametros.put("idStatus", status);
        parametros.put("cpf", CPF);
        
        Query query = this.getByQuery(q, ProtocoloConsulta.class);
        if(quantidade != null && quantidade >= 1)
            query.setMaxResults(quantidade);
        
        query = this.appendParameters(query, parametros);
        List<ProtocoloConsulta> consultas = query.getResultList();
        //List<ProtocoloConsulta> consultas = this.selectByQuery(q, ProtocoloConsulta.class, parametros);

        return consultas.stream().map(
                (consulta) -> tratarConsulta(consulta)).collect(Collectors.toList());
    }
    
    private ConsultasPaciente tratarConsulta(ProtocoloConsulta consulta) {
        return new ConsultasPaciente(consulta.getConsulta().getProtocolo().longValue(), consulta.getConsulta().getEspecialidade().getNome(),
                consulta.getCRM().getPessoa().getNomeInteiro(), consulta.getData(),
                consulta.getHora(), consulta.getClinica().getEndereco().getRua());
    }
    
}
