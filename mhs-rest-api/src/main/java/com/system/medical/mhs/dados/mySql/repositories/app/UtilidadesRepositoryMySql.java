/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.system.medical.mhs.dados.mySql.repositories.app;

import com.system.medical.mhs.dados.mySql.AbstractRepository;
import com.system.medical.mhs.dados.mySql.entities.Especialidade;
import com.system.medical.mhs.dados.mySql.entities.Plano;
import com.system.medical.mhs.dados.mySql.entities.ZonaRegiao;
import com.system.medical.mhs.dominios.RespostaDomain;
import com.system.medical.mhs.dominios.app.interfaces.forms.IUtilidadesRepository;
import com.system.medical.mhs.dominios.app.modelos.utilidades.ModelEspecialidade;
import com.system.medical.mhs.dominios.app.modelos.utilidades.ModelPlano;
import com.system.medical.mhs.dominios.app.modelos.utilidades.ModelRegiao;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Felipe
 */
@Repository
public class UtilidadesRepositoryMySql  extends AbstractRepository implements IUtilidadesRepository{

    @Override
    public RespostaDomain getEspecialidade() {
        List<Especialidade> esps = this.selectAll(Especialidade.class);
        List<ModelEspecialidade> especialidades = esps.stream().map((e) -> {
            return new ModelEspecialidade(e.getId(), e.getNome());
        }).collect(Collectors.toList());
        
        return new RespostaDomain(especialidades);
    }

    @Override
    public RespostaDomain getRegioes() {
        List<ZonaRegiao> regs = this.selectAll(ZonaRegiao.class);
        List<ModelRegiao> regioes = regs.stream().map((r) -> {
            return new ModelRegiao(r.getId(), r.getNome());
        }).collect(Collectors.toList());
        
        return new RespostaDomain(regioes);
    }

    @Override
    public RespostaDomain getPlanos() {
        List<Plano> pls = this.selectAll(Plano.class);
        List<ModelPlano> planos = pls.stream().map((p) -> {
            return new ModelPlano(p.getId(), p.getNome(), p.getConvenio().getNome());
        }).collect(Collectors.toList());
        
        return new RespostaDomain(planos);
    }
    
}
