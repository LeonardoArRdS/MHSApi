/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.system.medical.mhs.dominios.app.acoes;

import com.system.medical.mhs.dominios.RespostaDomain;
import com.system.medical.mhs.dominios.app.interfaces.forms.IFormsRepository;
import com.system.medical.mhs.dominios.app.modelos.forms.FormsPergunta;
import com.system.medical.mhs.dominios.app.modelos.forms.FormsResposta;

import java.util.List;

/**
 *
 * @author Felipe
 */
public class ActionsForms {
    private IFormsRepository formsRepository;

    public ActionsForms(IFormsRepository formsRepository) {
        this.formsRepository = formsRepository;
    }
    
    public RespostaDomain<List<FormsPergunta>> buscarPerguntasIniciais(){
        return this.formsRepository.buscarPerguntasIniciais();
    }
    
    public RespostaDomain<Boolean> responder(List<FormsResposta> respostas){
        return this.formsRepository.responder(respostas);
    }
}
