/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.system.medical.mhs.dados.mySql.entities;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author Felipe
 */
@Entity
@Table(name = "MHS.compartilhamento_prontuario_consulta")
public class CompartilhamentoProntuarioConsulta implements Serializable {
    @Id
    @OneToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "protocolo_compartilhamento")
    private CompartilhamentoProntuario compartilhamento;
    
    @ManyToOne(cascade = {CascadeType.MERGE})
    @JoinColumn(name = "protocolo_consulta")
    private Consulta consulta;

    public CompartilhamentoProntuarioConsulta() {
    }

    public CompartilhamentoProntuario getCompartilhamento() {
        return compartilhamento;
    }

    public void setCompartilhamento(CompartilhamentoProntuario compartilhamento) {
        this.compartilhamento = compartilhamento;
    }

    public Consulta getConsulta() {
        return consulta;
    }

    public void setConsulta(Consulta consulta) {
        this.consulta = consulta;
    }
    
    
}
