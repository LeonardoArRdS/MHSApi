/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.system.medical.mhs.dados.mySql.repositories.app;

import com.system.medical.mhs.dados.mySql.AbstractRepository;
import com.system.medical.mhs.dados.mySql.entities.ExamePessoa;
import com.system.medical.mhs.dominios.RespostaDomain;
import com.system.medical.mhs.dominios.app.interfaces.IExamesRepository;
import com.system.medical.mhs.dominios.app.modelos.ExamePaciente;

import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;
import javax.persistence.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Felipe
 */
@Repository
public class ExamesRepositoryMySql extends AbstractRepository implements IExamesRepository {

    @Override
    public RespostaDomain getExamesFuturos(Long CPF, Integer rows) {
        String q = "from ExamePessoa e join fetch e.pessoa p where p.cpf = :cpf and e.dataRealizacao >= :hoje order by e.dataRealizacao desc";
        Query query = this.getByQuery(q, ExamePessoa.class);

        Parametros parametros = new Parametros();
        parametros.put("cpf", CPF);
        parametros.put("hoje", Calendar.getInstance().getTime());

        if (rows != null && rows >= 1) {
            query.setMaxResults(rows);
        }
        query = this.appendParameters(query, parametros);
        List<ExamePessoa> exames = query.getResultList();
        List<ExamePaciente> examesPaciente = exames.stream().map((e) -> tratarExames(e)).collect(Collectors.toList());
    
        return new RespostaDomain(examesPaciente);
    }
    
    private ExamePaciente tratarExames(ExamePessoa exame){
        
        return new ExamePaciente(exame.getExame().getNome(), exame.getData());
    }
}
