package com.system.medical.mhs.dominios.pwa.modelos.pacientes;

import java.util.ArrayList;
import java.util.Date;

import com.system.medical.mhs.dominios.pwa.modelos.Tratamento;
import java.util.List;

public class ExamesRecentesCompleto {
	private Date data;
	private String especialidade;
	private List<String> conclusoes;
	private ArrayList<String> sintomas;
	private ArrayList<Tratamento> tratamentos;
	private String comentarioExames;
	private List<String> comentariosGerais;
	
	public ExamesRecentesCompleto(
			Date data,
			String especialidade,
			List<String> conclusoes,
			ArrayList<String> sintomas,
			ArrayList<Tratamento> tratamentos,
			String comentarioExames,
			List<String> comentariosGerais
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

    public String getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }

    public List<String> getConclusoes() {
        return conclusoes;
    }

    public void setConclusoes(List<String> conclusoes) {
        this.conclusoes = conclusoes;
    }

    public ArrayList<String> getSintomas() {
        return sintomas;
    }

    public void setSintomas(ArrayList<String> sintomas) {
        this.sintomas = sintomas;
    }

    public ArrayList<Tratamento> getTratamentos() {
        return tratamentos;
    }

    public void setTratamentos(ArrayList<Tratamento> tratamentos) {
        this.tratamentos = tratamentos;
    }

    public String getComentarioExames() {
        return comentarioExames;
    }

    public void setComentarioExames(String comentarioExames) {
        this.comentarioExames = comentarioExames;
    }

    public List<String> getComentariosGerais() {
        return comentariosGerais;
    }

    public void setComentariosGerais(List<String> comentariosGerais) {
        this.comentariosGerais = comentariosGerais;
    }
        
}
