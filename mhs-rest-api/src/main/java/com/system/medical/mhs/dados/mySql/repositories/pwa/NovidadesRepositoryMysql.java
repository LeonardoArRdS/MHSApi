package com.system.medical.mhs.dados.mySql.repositories.pwa;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import com.system.medical.mhs.dados.mySql.AbstractRepository;
import com.system.medical.mhs.dados.mySql.entities.Consulta;
import com.system.medical.mhs.dados.mySql.entities.NovidadeAcompanhamento;
import com.system.medical.mhs.dados.mySql.entities.ProgressaoPaciente;
import com.system.medical.mhs.dados.mySql.entities.ProtocoloConsulta;
import com.system.medical.mhs.dados.mySql.entities.log.LogConsulta;
import com.system.medical.mhs.dominios.RespostaDomain;
import com.system.medical.mhs.dominios.pwa.interfaces.dashboard.INovidadesRepository;
import com.system.medical.mhs.dominios.pwa.modelos.dashboard.Novidade;
import com.system.medical.mhs.dominios.pwa.modelos.dashboard.NovidadeConsulta;
import java.util.Calendar;

/**
*
* @author Leonardo Ariel
*/
@Repository
public class NovidadesRepositoryMysql extends AbstractRepository implements INovidadesRepository {
	@Override
	public RespostaDomain buscarNovidadesPacientes(Long crm, String uf) {
        Calendar now = Calendar.getInstance();
        now.add(Calendar.DAY_OF_MONTH, -2); // A partir de dois dias atrás
        String qNovidades = "from NovidadeAcompanhamento nov join fetch nov.acompanhamento ac " +
                "join fetch ac.crm crm where crm.valor = :crm and nov.realizacao >= :now order by nov.realizacao desc";
        
        String qProgressao = "from ProgressaoPaciente prog join fetch prog.acompanhamento ac " +
                "join fetch ac.crm crm where crm.valor = :crm and prog.realizacao >= :now order by prog.realizacao desc";
        
        //TODO
        String qConsultas = "from LogConsulta log"
                + " join fetch log.consulta c "
                + " join fetch c.protocoloConsulta pConsulta join fetch pConsulta.id.pessoa where pConsulta.id.crm.valor = :crm "
                + " and c.status.id = 4 and log.realizacao >= :now order by log.realizacao desc";
        
        Parametros parametros = new Parametros();
        parametros.put("crm", crm);
        parametros.put("now", now.getTime());
        
        List<NovidadeAcompanhamento> novidades =
                    this.selectByQuery(qNovidades, NovidadeAcompanhamento.class, parametros);
        
        List<ProgressaoPaciente> progressoes = 
                    this.selectByQuery(qProgressao, ProgressaoPaciente.class, parametros);
        
        List<LogConsulta> logs = 
                this.selectByQuery(qConsultas, LogConsulta.class, parametros);
//>>>>>>> 92cbf70cd7d5a0db25ee7d3117bbf9914266b14c
        
        List<Novidade> news = new ArrayList<>();
        
        news.addAll(tratarNovidades(novidades));
        news.addAll(tratarProgressoes(progressoes));
        news.addAll(tratarConsultas(logs));
        
        news.stream().sorted();
        
        return new RespostaDomain(news);
	}
        
        private List<Novidade> tratarNovidades(List<NovidadeAcompanhamento> novidades){
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
            return novidades.stream().map((novidade) ->{
                String tipo = novidade.getTipo().getTipo();		
				String titulo = "Novidade";
				String realizacao = sdf.format(novidade.getRealizacao());
				String msg = novidade.toString();
				
				return new Novidade(titulo, msg, novidade.getRealizacao().getTime(), realizacao, tipo);
            }).collect(Collectors.toList());
        }
        
        private List<Novidade> tratarProgressoes(List<ProgressaoPaciente> progressoes){
            
            return progressoes.stream().map((prog) -> {
                                        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
					String titulo = prog.getStatus().toString();
					String tipo = prog.getTipo().getTipo();
					String msg = prog.toString();
                                        String realizacao = sdf.format(prog.getRealizacao());
					
					return new Novidade(titulo, msg,  prog.getRealizacao().getTime(), realizacao, tipo);
            }).collect(Collectors.toList());
        }
        
        private List<Novidade> tratarConsultas(List<LogConsulta> consultas){
            SimpleDateFormat sdfData = new SimpleDateFormat("dd/MM/yyyy");
            SimpleDateFormat sdfHour = new SimpleDateFormat("HH:mm");
            
            SimpleDateFormat sdfNews = new SimpleDateFormat("dd/MM/yyyy HH:mm");
            List<Long> protocolos = 
                    consultas.stream().map(c -> c.getConsulta().getProtocolo()).collect(Collectors.toList());
//            List<ProtocoloConsulta> protocolosConsulta = 
//                    this.selectByQuery("from ProtocoloConsulta p join fetch p.consulta c join fetch "
//                            + "p.id.pessoa where c.protocolo in :protocolos", ProtocoloConsulta.class, new Parametros("protocolos", protocolos));
            
            return consultas.stream().map((c) -> {
                Consulta consulta = c.getConsulta();
               // ProtocoloConsulta pConsulta = protocolosConsulta.stream().filter(p -> p.getConsulta().equals(consulta)).findFirst().get();
                
                String msg = "solicitação de consulta em " 
                        + sdfData.format(consulta.getProtocoloConsulta().getDataCompleta()) 
                        + " às " + sdfHour.format(consulta.getProtocoloConsulta().getDataCompleta());
                
                String plano = consulta.getPlanoUtilizado() == null ? "Particular" : consulta.getPlanoUtilizado().getPlano().getNome();
                String realizacao = sdfNews.format(c.getRealizacao());
                return new NovidadeConsulta("Nova consulta", 
                        consulta.getProtocoloConsulta().getPessoa().getNomeInteiro(), plano, consulta.prontuarioCompartilhado(), msg, c.getRealizacao().getTime(), realizacao,  "Consulta");
            }).collect(Collectors.toList());
        }
}