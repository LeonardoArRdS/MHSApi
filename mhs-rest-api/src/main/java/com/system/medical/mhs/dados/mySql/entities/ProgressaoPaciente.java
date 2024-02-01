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
@Table(name = "MHS.progressao_paciente")
public class ProgressaoPaciente implements Serializable 	{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;	
	
	@ManyToOne
	@JoinColumn(name = "id_acompanhamento")
	private AcompanhamentoMedico acompanhamento;
	
	@ManyToOne
	@JoinColumn(name = "id_tipo_progressao")
	private TipoProgressao tipo;
	
	@ManyToOne
	@JoinColumn(name = "id_status_progressao")
	private StatusProgressao status;
	
	@Column(name = "data_realizacao")
	private Date realizacao;
	
	@Column(name = "porcentagem_conclusao")
	private Double conclusao;
	
	public ProgressaoPaciente() {
		
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

	public TipoProgressao getTipo() {
		return tipo;
	}

	public void setTipo(TipoProgressao tipo) {
		this.tipo = tipo;
	}

	public StatusProgressao getStatus() {
		return status;
	}

	public void setStatus(StatusProgressao status) {
		this.status = status;
	}

	public Date getRealizacao() {
		return realizacao;
	}

	public void setRealizacao(Date data) {
		this.realizacao = data;
	}

	public Double getConclusao() {
		return conclusao;
	}

	public void setConclusao(Double conclusao) {
		this.conclusao = conclusao;
	};
	
	public Double getPorcentagemConclusao(){
		return this.conclusao * 100;
	}
	
	public Double getProcentagemRestante(){
		return 100 - this.conclusao;
	}
	
	@Override
	public String toString(){
		return this.getAcompanhamento().getPaciente().getNome() + " agora está a " +
				String.format("%.2f", this.getProcentagemRestante()) + "%" + " de concluir o acompanhamento!!";
	}
}
