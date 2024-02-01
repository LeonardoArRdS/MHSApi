package com.system.medical.mhs.dados.mySql.repositories.pwa;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import org.springframework.stereotype.Repository;

import com.system.medical.mhs.dados.mySql.AbstractRepository;
import com.system.medical.mhs.dados.mySql.AbstractRepository.Parametros;
import com.system.medical.mhs.dados.mySql.entities.AcompanhamentoMedico;
import com.system.medical.mhs.dados.mySql.entities.AndamentoConsulta;
import com.system.medical.mhs.dados.mySql.entities.CRM;
import com.system.medical.mhs.dados.mySql.entities.CaracteristicaFisicaPessoa;
import com.system.medical.mhs.dados.mySql.entities.Clinica;
import com.system.medical.mhs.dados.mySql.entities.Consulta;
import com.system.medical.mhs.dados.mySql.entities.Especialidade;
import com.system.medical.mhs.dados.mySql.entities.Estado;
import com.system.medical.mhs.dados.mySql.entities.Pessoa;
import com.system.medical.mhs.dados.mySql.entities.PlanoPessoa;
import com.system.medical.mhs.dados.mySql.entities.ProtocoloConsulta;
import com.system.medical.mhs.dados.mySql.entities.Sintoma;
import com.system.medical.mhs.dados.mySql.entities.SintomaConsulta;
import com.system.medical.mhs.dados.mySql.entities.StatusAndamentoConsulta;
import com.system.medical.mhs.dados.mySql.entities.StatusConsulta;
import com.system.medical.mhs.dados.mySql.entities.TipoConsulta;
import com.system.medical.mhs.dominios.RespostaDomain;
import com.system.medical.mhs.dominios.pwa.interfaces.IPacientesHomeRepository;
import com.system.medical.mhs.dominios.pwa.modelos.pacientes.home.ConfirmacaoConsultaImediata;
import com.system.medical.mhs.dominios.pwa.modelos.pacientes.home.ConfirmacaoInicioConsulta;
import com.system.medical.mhs.dominios.pwa.modelos.pacientes.home.MeusPacientes;
import com.system.medical.mhs.dominios.pwa.modelos.pacientes.home.SolicitacaoConsultaEmergencia;
import com.system.medical.mhs.dominios.pwa.modelos.pacientes.home.SolicitacaoConsultaImediata;
import com.system.medical.mhs.utils.Funcoes;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

/**
 * @author Leonardo Ariel
 *
 */
@Repository
public class PacientesHomeRepository extends AbstractRepository implements IPacientesHomeRepository {

    @Override
    public RespostaDomain buscarMeusPacientes(Long crm, String uf) {
        RespostaDomain resposta;
        String query = "from ProtocoloConsulta p "
                + "join fetch p.id.crm crm join fetch crm.estado estado "
                + "where crm.valor = :crm and estado.uf = :uf "
                + "group by p.id.pessoa "
                + "order by p.id.dataHora DESC";

        Parametros parametros = new Parametros();
        parametros.put("crm", crm);
        parametros.put("uf", uf.toUpperCase());
        
        List<ProtocoloConsulta> consultas;
        try {
            consultas = this.selectByQuery(query, ProtocoloConsulta.class, parametros);
        } catch (Exception e) {
            resposta = new RespostaDomain(null);
            resposta.adicionarMensagemErro("Médico não encontrado para o crm:" + crm+" e UF:"+uf);
            resposta.adicionarMensagemErro(e.getMessage());
            return resposta;
        }
        ArrayList<MeusPacientes> pacientes = new ArrayList<MeusPacientes>();
        //TODO Ver como colocar ultima data de consulta!
        
        for (ProtocoloConsulta consulta : consultas) {
            Calendar cl = Calendar.getInstance();
            
            Pessoa pessoa = consulta.getPessoa();
            
            cl.setTimeInMillis(pessoa.getDataNascimento().getTime());
            
            CaracteristicaFisicaPessoa altura = pessoa.getCaracteristicasFisicas().stream().filter((caract) -> {
                return caract.getCaracteristica().getId().equals(1L);
            }).findFirst().orElse(null);

            CaracteristicaFisicaPessoa peso = pessoa.getCaracteristicasFisicas().stream().filter((caract) -> {
                return caract.getCaracteristica().getId().equals(2L);
            }).findFirst().orElse(null);
            
            String qProntuario = "from AcompanhamentoMedico acomp join fetch acomp.crm crm join fetch "
                    + "crm.estado estado join fetch acomp.pessoa pessoa where crm.valor = :crm and estado.uf = :uf and p.id = :idPessoa";
            Parametros paramsProntuario = new Parametros();
            paramsProntuario.put("crm", crm);
            paramsProntuario.put("uf", uf.toUpperCase());
            paramsProntuario.put("idPessoa", pessoa.getId());
            
            AcompanhamentoMedico acomp = this.getSingleResult(qProntuario, AcompanhamentoMedico.class, paramsProntuario);
            
            pacientes.add(
                    new MeusPacientes(
                        pessoa.getFoto(),
                        pessoa.getNomeInteiro(),
                        pessoa.getSexo().getNome(),
                        Funcoes.calcularIdade(cl),
                        altura.getValor(),
                        peso.getValor(),
                        (acomp != null),
                        new Date()
                    )
            );
        }
        resposta = new RespostaDomain(pacientes);
        
        return resposta;
    }

    @Override
    public RespostaDomain obterPacienteConsulta(Long crm, Long cpf, String uf) {
        RespostaDomain resposta;
        //TODO O QUE ISSO FAZ? > Obtém o paciente selecionado para iniciar a consulta.
        String query = "from ProtocoloConsulta pConsulta "
                + "join fetch pConsulta.id.pessoa p "
                + "where p.crm.valor = :crm and "
                + "pConsulta.id.dataHora =  ";

        List<ProtocoloConsulta> consultas;
        
        try {
            consultas = this.selectByQuery(query, ProtocoloConsulta.class, new Parametros("crm", crm));
        } catch (Exception e) {
            resposta = new RespostaDomain(null);
            resposta.adicionarMensagemErro("Médico não encontrado para o crm:" + crm);
            resposta.adicionarMensagemErro(e.getMessage());
            return resposta;
        }
        return null;
    }
    
    @Override
    @Transactional
    public RespostaDomain iniciarConsultaEmergencia(Long protocolo, SolicitacaoConsultaEmergencia solicitacao) {
        CRM crmMedico = this.getCRM(solicitacao.getCrm(), "");
        Clinica clinica = this.getClinica(1L); //TODO tirar hard code
        Pessoa pessoa = this.getPessoa(solicitacao.getCpfPaciente());

        Calendar now = Calendar.getInstance();

        PlanoPessoa planoUtilizado = pessoa.getPlanos().stream().filter(plano -> plano.getId().equals(solicitacao.getIdPlano())).findFirst().orElse(null);
        Especialidade especialidade = this.getEspecialidade(solicitacao.getIdEspecialidade());

        ProtocoloConsulta prot
                = new ProtocoloConsulta(crmMedico, pessoa, clinica, now.getTime());

        Consulta consulta = new Consulta();
        consulta.setProtocolo(protocolo);

        consulta.setEspecialidade(especialidade);
        consulta.setStatus(new StatusConsulta(4L)); //Setting consulta para aguardando validação
        consulta.setPlanoUtilizado(planoUtilizado);
        prot.setConsulta(consulta);

        ProtocoloConsulta savedProtocolo = this.saveAndGetResult(protocolo, prot);

        //TODO arrumar também...
        return new RespostaDomain(savedProtocolo);

    }
	
    @Transactional
    private ProtocoloConsulta saveAndGetResult(Long protocolo, ProtocoloConsulta consulta) {
        //TODO Arrumar essa parte
        String q = "from ProtocoloConsulta procto join fetch procto.consulta c where c.protocolo = :protocolo";

        this.persist(consulta);

        ProtocoloConsulta procto = this.getSingleResult(q, ProtocoloConsulta.class, new Parametros("protocolo", protocolo));

        return procto;

    }

    @Override
    @Transactional
    public RespostaDomain iniciarConsulta(Long protocoloConsulta) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        RespostaDomain resposta;
        try {
            Consulta c = this.getConsultaByProtocolo(protocoloConsulta);
            StatusAndamentoConsulta status = this.getStatusAndamentoConsulta(1L);
            AndamentoConsulta andamento
                    = new AndamentoConsulta();

            Date now = Calendar.getInstance().getTime();

            andamento.setHoraAtualizacao(now);
            andamento.setConsulta(c);
            if (c.getAndamento().stream().filter((a) -> a.getStatus().getId().equals(1L)).findFirst().orElse(null) != null) {
                throw new Exception("Essa consulta já foi inicializada!!");
            }
            andamento.setStatus(status);
            //c.getAndamento().add(andamento);
            this.getEntityManager().merge(andamento);
            resposta = new RespostaDomain(new ConfirmacaoInicioConsulta(sdf.format(now), protocoloConsulta, "Iniciada"));
        } catch (Exception e) {
            resposta = new RespostaDomain(null);
            resposta.adicionarMensagemErro(e.getMessage());

        }
        return resposta;
    }

    private Consulta getConsultaByProtocolo(Long protocolo) {
        String q = "from Consulta c join fetch c.andamento where c.protocolo = :protocolo";
        return this.findById(Consulta.class, protocolo);
    }

    private CRM getCRM(Long crm, String uf) {
        String q = "from CRM c where c.valor = :crm and c.uf = :uf";
        Parametros parametros = new Parametros();

        parametros.put("crm", crm);
        parametros.put("uf", uf);

        return this.getSingleResult(q, CRM.class, parametros);
    }

    private Pessoa getPessoa(Long cpf) {
        String q = "from Pessoa p where p.cpf = :cpf";
        return this.getSingleResult(q, Pessoa.class, new Parametros("cpf", cpf));
    }

    private Clinica getClinica(Long idClinica) {

        //TODO pegar clinica atual do médico
        return this.findById(Clinica.class, idClinica);
    }

    private Especialidade getEspecialidade(Long idEspecialidade) {
        return this.findById(Especialidade.class, idEspecialidade);
    }

    private StatusAndamentoConsulta getStatusAndamentoConsulta(Long id) {
        return this.findById(StatusAndamentoConsulta.class, id);
    }

    private Set<SintomaConsulta> getSintomas(Set<Long> ids, Consulta consulta) {
        String q = "from Sintoma s where s.id in :ids";
        List<Sintoma> sintomas = this.selectByQuery(q, Sintoma.class, new Parametros("ids", ids));

        Set<SintomaConsulta> sintCons = sintomas.stream().map((s) -> {
            SintomaConsulta sc = new SintomaConsulta();
            sc.setConsulta(consulta);
            sc.setSintoma(s);
            return sc;
        }).collect(Collectors.toSet());

        return sintCons;
    }
    
    @Override
    @Transactional
    public RespostaDomain iniciarConsultaImediata(Long protocolo, SolicitacaoConsultaImediata solicitacao) {
        CRM medico = this.getCRM(solicitacao.getCrm(), solicitacao.getUf());
        Pessoa paciente = this.getPessoa(solicitacao.getCpfPaciente());
        Clinica clinica = this.getClinica(1L);
        Especialidade especialidade = this.getEspecialidade(solicitacao.getIdEspecialidade());
        StatusConsulta status = this.findById(StatusConsulta.class, 4L); //AGUARDANDO APROVAÇÃO DO PACIENTE
        TipoConsulta tipo = this.findById(TipoConsulta.class, 2L); //CONSULTA IMEDIATA

        ProtocoloConsulta protoc
                = new ProtocoloConsulta();

        protoc.setCrm(medico);
        protoc.setPessoa(paciente);
        protoc.setClinica(clinica);

        Consulta consulta = new Consulta();
        Set<SintomaConsulta> sintomas = this.getSintomas(solicitacao.getSintomas(), consulta);

        consulta.setProtocolo(protocolo);
        consulta.setStatus(status);
        //consulta.setTipo(tipo);
        consulta.setEspecialidade(especialidade);
        consulta.setProtocoloConsulta(protoc);
        consulta.setSintomas(sintomas);
        protoc.setConsulta(consulta);
       
        RespostaDomain resposta;
        try {
          protoc =  this.saveAndGetResult(protocolo, protoc);
          resposta = new RespostaDomain(
                new ConfirmacaoConsultaImediata(protocolo, paciente.getNomeInteiro(), "A consulta foi registrada, aguardando confirmação do paciente"));
        } catch (Exception e) {
            e.printStackTrace();
            resposta = new RespostaDomain(null);
            resposta.adicionarMensagemErro(e.getMessage());
        }
        
        return resposta;
        //TODO Talvez seja necessário complementar essa parte, dependerá se:
        // 1 - O paciente n precise autorizar para começar a consulta (ai será necessário iniciar a consulta)
    }
     
}
