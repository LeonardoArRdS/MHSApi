/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.system.medical.mhs.dominios.app.interfaces;

import com.system.medical.mhs.dominios.RespostaDomain;

/**
 *
 * @author Felipe
 */
public interface IConsultasPacienteRepository {
    RespostaDomain getConsultasFuturas(Long CPF, Integer rows);
}