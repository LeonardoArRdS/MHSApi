package com.system.medical.mhs.dominios.app.acoes;

import java.util.List;

import com.system.medical.mhs.dominios.RespostaDomain;
import com.system.medical.mhs.dominios.app.interfaces.IProntuarioRepository;
import com.system.medical.mhs.dominios.app.modelos.prontuario.ConsultasRecentes;
import com.system.medical.mhs.dominios.app.modelos.prontuario.ConsultasRecentesCompleto;

/**
 * @author Leonardo Ariel
 *
 */
public class ActionsProntuario{
	
	private IProntuarioRepository prontuarioRepository;
	
	public ActionsProntuario(IProntuarioRepository prontuarioRepository) {
		this.prontuarioRepository = prontuarioRepository;
	}

	public RespostaDomain<List<ConsultasRecentes>> consultarConsultasRecentes(Long cpf) {
		return this.prontuarioRepository.consultarConsultasRecentes(cpf);
	}
	
	public RespostaDomain<List<ConsultasRecentesCompleto>> consultarConsultasRecentesCompleto(Long cpf) {
		return this.prontuarioRepository.consultarConsultasRecentesCompleto(cpf);
	}
	
}
