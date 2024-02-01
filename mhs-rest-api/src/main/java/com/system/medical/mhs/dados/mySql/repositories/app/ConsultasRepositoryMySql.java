/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.system.medical.mhs.dados.mySql.repositories.app;

import com.system.medical.mhs.dados.mySql.AbstractRepository;
import com.system.medical.mhs.dados.mySql.entities.CRM;
import com.system.medical.mhs.dados.mySql.entities.Clinica;
import com.system.medical.mhs.dados.mySql.entities.CompartilhamentoProntuario;
import com.system.medical.mhs.dados.mySql.entities.CompartilhamentoProntuarioConsulta;
import com.system.medical.mhs.dados.mySql.entities.Consulta;
import com.system.medical.mhs.dados.mySql.entities.Especialidade;
import com.system.medical.mhs.dados.mySql.entities.Pessoa;
import com.system.medical.mhs.dados.mySql.entities.PlanoPessoa;
import com.system.medical.mhs.dados.mySql.entities.ProtocoloConsulta;
import com.system.medical.mhs.dados.mySql.entities.StatusCompartilhamento;
import com.system.medical.mhs.dados.mySql.entities.StatusConsulta;
import com.system.medical.mhs.dados.mySql.entities.TipoConsulta;
import com.system.medical.mhs.dados.mySql.entities.log.LogConsulta;
import com.system.medical.mhs.dominios.RespostaDomain;
import com.system.medical.mhs.dominios.app.modelos.agendamento.AgendamentoConsulta;
import com.system.medical.mhs.dominios.app.modelos.agendamento.ConsultaAgendada;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.transaction.Transactional;
import org.springframework.stereotype.Repository;
import com.system.medical.mhs.dominios.app.interfaces.agendamento.IAgendamentoConsultasRepository;
import com.system.medical.mhs.dominios.app.modelos.agendamento.ConfirmacaoCompartilhamento;
import com.system.medical.mhs.dominios.app.modelos.agendamento.SolicitacaoCompartilhamento;
import java.util.Calendar;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Felipe
 */
@Repository
public class ConsultasRepositoryMySql extends AbstractRepository implements IAgendamentoConsultasRepository {

    @Autowired
    public StatusConsultaRepositoryMySql statusConsultaRepository;

    @Override
    @Transactional
    public RespostaDomain agendarConsulta(Long protocolo, AgendamentoConsulta agendamento) {
        CRM crm = this.getCRM(agendamento.getCrm(), "");
        Clinica clinica = this.getClinica(agendamento.getIdClinica());
        Pessoa pessoa = this.getPessoa(agendamento.getIdPaciente());
        PlanoPessoa planoUtilizado = pessoa.getPlanos().stream().filter(plano -> plano.getId().equals(agendamento.getIdPlano())).findFirst().orElse(null);
        Especialidade especialidade = this.getEspecialidade(agendamento.getIdEspecialidade());

        ProtocoloConsulta prot
                = new ProtocoloConsulta(crm, pessoa, clinica, new Date(agendamento.getDataHorario()));

        Consulta consulta = new Consulta();
        consulta.setProtocolo(protocolo);

        consulta.setEspecialidade(especialidade);
        consulta.setStatus(new StatusConsulta(4L)); //Setting consulta para aguardando validação
        consulta.setPlanoUtilizado(planoUtilizado);
        consulta.setTipo(new TipoConsulta(1L));
        prot.setConsulta(consulta);

        ProtocoloConsulta savedProtocolo = this.saveAndGetResult(protocolo, prot);

        try {
            LogConsulta log = this.statusConsultaRepository
                    .updateStatusConsulta(savedProtocolo, pessoa, StatusConsultaRepositoryMySql.AGUARDANDO_CONFIRMACAO_MEDICO);

        } catch (Exception e) {
            e.printStackTrace();

        }

        return new RespostaDomain(this.tratarConsulta(savedProtocolo));
    }

    private ConsultaAgendada tratarConsulta(ProtocoloConsulta protocoloConsulta) {
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

    private StatusCompartilhamento getStatusCompartilhamento(Long id) {
        return this.findById(StatusCompartilhamento.class, id);
    }

    @Override
    @Transactional
    public RespostaDomain shareProntuarioConsulta(SolicitacaoCompartilhamento solicitacao) {
        CRM crm = this.getCRM(solicitacao.getCrm(), solicitacao.getUf());
        Pessoa paciente = this.getPessoa(solicitacao.getIdPaciente());
        CompartilhamentoProntuario share = new CompartilhamentoProntuario();
        StatusCompartilhamento status = this.getStatusCompartilhamento(1L);

        Calendar inicioVigencia
                = Calendar.getInstance();

        //TODO
        //Setando 12 hrs antes;
        //Colocar isso no BD
        inicioVigencia.setTimeInMillis(solicitacao.getDataHoraConsulta());
        inicioVigencia.add(Calendar.HOUR_OF_DAY, -12);

        //Setando 36 hrs após consulta;
        Calendar fimVigencia
                = Calendar.getInstance();
        fimVigencia.setTimeInMillis(solicitacao.getDataHoraConsulta());
        fimVigencia.add(Calendar.HOUR_OF_DAY, 36);

        //Setando expiração da solicitação
        //Sei q é a mesma coisa q inicioVigencia, mas vai q muda...
        Calendar exp
                = Calendar.getInstance();
        exp.setTimeInMillis(inicioVigencia.getTimeInMillis());

        share.setCrm(crm);
        share.setPessoa(paciente);
        share.setProtocolo(solicitacao.getProtocoloConsulta());

        share.setInicioVigencia(inicioVigencia.getTime());
        share.setFimVigencia(fimVigencia.getTime());
        share.setStatus(status);

        CompartilhamentoProntuarioConsulta shareProntuarioConsulta = new CompartilhamentoProntuarioConsulta();

        try {
            shareProntuarioConsulta.setConsulta(this.getConsulta(solicitacao.getProtocoloConsulta()));
            share.setProntuarioConsulta(shareProntuarioConsulta);
            this.getEntityManager().merge(share);
        } catch (Exception e) {
            e.printStackTrace();
            return new RespostaDomain(null);
        }
        
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        
        ConfirmacaoCompartilhamento
                confirmacao = new ConfirmacaoCompartilhamento(inicioVigencia.getTimeInMillis(), 
                        fimVigencia.getTimeInMillis(), sdf.format(new Date(solicitacao.getDataHoraConsulta())), solicitacao.getProtocoloConsulta());

        return new RespostaDomain(confirmacao);
    }

    private Consulta getConsulta(Long protocolo) {
        return this.findById(Consulta.class, protocolo);
    }

    @Transactional
    private CompartilhamentoProntuario saveCompartilhamento(CompartilhamentoProntuario share) {
        
        CompartilhamentoProntuario savedShare = this.getEntityManager().merge(share);
        this.getEntityManager().flush();
        this.getEntityManager().clear();
        return savedShare;
    }

}
