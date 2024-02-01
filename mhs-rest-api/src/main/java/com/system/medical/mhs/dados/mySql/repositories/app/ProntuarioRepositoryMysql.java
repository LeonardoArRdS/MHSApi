package com.system.medical.mhs.dados.mySql.repositories.app;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.joda.time.DateTime;
import org.joda.time.Interval;
import org.springframework.stereotype.Repository;

import com.system.medical.mhs.dados.mySql.AbstractRepository;
import com.system.medical.mhs.dados.mySql.AbstractRepository.Parametros;
import com.system.medical.mhs.dados.mySql.entities.ComentarioConsulta;
import com.system.medical.mhs.dados.mySql.entities.Pessoa;
import com.system.medical.mhs.dados.mySql.entities.SintomaConsulta;
import com.system.medical.mhs.dados.mySql.entities.TratamentoPessoa;
import com.system.medical.mhs.dominios.RespostaDomain;
import com.system.medical.mhs.dominios.app.interfaces.IProntuarioRepository;
import com.system.medical.mhs.dominios.app.modelos.prontuario.ConsultasRecentes;
import com.system.medical.mhs.dominios.app.modelos.prontuario.ConsultasRecentesCompleto;
import com.system.medical.mhs.dominios.pwa.modelos.MedicacaoTratamento;
import com.system.medical.mhs.dominios.pwa.modelos.Tratamento;

/**
 * @author Leonardo Ariel
 *
 */
@Repository
public class ProntuarioRepositoryMysql extends AbstractRepository implements IProntuarioRepository {
	@Override
	public RespostaDomain consultarConsultasRecentes(Long cpf) {
		RespostaDomain resposta;
        String query = "from Pessoa p "
                + "where p.cpf = :cpf";
        
        Pessoa pessoa;
        try {
            pessoa = this.getSingleResult(query, Pessoa.class, new Parametros("cpf", cpf));
        } catch (Exception e) {
            resposta = new RespostaDomain(null);
            resposta.adicionarMensagemErro(e.toString());
            return resposta;
        }
        List<ConsultasRecentes> consultas = pessoa.getConsultas().stream()
                .map(
                        (consulta) -> {
                            List<String> anormalidades = consulta.getConsulta().getAnormalidades().stream().map(
                                    (a) -> a.getDescricao()).collect(Collectors.toList());
                            return new ConsultasRecentes(
                                consulta.getConsulta().getProtocoloConsulta().getDataCompleta(),
                                consulta.getConsulta().getEspecialidade().getNome(),
                                anormalidades
                            );
                        }
                ).collect(Collectors.toList());

        resposta = new RespostaDomain(consultas);
		
		return resposta;
	}

	@Override
	public RespostaDomain consultarConsultasRecentesCompleto(Long cpf) {
		RespostaDomain resposta;
        String query = "from Pessoa p "
                + "where p.cpf = :cpf";

        Pessoa pessoa;
        try {
            pessoa = this.getSingleResult(query, Pessoa.class, new Parametros("cpf", cpf));
        } catch (Exception e) {
            resposta = new RespostaDomain(null);
            resposta.adicionarMensagemErro(e.toString());
            return resposta;
        }

        ArrayList<String> sintomas = new ArrayList<String>();
        ArrayList<Tratamento> tratamentos = new ArrayList<Tratamento>();
        List<ConsultasRecentesCompleto> consultas = pessoa.getConsultas().stream().map(
            (consulta) -> {
                for (SintomaConsulta sintoma : consulta.getConsulta().getSintomas()) {
                    sintomas.add(sintoma.getSintoma().getNome());
                }
                for (TratamentoPessoa tratamento : pessoa.getTratamentos()) {

                    DateTime i = new DateTime(tratamento.getInicio());
                    DateTime f = new DateTime(tratamento.getTermino());
                    Interval interval = new Interval(i, f);
                    String periodType = "dias";
                    Integer dias = interval.toPeriod().getDays();
                    if (dias < 1) {
                        dias = interval.toPeriod().getHours();
                        if (dias < 1) {
                            periodType = "minutos";
                        } else {
                            periodType = "horas";
                        }
                    }

                    String diasExt = dias + " " + periodType;

                    List<MedicacaoTratamento> meds = tratamento.getMedicacoes().stream().map((m) -> {
                        return new MedicacaoTratamento(m.getMedicamento().getNome(), m.getDoseCompleta(), m.getFrequencia().getNome());
                    }).collect(Collectors.toList());

                    tratamentos.add(
                        new Tratamento(
                            tratamento.getTratamentoDoenca().getTratamento().getNome(),
                            meds,
                            diasExt,
                            tratamento.getFrequencia().getNome()
                        )
                    );
                }
                    //TODO Comentários exames?????
                    ComentarioConsulta conclusoes = consulta.getConsulta().getComentarios().stream().findFirst().orElse(new ComentarioConsulta());
                    ComentarioConsulta comentariosGerais = consulta.getConsulta().getComentarios().stream().findFirst().orElse(new ComentarioConsulta());
                    
                    return new ConsultasRecentesCompleto(
                            consulta.getConsulta().getProtocoloConsulta().getDataCompleta(),
                            consulta.getConsulta().getEspecialidade().getNome(),
                            conclusoes.getComentario(),//conclusoes
                            sintomas,
                            tratamentos,
                            "Exames apresentam alterações nas taxas de homoglobina e "
                            + "variações grandes nos valores de colesterol HCL, "
                            + "HDL e nos níveis de oxigênio intra-celular também "
                            + "afetando na respiração e perfomance atlética do paciente",//Comentários dos exames
                            comentariosGerais.getComentario() //gerais
                    );
                }).collect(Collectors.toList());
                            
    
    	resposta = new RespostaDomain(consultas);
    	return resposta;
	}
	
}
