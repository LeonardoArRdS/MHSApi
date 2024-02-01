package com.system.medical.mhs.dominios.pwa.acoes.dashboard;

import com.system.medical.mhs.dominios.RespostaDomain;
import com.system.medical.mhs.dominios.pwa.interfaces.dashboard.IMedicosRepository;
import com.system.medical.mhs.dominios.pwa.modelos.dashboard.AgendaDia;
import com.system.medical.mhs.dominios.pwa.modelos.dashboard.ProximasConsultas;
import com.system.medical.mhs.dominios.pwa.modelos.dashboard.UltimasConsultas;

/**
 * @author Leonardo Ariel
 *
 */
public class ActionsMedicosRepository {
	private IMedicosRepository medicoRepository;

	public ActionsMedicosRepository(IMedicosRepository medicoRepository) {
		this.medicoRepository = medicoRepository;
	}
	
	public RespostaDomain<AgendaDia> buscarAgenda(Long crm, String uf){
		return this.medicoRepository.buscarAgenda(crm, uf);
	}
	public RespostaDomain<ProximasConsultas> buscarProximasConsultas(Long crm, String uf){
		return this.medicoRepository.buscarProximasConsultas(crm, uf);
	}
	public RespostaDomain<UltimasConsultas> buscarUltimasConsultas(Long crm, String uf){
		return this.medicoRepository.buscarUltimasConsultas(crm, uf);
	}
}
