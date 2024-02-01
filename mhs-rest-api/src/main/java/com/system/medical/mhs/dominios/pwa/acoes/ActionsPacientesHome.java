package com.system.medical.mhs.dominios.pwa.acoes;

import com.system.medical.mhs.dominios.RespostaDomain;
import com.system.medical.mhs.dominios.pwa.interfaces.IPacientesHomeRepository;
import com.system.medical.mhs.dominios.pwa.modelos.pacientes.home.MeusPacientes;
import com.system.medical.mhs.dominios.pwa.modelos.pacientes.home.PacienteConsulta;

/**
 * @author Leonardo Ariel
 *
 */
public class ActionsPacientesHome {
	private IPacientesHomeRepository pacientesRepository;

	public ActionsPacientesHome(IPacientesHomeRepository pacientesRepository) {
		this.pacientesRepository = pacientesRepository;
	}
	
	public RespostaDomain<MeusPacientes> buscarMeusPacientes(Long crm, String uf){
		return this.pacientesRepository.buscarMeusPacientes(crm, uf);
	}
	
	public RespostaDomain<PacienteConsulta> obterPacienteConsulta(Long crm, Long cpf, String uf){
		return this.pacientesRepository.obterPacienteConsulta(crm, cpf, uf);
	}
}
