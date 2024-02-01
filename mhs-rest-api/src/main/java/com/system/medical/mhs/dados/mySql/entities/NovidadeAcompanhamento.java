package com.system.medical.mhs.dados.mySql.entities;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "novidade_acompanhamento")
public class NovidadeAcompanhamento implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "id_acompanhamento")
	private AcompanhamentoMedico acompanhamento;
	
	@ManyToOne
	@JoinColumn(name = "id_tipo_novidade")
	private TipoNovidadeAcompanhamento tipo;
	
	@Column(name = "data_realizacao")
	private Date realizacao;
	
	public NovidadeAcompanhamento() {
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public AcompanhamentoMedico getAcompanhamento() {
		return acompanhamento;
	}

	public void setAcompanhamento(AcompanhamentoMedico acompanhamento) {
		this.acompanhamento = acompanhamento;
	}

	public TipoNovidadeAcompanhamento getTipo() {
		return tipo;
	}

	public void setTipo(TipoNovidadeAcompanhamento tipo) {
		this.tipo = tipo;
	}

	public Date getRealizacao() {
		return realizacao;
	}

	public void setRealizacao(Date realizacao) {
		this.realizacao = realizacao;
	}
	
	@Override
	public String toString(){
		
		switch(id.intValue()){
			default:
				return this.acompanhamento.getPaciente().getNome() + " compartilhou o prontuário com você!";
		}
	}
}
