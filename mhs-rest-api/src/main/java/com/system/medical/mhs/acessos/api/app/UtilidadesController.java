/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.system.medical.mhs.acessos.api.app;

import com.system.medical.mhs.dados.mySql.repositories.app.UtilidadesRepositoryMySql;
import com.system.medical.mhs.dominios.RespostaDomain;
import com.system.medical.mhs.dominios.app.acoes.utilidades.AppUtilidades;
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
@RequestMapping("/app/utilidades")
public class UtilidadesController {
	
    @Autowired
    private UtilidadesRepositoryMySql utilidadesRepository;
    
    @RequestMapping("/planos")
    public ResponseEntity<?> getPlanos(){
        RespostaDomain resposta;
        try {
            resposta = new AppUtilidades(this.utilidadesRepository).getPlanos();
            return new ResponseEntity(resposta.get(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    
    @RequestMapping("/especialidades")
    public ResponseEntity<?> getEspecialidades(){
        RespostaDomain resposta;
        try {
            resposta = new AppUtilidades(this.utilidadesRepository).getEspecialidades();
            return new ResponseEntity(resposta.get(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    
    @RequestMapping("/regioes")
    public ResponseEntity<?> getRegioes(){
        RespostaDomain resposta;
        try {
            resposta = new AppUtilidades(this.utilidadesRepository).getRegioes();
            return new ResponseEntity(resposta.get(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
