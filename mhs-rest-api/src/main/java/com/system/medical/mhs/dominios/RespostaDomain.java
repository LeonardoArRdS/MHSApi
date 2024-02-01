/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.system.medical.mhs.dominios;

import com.system.medical.mhs.dominios.RespostaBase;

/**
 *
 * @author Felipe
 */
public class RespostaDomain<T> extends RespostaBase {

    private T objeto;
    
    public RespostaDomain(T objeto) {
        this.objeto = objeto;
    }
    
    public T get(){
        return this.objeto;
    }
}
