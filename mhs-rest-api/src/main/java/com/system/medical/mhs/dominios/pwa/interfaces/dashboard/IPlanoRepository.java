package com.system.medical.mhs.dominios.pwa.interfaces.dashboard;

import com.system.medical.mhs.dominios.RespostaDomain;

/**
*
* @author Leonardo Ariel
*/
public interface IPlanoRepository {
	RespostaDomain validarNumero(String empresa, Long numero);
}
