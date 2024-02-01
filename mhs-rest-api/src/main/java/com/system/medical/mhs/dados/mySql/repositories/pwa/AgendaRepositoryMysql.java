package com.system.medical.mhs.dados.mySql.repositories.pwa;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import com.system.medical.mhs.dados.mySql.AbstractRepository;
import com.system.medical.mhs.dados.mySql.AbstractRepository.Parametros;
import com.system.medical.mhs.dados.mySql.entities.Alergia;
import com.system.medical.mhs.dados.mySql.entities.CRM;
import com.system.medical.mhs.dados.mySql.entities.CaracteristicaFisicaPessoa;
import com.system.medical.mhs.dados.mySql.entities.Clinica;
import com.system.medical.mhs.dados.mySql.entities.ClinicaEspecialidade;
import com.system.medical.mhs.dados.mySql.entities.Consulta;
import com.system.medical.mhs.dados.mySql.entities.Contratacao;
import com.system.medical.mhs.dados.mySql.entities.ContratacaoEspecialidade;
import com.system.medical.mhs.dados.mySql.entities.DoencaPessoa;
import com.system.medical.mhs.dados.mySql.entities.Especialidade;
import com.system.medical.mhs.dados.mySql.entities.IdProtocoloConsulta;
import com.system.medical.mhs.dados.mySql.entities.IndisponibilidadeConsulta;
import com.system.medical.mhs.dados.mySql.entities.Medicacao;
import com.system.medical.mhs.dados.mySql.entities.Medicamento;
import com.system.medical.mhs.dados.mySql.entities.Pessoa;
import com.system.medical.mhs.dados.mySql.entities.PlanoPessoa;
import com.system.medical.mhs.dados.mySql.entities.ProtocoloConsulta;
import com.system.medical.mhs.dados.mySql.entities.StatusConsulta;
import com.system.medical.mhs.dados.mySql.entities.StatusConsultaGeral;
import com.system.medical.mhs.dados.mySql.entities.StatusGeralConsultaPessoa;
import com.system.medical.mhs.dados.mySql.entities.TipoConsulta;
import com.system.medical.mhs.dominios.RespostaDomain;
import com.system.medical.mhs.dominios.app.modelos.agendamento.ConsultaAgendada;
import com.system.medical.mhs.dominios.pwa.interfaces.IAgendaRepository;
import com.system.medical.mhs.dominios.pwa.modelos.agenda.DadosConsulta;
import com.system.medical.mhs.dominios.pwa.modelos.PlanosPessoa;
import com.system.medical.mhs.dominios.pwa.modelos.agenda.AgendarConsulta;
import com.system.medical.mhs.dominios.pwa.modelos.agenda.ConfirmacaoAlteracao;
import com.system.medical.mhs.dominios.pwa.modelos.agenda.ConsultaAgenda;
import com.system.medical.mhs.dominios.pwa.modelos.agenda.ConsultaSemana;
import com.system.medical.mhs.dominios.pwa.modelos.agenda.ConsultasRecentes;
import com.system.medical.mhs.dominios.pwa.modelos.agenda.Dados;
import com.system.medical.mhs.dominios.pwa.modelos.agenda.DisponibilidadeAgenda;
import com.system.medical.mhs.dominios.pwa.modelos.agenda.HistoricoDoencas;
import com.system.medical.mhs.dominios.pwa.modelos.agenda.HorarioIndisponivel;
import com.system.medical.mhs.dominios.pwa.modelos.agenda.MedicoEspecialidade;
import com.system.medical.mhs.dominios.pwa.modelos.agenda.MedicosClinica;
import com.system.medical.mhs.dominios.pwa.modelos.agenda.PlanoSaude;
import com.system.medical.mhs.dominios.pwa.modelos.agenda.PlanosClinica;
import com.system.medical.mhs.dominios.pwa.modelos.agenda.SolicitacaoAlteracaoHorarioConsulta;
import com.system.medical.mhs.dominios.pwa.modelos.agenda.Tratamentos;
import com.system.medical.mhs.dominios.pwa.modelos.pacientes.AlergiasConhecidas;
import com.system.medical.mhs.dominios.pwa.modelos.pacientes.ExamesRecentes;
import com.system.medical.mhs.utils.Imagem;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;
import javax.persistence.Query;
import javax.transaction.Transactional;
import org.joda.time.DateTime;
import org.joda.time.Minutes;

/**
 * @author Leonardo Ariel
 *
 */
@Repository
public class AgendaRepositoryMysql extends AbstractRepository implements IAgendaRepository {

    public RespostaDomain obterDados(Long cpf) {
        RespostaDomain resposta;
        String query = "select p from Pessoa p "
        			 + "where p.cpf = :cpf";
        
        Parametros parametros = new Parametros();
        parametros.put("cpf", cpf);

        Pessoa pessoa;
        try {
            pessoa = this.getSingleResult(query, Pessoa.class, parametros);
        } catch (Exception e) {
            resposta = new RespostaDomain(null);
            resposta.adicionarMensagemErro("Paciente não encontrado para o cpf:" + cpf);
            resposta.adicionarMensagemErro(e.getMessage());
            System.out.println(e.getMessage());
            return resposta;
        }
        
        CaracteristicaFisicaPessoa altura = pessoa.getCaracteristicasFisicas().stream().filter((caract) -> {
            return caract.getCaracteristica().getId().equals(1L);
        }).findFirst().orElse(null);

        CaracteristicaFisicaPessoa peso = pessoa.getCaracteristicasFisicas().stream().filter((caract) -> {
            return caract.getCaracteristica().getId().equals(2L);
        }).findFirst().orElse(null);

        resposta = new RespostaDomain(
            new Dados(
                new Imagem(pessoa.getFoto()).converterImagemBase64(),
                pessoa.getNomeInteiro(),
                pessoa.getDataNascimento(),
                altura.getValor(),
                pessoa.getRaca().getNome(),
                pessoa.getSexo().getNome(),
                peso.getValor(),
                pessoa.getNacionalidade().getDescricao()
            )
        );
        return resposta;
    }

    public RespostaDomain obterPlanoSaude(Long cpf) {
        RespostaDomain resposta;
        String query = "select p from Pessoa p where p.cpf = :cpf";
        Parametros parametros = new Parametros();
        parametros.put("cpf", cpf);

        Pessoa pessoa;
        try {
            pessoa = this.getSingleResult(query, Pessoa.class, parametros);
        } catch (Exception e) {
            resposta = new RespostaDomain(null);
            resposta.adicionarMensagemErro("Paciente não encontrado para o cpf:" + cpf);
            resposta.adicionarMensagemErro(e.getMessage());
            return resposta;
        }
        String plano;
        Long carteirinha;
        if (pessoa.getPlanos().stream().collect(Collectors.toList()).size() > 0) {
            plano = pessoa.getPlanos().stream().collect(Collectors.toList()).get(0).getPlano().getConvenio().getNome();
            carteirinha = pessoa.getPlanos().stream().collect(Collectors.toList()).get(0).getNumero();
        } else {
            plano = "Nenhum";
            carteirinha = (long) 0;
        }
        
        resposta = new RespostaDomain(
                new PlanoSaude(
                        pessoa.getTipoSanguineo().getTipo(),
                        plano,
                        carteirinha,
                        pessoa.getDoadorOrgaos())
        		);

        return resposta;
    }

    public RespostaDomain consultarAlergias(Long cpf) {
        RespostaDomain resposta;
        String query = "from Pessoa p where p.cpf = :cpf";
        Parametros parametros = new Parametros("cpf", cpf);

        Pessoa pessoa;
        try {
            pessoa = this.getSingleResult(query, Pessoa.class, parametros);
        } catch (Exception e) {
            resposta = new RespostaDomain(null);
            resposta.adicionarMensagemErro("Paciente não encontrado para o cpf:" + cpf);
            resposta.adicionarMensagemErro(e.getMessage());
            return resposta;
        }
        ArrayList<String> medicamentos = new ArrayList<String>();
        ArrayList<String> outras = new ArrayList<String>();
        
        for(Medicamento medicamento : pessoa.getAlergiasMedicamentos()){
        	medicamentos.add(medicamento.getNome());
        }
        for(Alergia alergia : pessoa.getAlergias()){
        	outras.add(alergia.getDescricao());
        }
        resposta = new RespostaDomain(
    		new AlergiasConhecidas(
				medicamentos, 
				outras
    		)
        );
        
        return resposta;
    }

    public RespostaDomain consultarTratamentos(Long cpf) {
        RespostaDomain resposta;
        String query = "from Medicacao mdc "
                + "join fetch mdc.pessoa p "
                + "where p.cpf = :cpf and "
                + "mdc.frequencia = (select max(m.frequencia) from Medicacao m where m.frequencia = mdc.frequencia)";;

        Medicacao medicacao;
        /*
        try {
            medicacao = this.getSingleResult(query, Medicacao.class, new Parametros("cpf", cpf));
        } catch (Exception e) {
            resposta = new RespostaDomain(null);
            resposta.adicionarMensagemErro(e.toString());
            return resposta;
        }
        */
        //TODO ESPERAR NOVA TABELA DE TRATAMENTOS
        resposta = new RespostaDomain(
            new Tratamentos(
                "Puran T4",//medicacao.getMedicamento().getNome(),//Correção de erro temporária
                "Moderado"
            )
        );

        return resposta;
    }

    public RespostaDomain consultarHistorico(Long cpf) {
        RespostaDomain resposta;
//        String query = "from DoencaPessoa dPessoa "
//                + "join fetch dPessoa.pessoa p "
//                + "join fetch dPessoa.doenca d "
//                + "where p.cpf = :cpf";
        String query = "from Pessoa p where p.cpf = :cpf";
        //List<DoencaPessoa> doencasPessoa;
        Pessoa pessoa;
        try {
            pessoa = this.getSingleResult(query, Pessoa.class, new Parametros("cpf", cpf));
        } catch (Exception e) {
            resposta = new RespostaDomain(null);
            resposta.adicionarMensagemErro("Paciente não encontrado para o cpf:" + cpf);
            resposta.adicionarMensagemErro(e.getMessage());
            return resposta;
        }
        /*
        //ARRUMAR ERRO MHS.status_doenca
        Set<DoencaPessoa> doencas = pessoa.getDoencas();
        List<String> doencasAtuais = doencas.stream().filter(
                (d) -> d.getStatus().getId().equals(1L)).map((d) -> d.getDoenca().getNome()).collect(Collectors.toList()); // DOENCA ATIVA
        
        List<String> doencasPassadas = doencas.stream().filter(
                (d) -> d.getStatus().getId().equals(2L)).map((d) -> d.getDoenca().getNome()).collect(Collectors.toList()); // DOENCA CURADA
        
        List<String> doencasFamiliares = new ArrayList<>();
        pessoa.getFamiliares().stream().forEach((f) -> {
            List<String> ds = f.getDoencas().stream().map((d) -> d.getNome()).collect(Collectors.toList());            
            doencasFamiliares.addAll(ds);
        });
        */
        List<String> doencasAtuais = new ArrayList<String>();
        List<String> doencasPassadas = new ArrayList<String>();
        doencasPassadas.add("Faringite");
        List<String> doencasFamiliares = new ArrayList<String>();
        doencasFamiliares.add("AVC");
        doencasFamiliares.add("Pressão Alta");
        resposta = new RespostaDomain(new HistoricoDoencas(doencasAtuais, doencasPassadas, doencasFamiliares));

        return resposta;
    }

    public RespostaDomain consultarExamesRecentes(Long cpf) {
        RespostaDomain resposta;
        String query = "from Pessoa p where p.cpf = :cpf";

        Pessoa pessoa;
        try {
            pessoa = this.getSingleResult(query, Pessoa.class, new Parametros("cpf", cpf));
        } catch (Exception e) {
            resposta = new RespostaDomain(null);
            resposta.adicionarMensagemErro(e.toString());
            return resposta;
        }
        List<ExamesRecentes> exames = pessoa.getExames().stream()
                .map(
                    (exame) -> {
                        return new ExamesRecentes(
                            exame.getDataRealizacao(),
                            exame.getExame().getNome(),
                            exame.getAnormalidade()//""
                        );
                    }
                ).collect(Collectors.toList());

        resposta = new RespostaDomain(exames);
        return resposta;
    }

    public RespostaDomain consultarConsultasRecentes(Long cpf) {
        RespostaDomain resposta;
        String query = "from Pessoa p where p.cpf = :cpf";

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
                            List<String> anorms = consulta.getConsulta().getAnormalidades().stream().map(
                                    (a) -> a.getDescricao()).collect(Collectors.toList());
                            return new ConsultasRecentes(
                                    consulta.getDataCompleta(),
                                    consulta.getConsulta().getEspecialidade().getNome(),
                                    anorms
                            );
                        }
                ).collect(Collectors.toList());

        resposta = new RespostaDomain(consultas);
        return resposta;
    }

    @Override
    public RespostaDomain obterDisponibilidade(Long crm, String uf, Calendar dia) {
        Calendar inicioDia = Calendar.getInstance();
        Calendar fimDia = Calendar.getInstance();

        inicioDia.setTimeInMillis(dia.getTimeInMillis());
        inicioDia.set(Calendar.HOUR_OF_DAY, 0);
        inicioDia.set(Calendar.MINUTE, 0);
        inicioDia.set(Calendar.SECOND, 0);

        fimDia.set(inicioDia.get(Calendar.YEAR), inicioDia.get(Calendar.MONTH), inicioDia.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
        fimDia.add(Calendar.DAY_OF_MONTH, 1);

        List<DisponibilidadeAgenda> agenda;
        Parametros parametrosAtendimento = new Parametros("crm", crm);
        String qPeriodoAtendimento = "from ContratacaoEspecialidade contr join fetch contr.id.contratacao c join fetch c.pessoa p where p.crm.valor = :crm";

        List<ContratacaoEspecialidade> contratacoes = this.selectByQuery(qPeriodoAtendimento, ContratacaoEspecialidade.class, parametrosAtendimento);

        Parametros parametrosConsulta = new Parametros();
        parametrosConsulta.put("inicio", inicioDia.getTime());
        parametrosConsulta.put("fim", fimDia.getTime());
        parametrosConsulta.put("crm", crm);
        String qConsultasDoDia = "from ProtocoloConsulta procto where procto.id.dataHora >= :inicio and procto.id.dataHora < :fim and procto.id.crm.valor = :crm";
        String qIndisponibilidade = "from IndisponibilidadeConsulta ind where ind.contratacao in :contratacoes and ind.indisponibilidade between :inicio and :fim";
        List<ProtocoloConsulta> consultas = this.selectByQuery(qConsultasDoDia, ProtocoloConsulta.class, parametrosConsulta);

        Parametros parametrosIndisponibilidade = new Parametros();
        parametrosIndisponibilidade.put("contratacoes", contratacoes);
        parametrosIndisponibilidade.put("inicio", inicioDia.getTime());
        parametrosIndisponibilidade.put("fim", fimDia.getTime());
        List<IndisponibilidadeConsulta> indisponiveis = this.selectByQuery(qIndisponibilidade, IndisponibilidadeConsulta.class, parametrosIndisponibilidade);

        agenda = this.mountHorariosDisponiveis(contratacoes, consultas, indisponiveis, inicioDia.getTime());
        RespostaDomain resposta = new RespostaDomain(agenda);
        return resposta;
    }

    private List<DisponibilidadeAgenda> mountHorariosDisponiveis(List<ContratacaoEspecialidade> contratacoes,
            List<ProtocoloConsulta> consultas, List<IndisponibilidadeConsulta> indisponibilidades, Date data) {
        List<DisponibilidadeAgenda> horariosDisponiveis = new ArrayList<>();

        for (ContratacaoEspecialidade contratacao : contratacoes) {
            Time inicioAtendimento = new Time(contratacao.getInicioAtendimento().getTime());
            Time fimAtendimento = new Time(contratacao.getFimAtendimento().getTime());

            Time intervalo = new Time(contratacao.getIntervaloAtendimento().getTime());

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
                        horariosDisponiveis.add(this.trataDisponibilidade(time, data, "consulta"));
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
                        horariosDisponiveis.add(this.trataDisponibilidade(time, data, "indisponivel"));
                        continue horasDisponiveis;
                    }
                }

                horariosDisponiveis.add(this.trataDisponibilidade(time, data, "disponivel"));
            }
        }

        return horariosDisponiveis.stream().sorted((t1, t2) -> t1.getData().compareTo(t2.getData())).collect(Collectors.toList());
    }

    private List<Time> buildListaHorario(Time start, Time end, Time intervalo) {
        List<Time> intervals = new ArrayList<>();

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

    private DisponibilidadeAgenda trataDisponibilidade(Time horario, Date data, String disponibilidade) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat sdfHora = new SimpleDateFormat("HH:mm");

        String dataStr = sdf.format(data) + " " + sdfHora.format(horario);

        return new DisponibilidadeAgenda(
                data.getTime(), dataStr, disponibilidade
        );
    }

    @Override
    @Transactional
    public RespostaDomain indisponibilizar(HorarioIndisponivel indisponibilidade, Pessoa solicitante) {
        Pessoa s = this.findById(Pessoa.class, solicitante.getId());
        String q = "from ContratacaoEspecialidade contr join fetch contr.id.contratacao c join fetch c.pessoa p where p.crm.valor = :crm and p.crm.estado.UF = :uf";
        Parametros parametros = new Parametros();
        parametros.put("crm", indisponibilidade.getCrm());
        parametros.put("uf", indisponibilidade.getUf().toUpperCase());

        List<DisponibilidadeAgenda> indisponibilidades = indisponibilidade.getHorarios().stream().map((horario) -> {
            ContratacaoEspecialidade contratacao = new ContratacaoEspecialidade();
            Date dt = new Date(horario);
            Time time = new Time(horario);

            try {
                List<ContratacaoEspecialidade> contrs = this.selectByQuery(q, ContratacaoEspecialidade.class, parametros);
                contratacao = contrs.stream().filter((c) -> {
                    Time tIni = new Time(c.getInicioAtendimento().getTime());
                    Time tFim = new Time(c.getFimAtendimento().getTime());

                    if (time.compareTo(tIni) < 0 || time.compareTo(tFim) > 1) {
                        return false;
                    }

                    return true;
                }).findFirst().orElse(null);

                if (contratacao == null) {
                    throw new Exception("Contratacao não encontrada!");
                }
            } catch (Exception e) {
                return new DisponibilidadeAgenda(horario, "-", "erro: Horário não condiz com o período de trabalho do médico");
            }

            IndisponibilidadeConsulta ind = new IndisponibilidadeConsulta();
            ind.setContratacao(contratacao);
            ind.setSolicitante(s);
            ind.setCriacao(new Date());

            ind.setIndisponibilidade(dt);
            this.getEntityManager().persist(ind);

            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
            String dtStr = sdf.format(dt);

            return new DisponibilidadeAgenda(horario, dtStr, "indisponivel");

        }).collect(Collectors.toList());

        return new RespostaDomain(indisponibilidades);
    }

    @Override
    public RespostaDomain obterConsultasDia(Long crm, String uf, Long dia) {
        String q = "from ProtocoloConsulta proct "
        		+ "join fetch proct.id.crm crm "
        		+ "join fetch proct.id.pessoa "
        		+ "where crm.valor = :crm and "
        		+ "crm.estado.UF = :uf and "
        		+ "proct.id.dataHora >= :inicio and "
        		+ "proct.id.dataHora <= :fim";
        
        Calendar inicio = Calendar.getInstance();
        Calendar fim = Calendar.getInstance();
        inicio.setTimeInMillis(dia);
        fim.setTimeInMillis(dia);

        inicio.set(Calendar.HOUR_OF_DAY, 0);
        inicio.set(Calendar.MINUTE, 0);

        fim.set(Calendar.HOUR_OF_DAY, 23);
        fim.set(Calendar.MINUTE, 59);

        Parametros parametros = new Parametros();
        parametros.put("crm", crm);
        parametros.put("uf", uf.toUpperCase());
        parametros.put("inicio", inicio.getTime());
        parametros.put("fim", fim.getTime());

        List<ProtocoloConsulta> proctos = this.selectByQuery(q, ProtocoloConsulta.class, parametros);

        List<ConsultaAgenda> consultas = proctos.stream().map((procto) -> {
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
            String paciente = procto.getPessoa().getNomeInteiro();
            PlanoPessoa planoPessoa = procto.getConsulta().getPlanoUtilizado();
            String plano = "particular";
            if (planoPessoa != null) {
                plano = planoPessoa.getPlano().getConvenio().getNome();
            }
            Long protocolo = procto.getConsulta().getProtocolo();
            
            return new ConsultaAgenda(protocolo, paciente, procto.getPessoa().getCpf(), procto.getHora(), procto.getDataCompleta(), plano);
        }).collect(Collectors.toList());

        return new RespostaDomain(consultas);
    }
    
    @Override
    public RespostaDomain obterConsultasSemana(Long crm, String uf, Long dia) {
    	List<List<ConsultaAgenda>> consultasSemana = new ArrayList<List<ConsultaAgenda>>();
        
        Calendar inicio = Calendar.getInstance();
        Calendar fim = Calendar.getInstance();
        
        inicio.setTimeInMillis(dia);
        fim.setTimeInMillis(dia);
        
        Parametros parametros;
        
        String q = "from ProtocoloConsulta proct "
        		+ "join fetch proct.id.crm crm "
        		+ "join fetch proct.id.pessoa "
        		+ "where crm.valor = :crm and "
        		+ "crm.estado.UF = :uf and "
        		+ "proct.id.dataHora >= :inicio and "
        		+ "proct.id.dataHora <= :fim";


        inicio.set(Calendar.HOUR_OF_DAY, 0);
        inicio.set(Calendar.MINUTE, 0);
        fim.set(Calendar.HOUR_OF_DAY, 23);
        fim.set(Calendar.MINUTE, 59);
        
        for(int i = 0; i<6; i++){
	
	        parametros = new Parametros();
	        parametros.put("crm", crm);
	        parametros.put("uf", uf.toUpperCase());
	        parametros.put("inicio", inicio.getTime());
	        parametros.put("fim", fim.getTime());
	
	        List<ProtocoloConsulta> proctos = this.selectByQuery(q, ProtocoloConsulta.class, parametros);
	        
	        List<ConsultaAgenda> consultas = proctos.stream().map((procto) -> {
	            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
	            String paciente = procto.getPessoa().getNomeInteiro();
	            PlanoPessoa planoPessoa = procto.getConsulta().getPlanoUtilizado();
	            String plano = "particular";
	            if (planoPessoa != null) {
	                plano = planoPessoa.getPlano().getConvenio().getNome();
	            }
	            Long protocolo = procto.getConsulta().getProtocolo();
	            
	            return new ConsultaAgenda(protocolo, paciente, procto.getPessoa().getCpf(),procto.getHora(), procto.getDataCompleta(), plano);
	        }).collect(Collectors.toList());
	    
	        consultasSemana.add(consultas);

	        inicio.set(Calendar.DATE, inicio.get(Calendar.DATE) + 1);
	        fim.set(Calendar.DATE, fim.get(Calendar.DATE) + 1);
    	}

        return new RespostaDomain(consultasSemana);
    }

	public RespostaDomain obterConsultasMes(Long crm, String uf, Long dia) {
		
		return new RespostaDomain(null);
	}

    @Override
    @Transactional
    public RespostaDomain alterarHorarioConsulta(SolicitacaoAlteracaoHorarioConsulta solicitacao) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");

        ConfirmacaoAlteracao conf;
        ProtocoloConsulta procto;
        IdProtocoloConsulta proctoId;
        try {

            Consulta c = this.findById(Consulta.class, solicitacao.getProtocolo());
            procto = c.getProtocoloConsulta();
            proctoId = procto.getId();

            DateTime dataAnterior = new DateTime(procto.getDataCompleta().getTime());
            DateTime dataAtual = new DateTime(solicitacao.getNovaData());

            ProtocoloConsulta pConsultaNovoHorario = null;
            IndisponibilidadeConsulta indisponibilidade = null;

            try {
                String qConsulta = "from ProtocoloConsulta procto where "
                				 + "procto.id.dataHora = :novaData and "
                				 + "procto.id.crm = :crm and "
                				 + "procto.id.pessoa = :pessoa and "
                				 + "procto.id.clinica = :clinica";
                
                Parametros paramsConsultaNoDia = new Parametros();
                paramsConsultaNoDia.put("novaData", dataAtual.toDate());
                paramsConsultaNoDia.put("crm", proctoId.getCrm());
                paramsConsultaNoDia.put("pessoa", proctoId.getPessoa());
                paramsConsultaNoDia.put("clinica", proctoId.getClinica());

                pConsultaNovoHorario = this.getSingleResult(qConsulta, ProtocoloConsulta.class, paramsConsultaNoDia);
            } catch (Exception e) {

            }
//            try {
//
//                String qIndisp = "from IndisponibilidadeConsulta i join fetch i.contratacao contrEsp join fetch contrEsp.contratacao c join fetch c.pessoa p join fetch p.crm crm"
//                        + " where i.indisponibilidade = :novaData and c.clinica = :clinica and crm = :crm";
//
//                Parametros paramsIndisp = new Parametros();
//                paramsIndisp.put("novaData", dataAtual.toDate());
//                paramsIndisp.put("clinica", proctoId.getClinica());
//                paramsIndisp.put("crm", proctoId.getCrm());
//
//                indisponibilidade = this.getSingleResult(qIndisp, IndisponibilidadeConsulta.class, paramsIndisp);
//            } catch (Exception e) {
//            }
            if (pConsultaNovoHorario != null || indisponibilidade != null) {
                throw new Exception("O horário escolhido já possui uma consulta pendente ou está disponível.");
            };

            Minutes period = Minutes.minutesBetween(dataAnterior, dataAtual);
            conf = new ConfirmacaoAlteracao(sdf.format(dataAnterior.toDate()), sdf.format(dataAtual.toDate()), new Long(period.getMinutes()), procto.getConsulta().getProtocolo());

            String q = "UPDATE protocolo_consulta "
            		+ "SET data_hora = :data "
            		+ "where protocolo_consulta = :protocolo";
            
            Query qUpdate = this.getEntityManager().createNativeQuery(q);
            qUpdate.setParameter("data", dataAtual.toDate());
            qUpdate.setParameter("protocolo", c.getProtocolo());
            int i = qUpdate.executeUpdate();

            if (i > 0) {
                StatusGeralConsultaPessoa status = new StatusGeralConsultaPessoa();
                StatusConsultaGeral st = this.findById(StatusConsultaGeral.class, 3L);
                StatusConsulta stConsulta = this.findById(StatusConsulta.class, 1L);
                status.setConsulta(c);
                status.setStatusGeral(st);
                status.setStatus(stConsulta);
                status.setDataHora(dataAtual.toDate());
                status.setDescricao("Alteração de horário");
                this.getEntityManager().persist(status);
                this.getEntityManager().flush();
            }
        } catch (Exception e) {
            RespostaDomain resposta = new RespostaDomain(null);
            resposta.adicionarMensagemErro(e.getMessage());
            return resposta;
        }
        return new RespostaDomain(conf);
    }
    
    public RespostaDomain obterDadosConsulta(Long cpf) {
		String q = "from Pessoa p "
				 + "where p.cpf = :cpf";
		
        Parametros parametros = new Parametros("cpf", cpf);
        //parametros.put("cpf", cpf);

        Pessoa pessoa;
        try {
            pessoa = this.getSingleResult(q, Pessoa.class, parametros);
        } catch (Exception e) {
            RespostaDomain resposta = new RespostaDomain(null);
            resposta.adicionarMensagemErro("Paciente não encontrado para o cpf:" + cpf);
            resposta.adicionarMensagemErro(e.getMessage());
            return resposta;
        }
		
        List<PlanoPessoa> planos = pessoa.getPlanos();
        
        List<PlanosPessoa> planosPessoa = planos.stream().map((plano) -> {
	        Long idPlano = plano.getId();
	        String nome = plano.getPlano().getConvenio().getNome();
	        Long carteirinha = plano.getNumero();
	        	
        	return new PlanosPessoa(idPlano, nome, carteirinha);
        }).collect(Collectors.toList());
        
		return new RespostaDomain(new DadosConsulta(pessoa.getId(), pessoa.getNome(), pessoa.getEmail(), pessoa.getTelefone(), planosPessoa));
	}

    @Override
	public RespostaDomain obterMedicosClinica(Long idClinica) {
		System.out.println("AgendaRepositoryMysql.obterMedicosClinica: Executando obterMedicosClinica...");
		String q = "from Contratacao con "
				 + "join fetch con.id.clinica cli "
				 + "join fetch con.id.profissao pro where "
				 + "cli.id = :clinica and "
				 + "pro.id = :profissao";
		
		Parametros parametros = new Parametros();
		parametros.put("clinica", idClinica);
		parametros.put("profissao", 1); //profissão médico
		
		List<Contratacao> contratacoes;
		try{
			contratacoes = this.selectByQuery(q, Contratacao.class, parametros);
		}catch(Exception e){
			System.out.println("AgendaRepositoryMysql.obterMedicosClinica: Exception:"+e.getMessage());
			RespostaDomain resposta = new RespostaDomain(null);
            resposta.adicionarMensagemErro("Não foi encontrada a clínica para o id:" + idClinica);
            resposta.adicionarMensagemErro(e.getMessage());
            
            return resposta;
		}
		System.out.println("AgendaRepositoryMysql.obterMedicosClinica: "+contratacoes.isEmpty());//TESTAR PQ ESTÁ VÁZIO!!!!
		List<MedicosClinica> medicosClinica = contratacoes.stream().map((mClinica) -> {
			
			List<MedicoEspecialidade> medicosEspecialidade = mClinica.getEspecialidades().stream().map((mEspecialidade) -> {
	            return new MedicoEspecialidade(mEspecialidade.getEspecialidade().getId(), mEspecialidade.getEspecialidade().getNome());
	        }).collect(Collectors.toList());
			
            return new MedicosClinica(mClinica.getPessoa().getCrm().getValor(), mClinica.getPessoa().getNomeInteiro(), medicosEspecialidade);
        }).collect(Collectors.toList());
		
        return new RespostaDomain(medicosClinica);
	}

	@Override
	public RespostaDomain obterPlanosClinica(Long idClinica) {
		String q = "from Clinica cli where cli.id = :clinica";
		
		Parametros parametros = new Parametros();
		parametros.put("clinica", idClinica);
		
		Clinica clinica;
		try{
			clinica = this.getSingleResult(q, Clinica.class, parametros);
		}catch(Exception e){
			System.out.println("Exception"+e.getMessage());
			RespostaDomain resposta = new RespostaDomain(null);
            resposta.adicionarMensagemErro("Não foi encontrada a clínica para o id:" + idClinica);
            resposta.adicionarMensagemErro(e.getMessage());
            
            return resposta;
		}
		
		List<PlanosClinica> planosClinica = clinica.getPlanosCobertos().stream().map((plano) -> {
            return new PlanosClinica(plano.getConvenio().getId(), plano.getConvenio().getNome());
        }).collect(Collectors.toList());
		
		return new RespostaDomain(planosClinica);
	}
	
	@Override
	@Transactional
	public RespostaDomain adicionarNovaConsulta(Long protocolo, AgendarConsulta agendar) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");

		ConfirmacaoAlteracao conf;
		ProtocoloConsulta procto;
		IdProtocoloConsulta proctoId;

		CRM crm = this.getCRM(agendar.getCrm(), "");
        Clinica clinica = this.getClinica(agendar.getIdClinica());
        Pessoa pessoa = this.getPessoa(agendar.getIdPaciente());
        PlanoPessoa planoUtilizado = pessoa.getPlanos().stream().filter(plano -> plano.getId().equals(agendar.getIdPlano())).findFirst().orElse(null);
        Especialidade especialidade = this.getEspecialidade(agendar.getIdEspecialidade());
        ProtocoloConsulta prot
                = new ProtocoloConsulta(crm, pessoa, clinica, new Date(agendar.getDataHorario()));

        Consulta consulta = new Consulta();
        consulta.setProtocolo(protocolo);
        consulta.setEspecialidade(especialidade);
        consulta.setStatus(new StatusConsulta(4L)); //Setting consulta para aguardando validação
        consulta.setPlanoUtilizado(planoUtilizado);
        consulta.setTipo(new TipoConsulta(1L));
        prot.setConsulta(consulta);

        ProtocoloConsulta savedProtocolo = this.saveAndGetResult(protocolo, prot);

        return new RespostaDomain(this.tratarConsulta(savedProtocolo));
    }
	
    private ConsultaAgendada tratarConsulta(ProtocoloConsulta protocoloConsulta){
        Consulta c = protocoloConsulta.getConsulta();
        CRM crm = protocoloConsulta.getCRM();
        Clinica clinica = protocoloConsulta.getClinica();
        
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy - HH:mm");

        Long protocolo = c.getProtocolo();
        String medico = crm.getPessoa().getNomeInteiro();
        String local = clinica.toString();
        String especialidade = c.getEspecialidade().getNome();
        String dataHora = sdf.format(protocoloConsulta.getDataCompleta());

        return new ConsultaAgendada(protocolo, medico, local, especialidade, dataHora);
    }
	
    private ProtocoloConsulta saveAndGetResult(Long protocolo, ProtocoloConsulta consulta) {
        //TODO Arrumar essa parte
        String q = "from ProtocoloConsulta procto join fetch procto.consulta c where c.protocolo = :protocolo";

        this.persist(consulta);

        ProtocoloConsulta procto = this.getSingleResult(q, ProtocoloConsulta.class, new Parametros("protocolo", protocolo));

        return procto;

    }
	
    //TODO arrumar para UF
    private CRM getCRM(Long crm, String uf) {
        String q = "from CRM c where c.valor = :crm";
        Parametros parametros = new Parametros();

        parametros.put("crm", crm);
        //parametros.put("uf", UF.SP);

        return this.getSingleResult(q, CRM.class, parametros);
    }

    private Pessoa getPessoa(Long idPaciente) {
        return this.findById(Pessoa.class, idPaciente);
    }

    private Clinica getClinica(Long idClinica) {
        return this.findById(Clinica.class, idClinica);
    }

    private Especialidade getEspecialidade(Long idEspecialidade) {
        return this.findById(Especialidade.class, idEspecialidade);
    }
}
