package com.system.medical.mhs.dominios.pwa.interfaces;

import com.system.medical.mhs.dados.mySql.entities.Pessoa;
import com.system.medical.mhs.dominios.RespostaDomain;
import com.system.medical.mhs.dominios.pwa.modelos.agenda.AgendarConsulta;
import com.system.medical.mhs.dominios.pwa.modelos.agenda.ConsultaAgenda;
import com.system.medical.mhs.dominios.pwa.modelos.agenda.DadosConsulta;
import com.system.medical.mhs.dominios.pwa.modelos.agenda.HorarioIndisponivel;
import com.system.medical.mhs.dominios.pwa.modelos.agenda.SolicitacaoAlteracaoHorarioConsulta;
import java.util.Calendar;

/**
 * @author Leonardo Ariel
 *
 */
public interface IAgendaRepository {
	//Perfil
	RespostaDomain obterDados(Long cpf);
	RespostaDomain obterPlanoSaude(Long cpf);
	RespostaDomain consultarAlergias(Long cpf);
	RespostaDomain consultarTratamentos(Long cpf);
	RespostaDomain consultarHistorico(Long cpf);
	RespostaDomain consultarExamesRecentes(Long cpf);
	RespostaDomain consultarConsultasRecentes(Long cpf);
        
    RespostaDomain obterDisponibilidade(Long crm, String uf, Calendar dataHora);
    RespostaDomain indisponibilizar(HorarioIndisponivel indisponibilidade, Pessoa solicitante);
    RespostaDomain obterConsultasDia(Long crm, String uf, Long dia);
    RespostaDomain alterarHorarioConsulta(SolicitacaoAlteracaoHorarioConsulta solicitacao);
    //RespostaDomain adicionarNovaConsulta(Long protocolo, DadosConsulta dados);
	RespostaDomain obterConsultasSemana(Long crm, String uf, Long dia);
	RespostaDomain obterConsultasMes(Long crm, String uf, Long dia);
	RespostaDomain adicionarNovaConsulta(Long protocolo, AgendarConsulta agendar);
	RespostaDomain obterDadosConsulta(Long cpf);
		
	RespostaDomain obterMedicosClinica(Long idClinica);
	RespostaDomain obterPlanosClinica(Long idClinica);
}
