package com.system.medical.mhs.dominios.pwa.acoes.dashboard;

import com.system.medical.mhs.dominios.RespostaDomain;
import com.system.medical.mhs.dominios.pwa.interfaces.dashboard.IPlanoRepository;
import com.system.medical.mhs.dominios.pwa.modelos.dashboard.PlanoSaude;

/**
 * @author Leonardo Ariel
 *
 */
public class ActionsPlanoRepository {
	private IPlanoRepository planoRepository;
	
	public ActionsPlanoRepository(IPlanoRepository planoRepository) {
		this.planoRepository = planoRepository;
	}
	
	public RespostaDomain<PlanoSaude> validarNumero(String empresa,Long numero){
		return this.planoRepository.validarNumero(empresa, numero);
	}
}
