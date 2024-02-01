package com.system.medical.mhs.dominios.app.modelos.prontuario;

import java.util.ArrayList;
import java.util.Date;

import com.system.medical.mhs.dominios.pwa.modelos.Tratamento;

public class ConsultasRecentesCompleto {
	private Date data;
	private String especialidade;
	private String conclusoes;
	private ArrayList<String> sintomas;
	private ArrayList<Tratamento> tratamentos;
	private String comentarioExames;
	private String comentariosGerais;
	
	public ConsultasRecentesCompleto(
			Date data,
			String especialidade,
			String conclusoes,
			ArrayList<String> sintomas,
			ArrayList<Tratamento> tratamentos,
			String comentarioExames,
			String comentariosGerais
			){
		this.data = data;
		this.especialidade = especialidade;
		this.conclusoes = conclusoes;
		this.sintomas = sintomas;
		this.tratamentos = tratamentos;
		this.comentarioExames = comentarioExames;
		this.comentariosGerais = comentariosGerais;
	}

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getEspecialidade() {  return especialidade; }
    
    public String getConclusoes() { return conclusoes; }

    public ArrayList<String> getSintomas() { return sintomas; }
    
    public ArrayList<Tratamento> getTratamentos() { return tratamentos; }

    public void setTratamentos(ArrayList<Tratamento> tratamentos) { this.tratamentos = tratamentos; }

    public String getComentarioExames() { return comentarioExames; }

    public String getComentariosGerais() { return comentariosGerais; }
}
