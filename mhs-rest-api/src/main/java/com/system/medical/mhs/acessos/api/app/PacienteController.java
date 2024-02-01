/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.system.medical.mhs.acessos.api.app;

import com.system.medical.mhs.dados.mySql.repositories.app.ConsultasPacienteRepositoryMySql;
import com.system.medical.mhs.dados.mySql.repositories.app.ExamesRepositoryMySql;
import com.system.medical.mhs.dominios.app.acoes.ActionsConsultaPaciente;
import com.system.medical.mhs.dominios.app.acoes.ActionsExame;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author Felipe Mariano
 */
@Controller
@RequestMapping("/app/pacientes")
public class PacienteController {
    
    @Autowired
    private ConsultasPacienteRepositoryMySql consultasRepository;
    
    @Autowired
    private ExamesRepositoryMySql examesRepository;
    
    @RequestMapping("/consultas/periodo")
    public ResponseEntity<?> getConsultasPorPeriodo(@RequestParam Long inicio, @RequestParam Long fim){
        try{
            return new ResponseEntity(null, HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity(e.getMessage(),
                            HttpStatus.BAD_REQUEST);
        }
    }
    
    @RequestMapping("/consultas/futuras")
    public ResponseEntity<?> getConsultasFuturas(@RequestParam Long cpf, @RequestParam(required = false) Integer limite){
        try{
            return new ResponseEntity(
                    new ActionsConsultaPaciente(this.consultasRepository).getConsultasFuturas(cpf, limite).get(), HttpStatus.OK);
        }catch(Exception e){
           return new ResponseEntity(e.getMessage(),
                            HttpStatus.BAD_REQUEST);
        }
    }
    
    @RequestMapping("/exames/futuros")
    public ResponseEntity<?> getExamesFuturos(@RequestParam Long cpf, @RequestParam(required = false) Integer limite){
        try{
            return new ResponseEntity(
                    new ActionsExame(this.examesRepository).getExamesFuturos(cpf, limite).get(), HttpStatus.OK);
        }catch(Exception e){
           return new ResponseEntity(e.getMessage(),
                            HttpStatus.BAD_REQUEST);
        }
    }
    
}
