/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.system.medical.mhs.acessos.api.app;

import com.system.medical.mhs.dados.mySql.repositories.app.FormsRepository;
import com.system.medical.mhs.dominios.app.acoes.ActionsForms;
import com.system.medical.mhs.dominios.app.modelos.forms.FormsResposta;
import java.util.List;
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
@RequestMapping("/app/forms")
public class FormsController {
    
    @Autowired
    private FormsRepository formsRepository;
    
    @RequestMapping("/inicial/perguntas")
    public ResponseEntity<?> getPerguntasIniciais(){
        try {
            return new ResponseEntity(new ActionsForms(this.formsRepository).buscarPerguntasIniciais().get(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }
    
    @RequestMapping(method = RequestMethod.POST, value = "/inicial/responder")
    public ResponseEntity<?> responder(@RequestBody List<FormsResposta> respostas){
        try {
            return new ResponseEntity(new ActionsForms(this.formsRepository).responder(respostas).get(), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        
    }
}
