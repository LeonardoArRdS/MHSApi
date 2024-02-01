/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.system.medical.mhs.dados.mySql.repositories.app;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.joda.time.Days;
import org.joda.time.Hours;
import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.system.medical.mhs.dados.mySql.AbstractRepository;
import com.system.medical.mhs.dados.mySql.entities.CRM;
import com.system.medical.mhs.dados.mySql.entities.Clinica;
import com.system.medical.mhs.dados.mySql.entities.ClinicaEspecialidade;
import com.system.medical.mhs.dados.mySql.entities.ClinicaEspecialidadePreco;
import com.system.medical.mhs.dados.mySql.entities.Contratacao;
import com.system.medical.mhs.dados.mySql.entities.ContratacaoEspecialidade;
import com.system.medical.mhs.dados.mySql.entities.Especialidade;
import com.system.medical.mhs.dados.mySql.entities.Pessoa;
import com.system.medical.mhs.dados.mySql.entities.Plano;
import com.system.medical.mhs.dominios.RespostaDomain;
import com.system.medical.mhs.dominios.app.interfaces.agendamento.IMedicosDisponiveisRepository;
import com.system.medical.mhs.dominios.app.modelos.agendamento.DisponibilidadeMedico;
import com.system.medical.mhs.dominios.app.modelos.agendamento.HorariosDisponiveis;
import com.system.medical.mhs.dominios.app.modelos.agendamento.Medico;
import com.system.medical.mhs.dominios.app.modelos.agendamento.MedicoResumo;

/**
 *
 * @author Felipe
 */
@Repository
public class MedicosRepositoryMySql extends AbstractRepository implements IMedicosDisponiveisRepository {

    @Autowired
    private ConsultasMedicoRepositoryMySql consultasMedRepository;

    @Override
    public RespostaDomain getMedicos(Long idPlano, Long idEspecialidade, Long idRegiao) {
        Parametros parametros = new Parametros();

        String q = "SELECT contrEsp.* from contratacao_especialidade contrEsp, pessoa p, contratacao contr ";

        if (idRegiao != null) {
            parametros.put("idRegiao", idRegiao);
            q += "INNER JOIN "
                    + "(SELECT id_clinica FROM regiao reg, zona_regiao zona, clinica, endereco_clinica endco \n"
                    + "  where zona.id = :idRegiao AND zona.id = reg.id_zona and clinica.id = endco.id_clinica and endco.cep_faixa \n"
                    + "  between reg.cep_inicio and reg.cep_fim) localidade "
                    + "ON localidade.id_clinica = contr.id_clinica ";
        }

        if (idPlano != null) {
            parametros.put("idPlano", idPlano);
            q += "INNER JOIN "
                    + "clinica_plano cPlano \n"
                    + "ON cPlano.id_clinica = contr.id_clinica and cPlano.id_plano = :idPlano ";
        }

        q += "where contrEsp.id_contratacao = contr.id and p.id = contr.id_pessoa";

        if (idEspecialidade != null) {
            parametros.put("idEspecialidade", idEspecialidade);
            q += " and contrEsp.id_especialidade = :idEspecialidade";
        }

        q += " group by contr.id_pessoa";
        
        List<ContratacaoEspecialidade> contratacoes
                = this.selectByNativeQuery(q, ContratacaoEspecialidade.class, parametros);
        List<MedicoResumo> medicos
                = contratacoes.stream().map(contr -> getResumo(contr)).collect(Collectors.toList());

        return new RespostaDomain(medicos);
    }

    private MedicoResumo getResumo(ContratacaoEspecialidade contratacao) {
        Pessoa p = contratacao.getContratacao().getPessoa();

        Long idEspecialidade = contratacao.getEspecialidade().getId();
        Long idClinica = contratacao.getContratacao().getClinica().getId();

        String nome = p.getNomeInteiro();
        CRM crm = p.getCrm();
        List<String> espArray = crm.getEspecialidades().stream().map((e) -> e.getNome()).collect(Collectors.toList());

        String especialidades = String.join(", ", espArray);
        String q = "from ClinicaEspecialidade cl where cl.clinica.id = :idClinica and cl.especialidade.id = :idEspecialidade";

        Parametros parametros = new Parametros();
        parametros.put("idEspecialidade", idEspecialidade);
        parametros.put("idClinica", idClinica);

        List<ClinicaEspecialidadePreco> precos = new ArrayList<>();

        Long idMoeda = 1L;

        for (Especialidade especialidade : crm.getEspecialidades()) {
            ClinicaEspecialidadePreco pco = getPrecoEspecialidade(crm.getPessoa().getId(), especialidade.getId(), idMoeda);
            if (pco != null) {
                precos.add(pco);
            }
        }

        Double precoMedio = precos.stream().mapToDouble(pco -> pco.getValor() == null ? 0.0 : pco.getValor()).average().orElse(0.0);

//        ClinicaEspecialidade cEsp = this.getSingleResult(q, ClinicaEspecialidade.class, parametros);
//        ClinicaEspecialidadePreco preco = cEsp.getPrecos().stream().findFirst().orElse(null);
        String precoConsulta = "R$ " + precoMedio;

        return new MedicoResumo(nome, especialidades, precoConsulta);
    }

    @Override
    public RespostaDomain getMedicosPorEspecialidade(Long idEspecialidade) {
        RespostaDomain resposta;
        String q = "from Especialidade esp where esp.id = :idEspecialidade";
        Especialidade especialidade = this.getSingleResult(q, Especialidade.class, new Parametros("idEspecialidade", idEspecialidade));
        List<MedicoResumo> medicos
                = especialidade.getCrms().stream().map((crm) -> this.tratarMedicosConsulta(crm, especialidade)).collect(Collectors.toList());

        resposta = new RespostaDomain(medicos);
        return resposta;
    }

    @Override
    public RespostaDomain getMedicosNearBy(Long latitude, Long longitude) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public RespostaDomain getMedicosByRangePrecos(Double inferior, Double superior) {
//        String q = "from ClinicaEspecialidadePreco p where p.valor >= :pcoInferior and p.valor <= :pcoSuperior order by p.valor";
//        Parametros parametros = new Parametros();
//        parametros.put("pcoInferior", inferior);
//        parametros.put("pcoSuperior", superior);
//        List<ClinicaEspecialidadePreco> preco = this.selectByQuery(q, ClinicaEspecialidadePreco.class, parametros);
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public RespostaDomain getMedicosByPlano(Long idPlano) {
        String qPlano = "from Plano p where p.id = :idPlano";
        String qClinicEsp = "from ClinicaEspecialidade c where :plano in elements(c.id.clinica.planosCobertos)";
        String qContratacoes = "from Contratacao contr where contr.clinica in :clinicas";

        Plano plano = this.getSingleResult(qPlano, Plano.class, new Parametros("idPlano", idPlano));
        List<ClinicaEspecialidade> cEsp = this.selectByQuery(qClinicEsp, ClinicaEspecialidade.class, new Parametros("plano", plano));
        Set<Clinica> clinicas = cEsp.stream().map(c -> c.getClinica()).collect(Collectors.toSet());

        List<Contratacao> contratacoes = this.selectByQuery(qContratacoes, Contratacao.class, new Parametros("clinicas", clinicas));

        List<MedicoResumo> medicos = clinicas.stream().map((clinica) -> {

            Contratacao contratacao = contratacoes.stream().filter(
                    contr -> contr.getClinica().equals(clinica)).findFirst().get();

            Pessoa pessoa = contratacao.getPessoa();
            CRM crm = pessoa.getCrm();
            List<String> espArray = crm.getEspecialidades().stream().map((e) -> e.getNome()).collect(Collectors.toList());
            List<ClinicaEspecialidadePreco> precos = new ArrayList<>();

            Long idMoeda = 1L;
            String especialidades = String.join(", ", espArray);

            for (Especialidade especialidade : crm.getEspecialidades()) {
                precos.add(getPrecoEspecialidade(crm.getPessoa().getId(), especialidade.getId(), idMoeda));
            }

            Double precoMedio = precos.stream().mapToDouble(p -> p.getValor()).average().orElse(0.0);

            return new MedicoResumo(pessoa.getNomeInteiro(), especialidades, "R$ " + precoMedio);
        }).collect(Collectors.toList());

        return new RespostaDomain(medicos);
    }

    @Override
    public RespostaDomain getMedicosByDisponibilidade(Integer dias) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public RespostaDomain getMedicosBySexo(Long idSexo) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public RespostaDomain getDetalhe(Long CRM, String UF, Long idEspecialidade) {
        RespostaDomain resposta;
        
        //Melhorar codigo
        String q = "from CRM c join fetch c.especialidades where c.valor = :crm and c.estado.UF = :uf";

        Parametros parametros = new Parametros();
        parametros.put("crm", CRM);
        parametros.put("uf", UF);
        CRM crm = null;
        try {
            crm = this.getSingleResult(q, CRM.class, parametros);
        } catch (Exception e) {
        	e.printStackTrace();
            resposta = new RespostaDomain(null);
            resposta.adicionarMensagemErro(e.getMessage());
            return resposta;
        }
        Long idMoeda = new Long(1); //Forçando BRL;

        List<String> espArray = crm.getEspecialidades().stream().map((e) -> e.getNome()).collect(Collectors.toList());
        List<String> formacoesArray = crm.getFormacoes().stream().map((f) -> f.getDescricao()).collect(Collectors.toList());
        List<ClinicaEspecialidadePreco> precos = new ArrayList<>();
//        for (Especialidade especialidade : crm.getEspecialidades()) {
//            precos.add(getPrecoEspecialidade(crm.getPessoa().getId(), especialidade.getId(), idMoeda));
//
//        }
        
        ClinicaEspecialidadePreco preco;
        Double precoEspecialidade = null;
        try {
			preco = this.getPrecoEspecialidade(crm.getPessoa().getId(), idEspecialidade, idMoeda);
			precoEspecialidade = preco.getValor();
		} catch (Exception exGetPreco) {
			exGetPreco.printStackTrace();
			resposta = new RespostaDomain(null);
			resposta.adicionarMensagemErro("Erro ao buscar especialidade para o CRM indicado");
			resposta.adicionarMensagemErro(exGetPreco.getMessage());
			return resposta;
		}
        
        String especialidades = String.join(", ", espArray);
        
        List<MedicoResumo> detalhes = new ArrayList<>();
        //detalhes.add(new MedicoResumo(crm.getPessoa().getNomeInteiro(),
        //       especialidades, "R$ " + media));

        Calendar cl = Calendar.getInstance();
        //cl.setTimeInMillis(dia);
        cl.set(Calendar.HOUR_OF_DAY, 0);
        cl.set(Calendar.MINUTE, 0);
        cl.set(Calendar.SECOND, 0);
        String proximaDisponibilidade = "Horário livre ";
        DisponibilidadeMedico disponibilidade = null;
        //VER SE HÁ DISPONIBILIDADE EM ATÉ 5 DIAS
        Set<String> localidades = new HashSet<>();
        daysLoops:
        for (int i = 0; i <= 6; i++) {
        	
            List<HorariosDisponiveis> hrios
                    = (List<HorariosDisponiveis>) this.consultasMedRepository.getHorariosDisponiveisPorDia(CRM, UF, cl, idEspecialidade).get();
            
            if (hrios.isEmpty()) {
                cl.add(Calendar.DAY_OF_MONTH, 1);
                continue;
            }
            
            HorariosDisponiveis hrio = hrios.get(0);

            List<Integer> dt = Arrays.asList(hrio.getData().split("/")).stream().map((str) -> new Integer(str)).collect(Collectors.toList());
            Calendar nextAvailable = Calendar.getInstance();
            nextAvailable.set(dt.get(2), dt.get(1) - 1, dt.get(0), 0, 0, 0);

            LocalDate now = LocalDate.fromCalendarFields(cl);
            LocalDate next = LocalDate.fromCalendarFields(nextAvailable);

            int days = Days.daysBetween(now, next).getDays();
            int hours = Hours.hoursIn(next.toInterval()).getHours();
            String periodo = hours < 12 ? "manhã" : hours < 18 ? "tarde" : "noite";
            
            switch(days){
                case 0:
                     proximaDisponibilidade = "hoje de ";
                     break;
                case 1:
                    proximaDisponibilidade = "amanhã de ";
                    break;
                case 2:
                    proximaDisponibilidade = "depois de amanhã de ";
                    break;
                default:
                    proximaDisponibilidade = "em " + days + " dia(s) a ";
                    
            }
            
            proximaDisponibilidade += periodo;
            disponibilidade = new DisponibilidadeMedico(proximaDisponibilidade, hrio.getLocalidade());
            break;
            
        }

        //BuscarMedicosConsultas meds = new BuscarMedicosConsultas();
        resposta = new RespostaDomain(new Medico(crm.getPessoa().getNomeInteiro(),
                especialidades, formacoesArray, new ArrayList<String>(), disponibilidade, "R$ " + precoEspecialidade));
        return resposta;
    }


    private MedicoResumo tratarMedicosConsulta(CRM crm, Especialidade especialidade) {
        Pessoa pessoa = crm.getPessoa();
        String nomeMedico = pessoa.getNomeInteiro();
        List<String> espArray = crm.getEspecialidades().stream().map((e) -> e.getNome()).collect(Collectors.toList());
        String especialidades = String.join(", ", espArray);

        Long idMoeda = new Long(1); //Forçando BRL;
        ClinicaEspecialidadePreco preco = getPrecoEspecialidade(pessoa.getId(), especialidade.getId(), idMoeda);
        String precoStr = "-";
        if (preco != null) {
            precoStr = preco.toString();
        }

        return new MedicoResumo(nomeMedico, especialidades, precoStr);
    }

    private ClinicaEspecialidadePreco getPrecoEspecialidade(Long idPessoa, Long idEspecialidade, Long idMoeda) {
        String nativeQuery = "SELECT pco.* "
                + "FROM MHS.contratacao_especialidade cEsp, MHS.contratacao contr, MHS.pessoa p, MHS.clinica_especialidade cliEsp, MHS.clinica_especialidade_preco pco "
                + "WHERE cEsp.id_especialidade = :idEspecialidade AND cliEsp.id_especialidade = cEsp.id_especialidade AND contr.id_clinica = cliEsp.id_clinica "
                + "AND cEsp.id_contratacao = contr.id AND contr.id_pessoa = p.id AND p.id = :idPessoa AND pco.id_clinica_especialidade = cliEsp.id AND pco.id_moeda = :idMoeda";

        Parametros parametros = new Parametros();
        parametros.put("idEspecialidade", idEspecialidade);
        parametros.put("idPessoa", idPessoa);
        parametros.put("idMoeda", idMoeda); //BRL;

        List<ClinicaEspecialidadePreco> precos = this.selectByNativeQuery(nativeQuery, ClinicaEspecialidadePreco.class, parametros);

        //Retornando o primeiro valor,
        if (precos.size() > 0) {
            return precos.get(0);
        }
        return null;
    }
}
