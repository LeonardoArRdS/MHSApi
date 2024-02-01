package com.system.medical.mhs.dominios.app.interfaces;

import com.system.medical.mhs.dominios.RespostaDomain;

/**
 * @author Leonardo Ariel
 *
 */
public interface IProntuarioRepository {
	RespostaDomain consultarConsultasRecentes(Long cpf);
	RespostaDomain consultarConsultasRecentesCompleto(Long cpf);
}
