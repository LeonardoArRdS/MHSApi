/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.system.medical.mhs.dominios.app.interfaces.forms;

import com.system.medical.mhs.dominios.RespostaDomain;
import com.system.medical.mhs.dominios.app.modelos.forms.FormsResposta;

import java.util.List;

/**
 *
 * @author Felipe
 */
public interface IFormsRepository {
    RespostaDomain buscarPerguntasIniciais();
    RespostaDomain responder(List<FormsResposta> respostas);
}
