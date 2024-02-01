package com.system.medical.mhs.dominios.pwa.acoes;

import com.system.medical.mhs.dados.mySql.entities.Pessoa;
import com.system.medical.mhs.dominios.RespostaDomain;
import com.system.medical.mhs.dominios.app.modelos.agendamento.AgendamentoConsulta;
import com.system.medical.mhs.dominios.pwa.interfaces.IAgendaRepository;
import com.system.medical.mhs.dominios.pwa.modelos.agenda.DadosConsulta;
import com.system.medical.mhs.dominios.pwa.modelos.agenda.AgendarConsulta;
import com.system.medical.mhs.dominios.pwa.modelos.agenda.ConfirmacaoAlteracao;
import com.system.medical.mhs.dominios.pwa.modelos.agenda.ConsultaAgenda;
import com.system.medical.mhs.dominios.pwa.modelos.agenda.ConsultasRecentes;
import com.system.medical.mhs.dominios.pwa.modelos.agenda.DisponibilidadeAgenda;
import com.system.medical.mhs.dominios.pwa.modelos.agenda.ExamesRecentes;
import com.system.medical.mhs.dominios.pwa.modelos.agenda.HorarioIndisponivel;
import com.system.medical.mhs.dominios.pwa.modelos.agenda.MedicosClinica;
import com.system.medical.mhs.dominios.pwa.modelos.agenda.PlanosClinica;
import com.system.medical.mhs.dominios.pwa.modelos.agenda.SolicitacaoAlteracaoHorarioConsulta;
import com.system.medical.mhs.dominios.pwa.modelos.agenda.Tratamentos;
import com.system.medical.mhs.dominios.pwa.modelos.dashboard.PlanoSaude;
import com.system.medical.mhs.dominios.pwa.modelos.pacientes.AlergiasConhecidas;
import com.system.medical.mhs.dominios.pwa.modelos.pacientes.Dados;
import com.system.medical.mhs.dominios.pwa.modelos.pacientes.HistoricoDoencas;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * @author Leonardo Ariel
 *
 */
public class ActionsAgendaRepository {

    private IAgendaRepository agendaRepository;

    public ActionsAgendaRepository(IAgendaRepository agendaRepository) {
        this.agendaRepository = agendaRepository;
    }

    public RespostaDomain<Dados> obterDados(Long cpf) {
        return this.agendaRepository.obterDados(cpf);
    }

    public RespostaDomain<PlanoSaude> obterPlanoSaude(Long cpf) {
        return this.agendaRepository.obterPlanoSaude(cpf);
    }

    public RespostaDomain<AlergiasConhecidas> consultarAlergias(Long cpf) {
        return this.agendaRepository.consultarAlergias(cpf);
    }

    public RespostaDomain<Tratamentos> consultarTratamentos(Long cpf) {
        return this.agendaRepository.consultarTratamentos(cpf);
    }

    public RespostaDomain<HistoricoDoencas> consultarHistorico(Long cpf) {
        return this.agendaRepository.consultarHistorico(cpf);
    }

    public RespostaDomain<ExamesRecentes> consultarExamesRecentes(Long cpf) {
        return this.agendaRepository.consultarExamesRecentes(cpf);
    }

    public RespostaDomain<ConsultasRecentes> consultarConsultasRecentes(Long cpf) {
        return this.agendaRepository.consultarConsultasRecentes(cpf);
    }

    public RespostaDomain<DisponibilidadeAgenda> buscarDisponibilidade(Long crm, String uf, Long dia) {
        Calendar cl = Calendar.getInstance();
        cl.setTimeInMillis(dia);

        return this.agendaRepository.obterDisponibilidade(crm, uf, cl);
    }

    public RespostaDomain<DisponibilidadeAgenda> indisponibilizarHorario(HorarioIndisponivel indisponibilidade) {
        Pessoa solicitante = new Pessoa();
        solicitante.setId(1L);

        return this.agendaRepository.indisponibilizar(indisponibilidade, solicitante);
    }
    
    public RespostaDomain<ConsultaAgenda> buscarConsultasDia(Long crm, String uf, Long dia){
        return this.agendaRepository.obterConsultasDia(crm, uf, dia);
    }
    
    public RespostaDomain<ConsultaAgenda> buscarConsultasSemana(Long crm, String uf, Long dia){
        return this.agendaRepository.obterConsultasSemana(crm, uf, dia);
    }
    
    public RespostaDomain<ConsultaAgenda> buscarConsultasMes(Long crm, String uf, Long dia){
        return this.agendaRepository.obterConsultasMes(crm, uf, dia);
    }
    
    public RespostaDomain<ConfirmacaoAlteracao> alterarHorarioConsulta(SolicitacaoAlteracaoHorarioConsulta solicitacao){
        return this.agendaRepository.alterarHorarioConsulta(solicitacao);
    }
    
	public RespostaDomain<DadosConsulta> obterDadosConsulta(Long cpf) {
		return this.agendaRepository.obterDadosConsulta(cpf);
	}

	public RespostaDomain<MedicosClinica> obterMedicosClinica(Long idClinica) {
		return this.agendaRepository.obterMedicosClinica(idClinica);
	}
	

	public RespostaDomain<PlanosClinica> obterPlanosClinica(Long idClinica) {
		return this.agendaRepository.obterPlanosClinica(idClinica);
	}
	
    public RespostaDomain<ConfirmacaoAlteracao> adicionarNovaConsulta(AgendarConsulta agendar){
    	//System.out.println(agendar.toString());
        Long protocolo = generateProtocolo(agendar);
        return this.agendaRepository.adicionarNovaConsulta(protocolo, agendar);
    }
    
    private Long generateProtocolo(AgendarConsulta agendar){
        SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMddHHmm");
        Calendar registered = Calendar.getInstance();
        registered.setTimeInMillis(agendar.getDataHorario());
        
        Long regControl = new Long(fmt.format(registered.getTime()));
        
        Long protocolo = this.protocolo(
                regControl, agendar.getIdPaciente());
        
        return protocolo;
    }
    
    private Long protocolo(Long... valores){
        String protStr = "";
        for(Long valor : valores){
            protStr += valor.toString();
        }
        
        return new Long(protStr);
    }
}
