package com.system.medical.mhs.dados.mySql.repositories.pwa;

import java.util.ArrayList;

import org.springframework.stereotype.Repository;

import com.system.medical.mhs.dados.mySql.AbstractRepository;
import com.system.medical.mhs.dados.mySql.AbstractRepository.Parametros;
import com.system.medical.mhs.dados.mySql.entities.CaracteristicaFisicaPessoa;
import com.system.medical.mhs.dados.mySql.entities.Cidade;
import com.system.medical.mhs.dados.mySql.entities.DoencaPessoa;
import com.system.medical.mhs.dados.mySql.entities.Estado;
import com.system.medical.mhs.dados.mySql.entities.Medicacao;
import com.system.medical.mhs.dados.mySql.entities.Pessoa;
import com.system.medical.mhs.dados.mySql.entities.SintomaConsulta;
import com.system.medical.mhs.dados.mySql.entities.TratamentoPessoa;
import com.system.medical.mhs.dominios.RespostaDomain;
import com.system.medical.mhs.dominios.pwa.interfaces.IPacientesRepository;
import com.system.medical.mhs.dominios.pwa.modelos.Doencas;
import com.system.medical.mhs.dominios.pwa.modelos.MedicacaoTratamento;
import com.system.medical.mhs.dominios.pwa.modelos.OpcoesContato;
import com.system.medical.mhs.dominios.pwa.modelos.Tratamento;
import com.system.medical.mhs.dominios.pwa.modelos.pacientes.Dados;
import com.system.medical.mhs.dominios.pwa.modelos.pacientes.DadosPessoais;
import com.system.medical.mhs.dominios.pwa.modelos.pacientes.ExamesRecentes;
import com.system.medical.mhs.dominios.pwa.modelos.pacientes.ExamesRecentesCompleto;
import com.system.medical.mhs.dominios.pwa.modelos.pacientes.HistoricoDoencas;
import com.system.medical.mhs.dominios.pwa.modelos.pacientes.MedicacaoFrequente;
import com.system.medical.mhs.dominios.pwa.modelos.pacientes.Perfil;
import com.system.medical.mhs.utils.Imagem;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import org.joda.time.DateTime;
import org.joda.time.Interval;

@Repository
public class PacientesRepositoryMySql extends AbstractRepository implements IPacientesRepository {

    @Override
    public RespostaDomain obterDados(Long cpf) {
        RespostaDomain resposta;
        String query = "select p from Pessoa p where p.cpf = :cpf";
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

        CaracteristicaFisicaPessoa altura = pessoa.getCaracteristicasFisicas().stream().filter((caract) -> {
            return caract.getCaracteristica().getId().equals(1L);
        }).findFirst().orElse(null);

        CaracteristicaFisicaPessoa peso = pessoa.getCaracteristicasFisicas().stream().filter((caract) -> {
            return caract.getCaracteristica().getId().equals(2L);
        }).findFirst().orElse(null);

        resposta = new RespostaDomain(
                new Dados(
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

    @Override
    public RespostaDomain obterDadosPessoais(Long cpf) {
        RespostaDomain resposta;
        String query = "select p from Pessoa p where p.cpf = :cpf";
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
        Cidade cidade = pessoa.getLocalNascimento();
        Estado estado = cidade.getEstado();
        String planos = pessoa.getPlanos().stream().map((p) -> p.getPlano().getNome()).collect(Collectors.joining(", "));
        String tel = pessoa.getTelefone() == null ? null : pessoa.getTelefone().toString();

        resposta = new RespostaDomain(
            new DadosPessoais(
                pessoa.getEndereco().getCEP(),
                cidade.getNome(),
                estado.getNome(),
                estado.getPais().getNome(),
                pessoa.getEndereco().getRua(),
                pessoa.getEndereco().getNumero(),
                pessoa.getEmail(),
                tel,
                pessoa.getCpf(),
                "123.456.79-1x",//pessoa.getRg().toString(),//Deixar RG String
                pessoa.getNumeroSUS(),//.toString(),
                pessoa.getNomeMae(),
                pessoa.getNomePai(),
                pessoa.getOcupacao().getNome(),
                planos,
                new OpcoesContato((pessoa.getEmail() != null), (pessoa.getTelefone() != null))
            )
        );
        return resposta;
    }

    @Override
    public RespostaDomain obterPerfil(Long cpf) {
        RespostaDomain resposta;
        String query = "select p from Pessoa p where p.cpf = :cpf";
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
        resposta = new RespostaDomain(
            new Perfil(
        		new Imagem(pessoa.getFoto()).converterImagemBase64(),
                pessoa.getNome(),
                pessoa.getSobrenome(),
                pessoa.getDataNascimento(),
                pessoa.getSexo().getNome(),
                pessoa.getNacionalidade().getDescricao()
            )
        );
        return resposta;
    }

    @Override
    public RespostaDomain consultarAlergiasConhecidas(Long cpf) {
        RespostaDomain resposta;
        resposta = new RespostaDomain(null);
        return resposta;
    }

    /*
    //Se houver necessidade de repor a consulta...
    @Override
    public RespostaDomain consultarDoencaFrequente(Long cpf) {
        RespostaDomain resposta;
        String query = "from DoencaPessoa dPessoa "
                + "join fetch dPessoa.pessoa p "
                + "join fetch dPessoa.doenca d "
                + "where p.cpf = :cpf";
        List<DoencaPessoa> doencasPessoa;
        
        try {
            doencasPessoa = this.selectByQuery(query, DoencaPessoa.class, new Parametros("cpf", cpf));
        } catch (Exception e) {
            resposta = new RespostaDomain(null);
            resposta.adicionarMensagemErro("Paciente não encontrado para o cpf:"+cpf);
            resposta.adicionarMensagemErro(e.getMessage());
            return resposta;
        }
        ArrayList<Doencas> doencas = new ArrayList<Doencas>();
        for (DoencaPessoa doenca : doencasPessoa) {
            doencas.add(
            	new Doencas(
                    doenca.getDoenca().getNome(),
                    doenca.getDoenca().getCID().toString()
                )
            );
        }

        resposta = new RespostaDomain(new DoencaFrequente(doencas));

        return resposta;
    }
     */
    public RespostaDomain consultarExamesRecentes(Long cpf) {
        RespostaDomain resposta;
        String query = "from Pessoa p "
                + //	"join fetch dExame.diagnostico d "+
                //	"join fetch d.consulta c "+
                //	"join fetch c.protocolo pr "+
                //	"join fetch pr.protocolo p "+
                //	"join fetch dExame.exame e "+
                "where p.cpf = :cpf";

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
                                    exame.getAnormalidade()
                            );
                        }
                ).collect(Collectors.toList());

        resposta = new RespostaDomain(exames);
        return resposta;
    }

    @Override
    public RespostaDomain consultarExamesRecentesCompleto(Long cpf) {
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
        List<ExamesRecentesCompleto> exames = new ArrayList<>();
        pessoa.getConsultas().stream()
                .forEach(
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
                            List<ExamesRecentesCompleto> examesIn = pessoa.getExames().stream().map((e) -> {
                                
                                List<String> conclusoes = new ArrayList<>();
                                List<String> gerais = new ArrayList<>();

                                e.getResultados().stream().forEach((conc) -> {
                                    Long idComGerais, idComConclusivos;
                                    idComGerais = 1L;
                                    idComConclusivos = 2L;
                                    conclusoes.addAll(conc.getComentarios().stream().filter((c) -> c.getTipo().getId().equals(idComGerais))
                                            .map((c) -> c.getComentario()).collect(Collectors.toList()));

                                    conclusoes.addAll(conc.getComentarios().stream().filter((c) -> c.getTipo().getId().equals(idComConclusivos))
                                            .map((c) -> c.getComentario()).collect(Collectors.toList()));

                                });
                                //TODO O que é comentários exames ?
                                
                                return new ExamesRecentesCompleto(
                                        e.getDataRealizacao(),
                                        consulta.getConsulta().getEspecialidade().getNome(),
                                        conclusoes,
                                        sintomas,
                                        tratamentos,
                                        "comentários exames",
                                        gerais
                                );
                            }).collect(Collectors.toList());
                            
                            exames.addAll(examesIn);
                        });
    
    	resposta = new RespostaDomain(exames);
        return resposta;
    }

    @Override
    public RespostaDomain consultarHistoricoDoencas(Long cpf) {
    	RespostaDomain resposta;
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
      
        Set<DoencaPessoa> doencas = pessoa.getDoencas();
        List<Doencas> doencasAtuais = doencas.stream().filter(
                (d) -> d.getStatus().getId().equals(1L)).map(
                	(doenca) -> {
	                	return new Doencas(
	                		doenca.getDoenca().getNome(),
	                		doenca.getDoenca().getCID()
	                	);
                	}
                ).collect(Collectors.toList()); // DOENCA ATIVA
        
        List<Doencas> doencasPassadas = doencas.stream().filter(
                (d) -> d.getStatus().getId().equals(2L)).map(
                    	(doenca) -> {
    	                	return new Doencas(
    	                		doenca.getDoenca().getNome(),
    	                		doenca.getDoenca().getCID()
    	                	);
                    	}
                    ).collect(Collectors.toList()); // DOENCA CURADA
        
        List<Doencas> doencasFamiliares = new ArrayList<>();
        pessoa.getFamiliares().stream().forEach((f) -> {
            List<Doencas> ds = f.getDoencas().stream().map(
                	(doenca) -> {
	                	return new Doencas(
	                		doenca.getNome(),
	                		doenca.getCID()
	                	);
                	}
                ).collect(Collectors.toList());            
            doencasFamiliares.addAll(ds);
        });
        
        resposta = new RespostaDomain(new HistoricoDoencas(doencasAtuais, doencasPassadas, doencasFamiliares));

        return resposta;
    }

    @Override
    public RespostaDomain consultarMedicacaoFrequente(Long cpf) {
    	 RespostaDomain resposta;
         String query = "from Medicacao mdc "
                 + "join fetch mdc.pessoa p "
                 + "join fetch mdc.medicamento mdm "
                 + "where p.cpf = :cpf";

         List<Medicacao> medicacoes;
         try {
             medicacoes = this.selectByQuery(query, Medicacao.class, new Parametros("cpf", cpf));
         } catch (Exception e) {
             resposta = new RespostaDomain(null);
             resposta.adicionarMensagemErro("Paciente não encontrado para o cpf:" + cpf);
             resposta.adicionarMensagemErro(e.getMessage());
             return resposta;
         }
         ArrayList<String> medicacaoFrequente = new ArrayList<String>();
         for (Medicacao medicacao : medicacoes) {
             medicacaoFrequente.add(medicacao.getMedicamento().getNome());
         }
         resposta = new RespostaDomain(new MedicacaoFrequente(medicacaoFrequente));
         return resposta;
      }

    @Override
    public RespostaDomain consultarPrescricao(Long cpf) {
        RespostaDomain resposta;
        resposta = new RespostaDomain(null);
        return resposta;
    }

    @Override
    public RespostaDomain consultarProcedimentosAtuais(Long cpf) {
        RespostaDomain resposta;
        resposta = new RespostaDomain(null);
        return resposta;
    }
}
