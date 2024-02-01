package com.system.medical.mhs.dominios.pwa.interfaces.dashboard;

import com.system.medical.mhs.dominios.RespostaDomain;

/**
*
* @author Leonardo Ariel
*/
public interface IMedicosRepository {
	RespostaDomain buscarAgenda(Long crm, String UF);
	RespostaDomain buscarProximasConsultas(Long crm, String UF);
	RespostaDomain buscarUltimasConsultas(Long crm, String UF);
}
