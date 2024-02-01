package com.system.medical.mhs.acessos.api.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.system.medical.mhs.dados.mySql.repositories.app.ProntuarioRepositoryMysql;
import com.system.medical.mhs.dominios.app.acoes.ActionsProntuario;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Leonardo Ariel
 *
 */

@RequestMapping("/app/prontuario")
public class ProntuarioController {
	@Autowired
	private ProntuarioRepositoryMysql prontuarioRepository;
	
	@RequestMapping("/consultas/recentes")
	public ResponseEntity<?> getConsultasRecentes(Long cpf) throws Exception{
		
		try {
            return new ResponseEntity(new ActionsProntuario(this.prontuarioRepository).consultarConsultasRecentes(cpf).get(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
	}
	
	@RequestMapping("/consultas/recentes/completo")
	public ResponseEntity<?> getConsultasRecentesCompleto(Long cpf) throws Exception{
		if(this.prontuarioRepository.consultarConsultasRecentesCompleto(cpf).contemErros()){ throw new Exception(this.prontuarioRepository.consultarConsultasRecentesCompleto(cpf).converterErrosParaString()); }
		try {
            return new ResponseEntity(new ActionsProntuario(this.prontuarioRepository).consultarConsultasRecentesCompleto(cpf).get(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
	}
}
