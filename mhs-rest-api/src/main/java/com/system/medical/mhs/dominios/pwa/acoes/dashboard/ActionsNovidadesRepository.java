package com.system.medical.mhs.dominios.pwa.acoes.dashboard;

import java.util.List;

import com.system.medical.mhs.dominios.RespostaDomain;
import com.system.medical.mhs.dominios.pwa.interfaces.dashboard.INovidadesRepository;
import com.system.medical.mhs.dominios.pwa.modelos.dashboard.Novidade;

/**
 * @author Leonardo Ariel
 *
 */
public class ActionsNovidadesRepository {
	private INovidadesRepository novidadesRepository;
	
	public ActionsNovidadesRepository(INovidadesRepository novidadesRepository) {
		this.novidadesRepository = novidadesRepository;
	}
	
	public RespostaDomain<List<Novidade>> buscarNovidadesPacientes(Long crm, String uf){
		return this.novidadesRepository.buscarNovidadesPacientes(crm, uf);
	}
}