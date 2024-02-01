package com.system.medical.mhs.acessos.api.pwa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.system.medical.mhs.dados.mySql.repositories.pwa.PacientesHomeRepository;
import com.system.medical.mhs.dominios.RespostaDomain;
import com.system.medical.mhs.dominios.pwa.acoes.ActionsPacientesHome;
import com.system.medical.mhs.dominios.pwa.acoes.pacientes.home.ActionsConsulta;
import com.system.medical.mhs.dominios.pwa.modelos.pacientes.home.SolicitacaoConsultaEmergencia;
import com.system.medical.mhs.dominios.pwa.modelos.pacientes.home.SolicitacaoConsultaImediata;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author Leonardo Ariel
 *
 */
@RequestMapping("/pwa/pacientes/home")
@RestController
public class PacientesHomeController {
	@Autowired
	private PacientesHomeRepository pacientesHomeRepository;
        
	
//<<<<<<< HEAD
	@RequestMapping("/pacienteConsulta")
    public ResponseEntity<?> getPacienteConsulta(Long crm, Long cpf, String uf) throws Exception {
    	if(pacientesHomeRepository.obterPacienteConsulta(crm, cpf, uf).contemErros()){ throw new Exception(pacientesHomeRepository.obterPacienteConsulta(crm, cpf, uf).converterErrosParaString()); }
    	try {
        	return new ResponseEntity(new ActionsPacientesHome(this.pacientesHomeRepository).obterPacienteConsulta(crm, cpf, uf).get(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(),
                    HttpStatus.BAD_REQUEST);
        }
    }
//=======
	@RequestMapping("/meusPacientes")
        public ResponseEntity<?> getMeusPacientes(Long crm, String uf) throws Exception {
		if(pacientesHomeRepository.buscarMeusPacientes(crm, uf).contemErros()){ throw new Exception(pacientesHomeRepository.buscarMeusPacientes(crm, uf).converterErrosParaString()); }
            try {
                    return new ResponseEntity(new ActionsPacientesHome(this.pacientesHomeRepository).buscarMeusPacientes(crm, uf).get(), HttpStatus.OK);
            } catch (Exception e) {
                return new ResponseEntity(e.getMessage(),
                        HttpStatus.BAD_REQUEST);
            }
        }
	
        @RequestMapping(method = RequestMethod.POST, value = "/consulta/emergencia")
        public ResponseEntity<?> iniciarConsultaEmergencia(@RequestBody SolicitacaoConsultaEmergencia solicitacao){
            try {
                return new ResponseEntity(new ActionsConsulta(this.pacientesHomeRepository).iniciarConsultaEmergencia(solicitacao).get(), HttpStatus.OK);
            } catch (Exception e) {
                return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
            }
        }
        
        @RequestMapping(method = RequestMethod.POST, value = "/consulta/imediata")
        public ResponseEntity<?> iniciarConsultaImediata(@RequestBody SolicitacaoConsultaImediata solicitacao){
            try {
                RespostaDomain resposta = new ActionsConsulta(this.pacientesHomeRepository).iniciarConsultaImediata(solicitacao);
                if(resposta.contemErros())
                    throw new Exception(resposta.converterErrosParaString());
                
                return new ResponseEntity(resposta.get(), HttpStatus.OK);
            } catch (Exception e) {
                return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
            }
        }
        
        
        @RequestMapping(method = RequestMethod.POST, value = "/consulta/iniciar")
        public ResponseEntity<?> iniciarConsulta(@RequestParam Long protocolo){
            try {
                RespostaDomain resposta = new ActionsConsulta(this.pacientesHomeRepository).iniciarConsulta(protocolo);
                if(resposta.contemErros())
                    throw new Exception(resposta.converterErrosParaString());
                return new ResponseEntity(resposta.get(), HttpStatus.OK);
            } catch (Exception e) {
                return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
            }
        }
//>>>>>>> 92cbf70cd7d5a0db25ee7d3117bbf9914266b14c
}
