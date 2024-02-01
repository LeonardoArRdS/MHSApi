/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.system.medical.mhs.dados.mySql.repositories.app;

import com.system.medical.mhs.dados.mySql.AbstractRepository;
import com.system.medical.mhs.dados.mySql.entities.CaracteristicaFisicaPessoa;
import com.system.medical.mhs.dados.mySql.entities.ComentarioConsulta;
import com.system.medical.mhs.dados.mySql.entities.Consulta;
import com.system.medical.mhs.dados.mySql.entities.Pessoa;
import com.system.medical.mhs.dados.mySql.entities.PlanoPessoa;
import com.system.medical.mhs.dominios.RespostaDomain;
import com.system.medical.mhs.dominios.app.interfaces.prontuario.IResumoProntuarioRepository;
import com.system.medical.mhs.dominios.app.modelos.prontuario.ResumoAlergias;
import com.system.medical.mhs.dominios.app.modelos.prontuario.ResumoConsulta;
import com.system.medical.mhs.dominios.app.modelos.prontuario.ResumoDadosPessoais;
import com.system.medical.mhs.dominios.app.modelos.prontuario.ResumoHistoricoDoencas;
import com.system.medical.mhs.dominios.app.modelos.prontuario.ResumoPlanoSaude;
import com.system.medical.mhs.dominios.app.modelos.prontuario.ResumoSaude;
import com.system.medical.mhs.dominios.app.modelos.prontuario.ResumoTratamentos;
import java.text.SimpleDateFormat;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Felipe
 */
@Repository
public class ResumoProntuarioRepositoryMySql extends AbstractRepository implements IResumoProntuarioRepository {

    @Override
    public RespostaDomain getDados(Long idPaciente) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Pessoa pessoa = this.findById(Pessoa.class, idPaciente);

        List<CaracteristicaFisicaPessoa> caracs = pessoa.getCaracteristicasFisicas();
        CaracteristicaFisicaPessoa altura = caracs.stream().filter((c) -> c.getId().getCaracteristica().getId().equals(1L)).findFirst().orElse(null);
        CaracteristicaFisicaPessoa peso = caracs.stream().filter((c) -> c.getId().getCaracteristica().getId().equals(2L)).findFirst().orElse(null);

        String strAltura = altura != null ? altura.toString() : "Vazio";
        String strPeso = peso != null ? peso.toString() : "Vazio";

        ResumoDadosPessoais dadosPessoais
                = new ResumoDadosPessoais(
                        pessoa.getNome(),
                        sdf.format(pessoa.getDataNascimento()),
                        strAltura,
                        pessoa.getRaca().toString(),
                        pessoa.getSexo().toString(),
                        strPeso,
                        pessoa.getNacionalidadeCompleta()
                );

        return new RespostaDomain(dadosPessoais);
    }

    @Override
    public RespostaDomain getPlanosSaude(Long idPaciente) {
        Pessoa pessoa = this.findById(Pessoa.class, idPaciente);
        List<PlanoPessoa> planos = pessoa.getPlanos();

        List<ResumoPlanoSaude> resumoPlanos = planos.stream().map((p) -> {
            return new ResumoPlanoSaude(p.getPlano().getNome(), p.getNumero());
        }).collect(Collectors.toList());

        ResumoSaude saude = new ResumoSaude(pessoa.getTipoSanguineo().getTipo(), pessoa.getDoadorOrgaos(), resumoPlanos);
        return new RespostaDomain(saude);
    }

    @Override
    public RespostaDomain getAlergias(Long idPaciente) {
        Pessoa pessoa = this.findById(Pessoa.class, idPaciente);

        List<String> medicamentos = pessoa.getAlergiasMedicamentos().stream().map(m -> m.getNome()).collect(Collectors.toList());
        List<String> outros = pessoa.getAlergias().stream().map(m -> m.getDescricao()).collect(Collectors.toList());

        ResumoAlergias alergias = new ResumoAlergias(medicamentos, outros);

        return new RespostaDomain(alergias);

    }

    @Override
    public RespostaDomain getTratamentos(Long idPaciente) {

        Pessoa pessoa = this.findById(Pessoa.class, idPaciente);

        List<ResumoTratamentos> tratamentos = pessoa.getTratamentos().stream().map((t) -> {
            String trat = t.getDescricao();
            String medicacoes = t.getMedicacoes().stream().map(m -> m.getMedicamento().getNome()).collect(Collectors.joining(","));

            return new ResumoTratamentos(trat, medicacoes);
        }).collect(Collectors.toList());

        return new RespostaDomain(tratamentos);
    }

    @Override
    public RespostaDomain getHistoricoDoencas(Long idPaciente) {
        Pessoa pessoa = this.findById(Pessoa.class, idPaciente);
        
        List<String> atuais = pessoa.getDoencas().stream().filter(
                (d) -> d.getStatus().getId().equals(1L)).map(d -> d.getDoenca().getNome()).collect(Collectors.toList());
        List<String> passadas =  pessoa.getDoencas().stream().filter(
                (d) -> d.getStatus().getId().equals(2L)).map(d -> d.getDoenca().getNome()).collect(Collectors.toList());
        
        
        Set<String> familia = new HashSet<>();
        pessoa.getFamiliares().stream().forEach((f) -> {
           familia.addAll(f.getDoencas().stream().map(d -> d.getNome()).collect(Collectors.toSet()));
        });

        ResumoHistoricoDoencas doencas = new ResumoHistoricoDoencas(atuais, passadas, familia);

        return new RespostaDomain(doencas);
    }

    @Override
    public RespostaDomain getConsultas(Long idPaciente) {

        Pessoa pessoa = this.findById(Pessoa.class, idPaciente);
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        
        List<ResumoConsulta> consultas = pessoa.getConsultas().stream().map((c) -> {
            Consulta consulta = c.getConsulta();
            String conclusao = consulta.getComentarios().stream().filter(
                    cons -> cons.getId().equals(2L)).findFirst().orElse(new ComentarioConsulta()).getComentario();
            
            return new ResumoConsulta(sdf.format(c.getId().getDataHora()), 
                    c.getConsulta().getEspecialidade().getNome(), conclusao);
        }).collect(Collectors.toList());

        return new RespostaDomain(consultas);
    }

}
