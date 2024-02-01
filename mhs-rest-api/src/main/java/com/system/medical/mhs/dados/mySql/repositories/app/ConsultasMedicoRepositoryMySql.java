/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.system.medical.mhs.dados.mySql.repositories.app;

import com.system.medical.mhs.dados.mySql.AbstractRepository;
import com.system.medical.mhs.dados.mySql.entities.Clinica;
import com.system.medical.mhs.dados.mySql.entities.ContratacaoEspecialidade;
import com.system.medical.mhs.dados.mySql.entities.IndisponibilidadeConsulta;
import com.system.medical.mhs.dados.mySql.entities.ProtocoloConsulta;
import com.system.medical.mhs.dominios.RespostaDomain;
import com.system.medical.mhs.dominios.app.modelos.agendamento.HorariosDisponiveis;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import org.springframework.stereotype.Repository;
import com.system.medical.mhs.dominios.app.interfaces.agendamento.IHorariosDisponiveisRepository;

/**
 *
 * @author Felipe
 */
@Repository
public class ConsultasMedicoRepositoryMySql extends AbstractRepository implements IHorariosDisponiveisRepository {

    @Override
    public RespostaDomain getHorariosDisponiveis(Long CRM, String UF) {
        Calendar now = Calendar.getInstance();
        Calendar endOfDay = Calendar.getInstance();
        endOfDay.set(Calendar.HOUR_OF_DAY, 23);

        Parametros parametrosAtendimento = new Parametros();
        parametrosAtendimento.put("crm", CRM);
        parametrosAtendimento.put("uf", UF.toUpperCase());
        String qPeriodoAtendimento = "from ContratacaoEspecialidade contr join fetch contr.id.contratacao c join fetch c.pessoa p where p.crm.valor = :crm and p.crm.estado.UF = :uf";

        Parametros parametrosConsulta = new Parametros();
        parametrosConsulta.put("inicio", now.getTime());
        parametrosConsulta.put("fim", endOfDay.getTime());
        parametrosConsulta.put("crm", CRM);
        parametrosConsulta.put("uf", UF.toUpperCase());
        String qConsultasDoDia = "from ProtocoloConsulta procto join fetch procto.id.crm crm where procto.id.dataHora >= :inicio and procto.id.dataHora <= :fim and crm.valor = :crm and crm.estado.UF = :uf";
        
        List<ProtocoloConsulta> consultas = this.selectByQuery(qConsultasDoDia, ProtocoloConsulta.class, parametrosConsulta);
        List<ContratacaoEspecialidade> contratacoes = this.selectByQuery(qPeriodoAtendimento, ContratacaoEspecialidade.class, parametrosAtendimento);
        
        String qIndisponibilidade = "from IndisponibilidadeConsulta ind where ind.contratacao in :contratacoes and ind.indisponibilidade between :inicio and :fim";
        Parametros parametrosIndisponibilidade = new Parametros();
        parametrosIndisponibilidade.put("contratacoes", contratacoes);
        parametrosIndisponibilidade.put("inicio", now.getTime());
        parametrosIndisponibilidade.put("fim", endOfDay.getTime());
        List<IndisponibilidadeConsulta> indisponiveis = this.selectByQuery(qIndisponibilidade, IndisponibilidadeConsulta.class, parametrosIndisponibilidade);

        
        
        List<HorariosDisponiveis> horariosDisponiveis
                = this.mountHorariosDisponiveis(contratacoes, consultas, indisponiveis, now.getTime());

        return new RespostaDomain(horariosDisponiveis);
    }

    @Override
    public RespostaDomain getHorariosDisponiveisPorDia(Long CRM, String UF, Calendar dia, Long idEspecialidade) {
        Calendar inicioDia = Calendar.getInstance();
        Calendar fimDia = Calendar.getInstance();

        inicioDia.setTimeInMillis(dia.getTimeInMillis());
        inicioDia.set(Calendar.HOUR_OF_DAY, 0);
        inicioDia.set(Calendar.MINUTE, 0);
        inicioDia.set(Calendar.SECOND, 0);

        fimDia.set(inicioDia.get(Calendar.YEAR), inicioDia.get(Calendar.MONTH), inicioDia.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
        fimDia.add(Calendar.DAY_OF_MONTH, 1);

        List<HorariosDisponiveis> horariosDisponiveis;
        Parametros parametrosAtendimento = new Parametros();
        parametrosAtendimento.put("crm", CRM);
        parametrosAtendimento.put("uf", UF.toUpperCase());
        parametrosAtendimento.put("idEspecialidade", idEspecialidade);
        String qPeriodoAtendimento = "from ContratacaoEspecialidade contr join fetch contr.id.contratacao c join fetch c.pessoa p join fetch contr.especialidade esp where esp.id = :idEspecialidade and p.crm.valor = :crm and p.crm.estado.UF = :uf";

        List<ContratacaoEspecialidade> contratacoes = this.selectByQuery(qPeriodoAtendimento, ContratacaoEspecialidade.class, parametrosAtendimento);

        Parametros parametrosConsulta = new Parametros();
        parametrosConsulta.put("inicio", inicioDia.getTime());
        parametrosConsulta.put("fim", fimDia.getTime());
        parametrosConsulta.put("crm", CRM);
        parametrosConsulta.put("uf", UF.toUpperCase());
        String qConsultasDoDia = "from ProtocoloConsulta procto join fetch procto.id.crm crm where procto.id.dataHora >= :inicio and procto.id.dataHora < :fim and crm.valor = :crm and crm.estado.UF = :uf";
        

        List<ProtocoloConsulta> consultas = this.selectByQuery(qConsultasDoDia, ProtocoloConsulta.class, parametrosConsulta);
        
        String qIndisponibilidade = "from IndisponibilidadeConsulta ind where ind.contratacao in :contratacoes and ind.indisponibilidade between :inicio and :fim";
        Parametros parametrosIndisponibilidade = new Parametros();
        parametrosIndisponibilidade.put("contratacoes", contratacoes);
        parametrosIndisponibilidade.put("inicio", inicioDia.getTime());
        parametrosIndisponibilidade.put("fim", fimDia.getTime());
        List<IndisponibilidadeConsulta> indisponiveis = this.selectByQuery(qIndisponibilidade, IndisponibilidadeConsulta.class, parametrosIndisponibilidade);

        horariosDisponiveis
                = this.mountHorariosDisponiveis(contratacoes, consultas, indisponiveis, inicioDia.getTime());
        
        RespostaDomain resposta = new RespostaDomain(horariosDisponiveis);
//        ConsultarHorariosDisponiveis response = new ConsultarHorariosDisponiveis();
//        response.setHorarios(horariosDisponiveis);
        return resposta;
    }
    
    private List<HorariosDisponiveis> mountHorariosDisponiveis(List<ContratacaoEspecialidade> contratacoes,
            List<ProtocoloConsulta> consultas, List<IndisponibilidadeConsulta> indisponibilidades, Date data) {
        List<HorariosDisponiveis> horariosDisponiveis = new ArrayList<>();
        for (ContratacaoEspecialidade contratacao : contratacoes) {     
            Time inicioAtendimento = new Time(contratacao.getInicioAtendimento().getTime());
            Time fimAtendimento = new Time(contratacao.getFimAtendimento().getTime());

            Clinica clinica = contratacao.getContratacao().getClinica();
            String localidade = String.valueOf(clinica.getEndereco());
            
            Time intervalo = new Time(contratacao.getIntervaloAtendimento().getTime());
            //intervalo.setTimeInMillis(contratacao.getIntervaloAtendimento().getTime());

            List<Time> times = buildListaHorario(inicioAtendimento, fimAtendimento, intervalo);
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
            
            horasDisponiveis:
            for (Time time : times) {
            	
                Iterator<ProtocoloConsulta> consultasI = consultas.iterator();

                String horaDisponivel = sdf.format(time);
                while (consultasI.hasNext()) {
                    ProtocoloConsulta c = consultasI.next();
                    Date dtConsulta = c.getDataCompleta();

                    String horaConsulta = sdf.format(dtConsulta);

                    if ((horaConsulta.equals(horaDisponivel))) {
                        consultasI.remove();
                        horariosDisponiveis.add(this.trataHorasDisponiveis(time, Boolean.FALSE, data, localidade, contratacao.getEspecialidade().getNome()));
                        continue horasDisponiveis;
                    }

                }

                Iterator<IndisponibilidadeConsulta> inds = indisponibilidades.stream().iterator();
                while (inds.hasNext()) {
                    IndisponibilidadeConsulta indisponibilidade = inds.next();
                    Date dtInd = indisponibilidade.getIndisponibilidade();

                    String horaIndisponibilidade = sdf.format(dtInd);
                    if ((horaIndisponibilidade.equals(horaDisponivel))) {
                        //inds.remove();
                        horariosDisponiveis.add(this.trataHorasDisponiveis(time, Boolean.FALSE, data, localidade, contratacao.getEspecialidade().getNome()));
                        continue horasDisponiveis;
                    }
                }
                

                horariosDisponiveis.add(this.trataHorasDisponiveis(time, Boolean.TRUE, data, localidade, contratacao.getEspecialidade().getNome()));
            }
        }
        
        return horariosDisponiveis;
    }

    private HorariosDisponiveis trataHorasDisponiveis(Time horario, Boolean disponivel, Date data, String localidade, String especialidade) {
        SimpleDateFormat sdf = new SimpleDateFormat("HH");
        SimpleDateFormat sdfHora = new SimpleDateFormat("HH:mm");
        SimpleDateFormat sdfData = new SimpleDateFormat("dd/MM/yyyy");
        String turno = "manha";
        Integer hora = new Integer(sdf.format(horario));

        if (hora >= 12 && hora < 18) {
            turno = "tarde";
        } else if (hora >= 18) {
            turno = "noturno";
        }

        return new HorariosDisponiveis(
                turno, sdfHora.format(horario), especialidade, disponivel, sdfData.format(data), localidade
        );
    }

    private List<Time> buildListaHorario(Time start, Time end, Time intervalo) {
        List<Time> intervals = new ArrayList<>();
//        Time start = new Time(inicio.getTimeInMillis());;
//        Time end = new Time(fim.getTimeInMillis());

        intervals.add(start);

        Calendar cl = Calendar.getInstance();
        cl.setTime(start);
        while (cl.getTime().before(end)) {
            Calendar clIntervalo = Calendar.getInstance();
            clIntervalo.setTimeInMillis(intervalo.getTime());
            cl.add(Calendar.MINUTE, clIntervalo.get(Calendar.MINUTE));
            intervals.add(new Time(cl.getTimeInMillis()));
        }

        return intervals;
    }

}
