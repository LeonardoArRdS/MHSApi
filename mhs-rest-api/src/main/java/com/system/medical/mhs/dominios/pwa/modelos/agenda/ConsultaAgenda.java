/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.system.medical.mhs.dominios.pwa.modelos.agenda;

import java.util.Date;

/**
 *
 * @author Felipe
 */
public class ConsultaAgenda {
    private Long protocolo;
    private String paciente;
    private Long cpf;//Remover
    private String hora;
    private Date data;//Remover
    private String plano;

    public ConsultaAgenda(Long protocolo, String paciente, Long cpf, String hora, Date data, String plano) {
        this.protocolo = protocolo;
        this.paciente = paciente;
        this.cpf = cpf;
        this.hora = hora;
        this.data = data;
        this.plano = plano;
    }

    public Long getCpf() { return cpf; }
    public Date getData() { return data; }

	public Long getProtocolo() { return protocolo; }

	public String getPaciente() { return paciente; }

    public String getHora() { return hora; }

    public String getPlano() { return plano; }   
}