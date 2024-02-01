/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.system.medical.mhs.acessos.api.app;

import com.system.medical.mhs.dados.mySql.repositories.app.ConsultasRepositoryMySql;
import com.system.medical.mhs.dominios.app.acoes.agendamento.ActionsConsulta;
import com.system.medical.mhs.dominios.app.acoes.agendamento.ActionsCompartilhamentoProntuario;
import com.system.medical.mhs.dominios.app.modelos.agendamento.AgendamentoConsulta;
import com.system.medical.mhs.dominios.app.modelos.agendamento.SolicitacaoCompartilhamento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author Felipe
 */
@Controller
@RequestMapping("/app/consultas")
public class ConsultaController {  
    @Autowired
    private ConsultasRepositoryMySql consultasRepository;
    
    @RequestMapping(method = RequestMethod.POST, value = "/agendar")
    public ResponseEntity<?> agendarConsulta(@RequestBody AgendamentoConsulta agendamento){
        try {
            return new ResponseEntity(new ActionsConsulta(this.consultasRepository).agendar(agendamento).get(), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }        
    }
    
    @RequestMapping(method = RequestMethod.POST, value = "/prontuario/share")
    public ResponseEntity<?> compartilharProntuario(@RequestBody SolicitacaoCompartilhamento solicitacao){
        try {
            return new ResponseEntity(new ActionsCompartilhamentoProntuario(this.consultasRepository).compartilhar(solicitacao).get(), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
