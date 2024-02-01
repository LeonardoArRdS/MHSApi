package com.system.medical.mhs.dados.mySql.entities;

import java.io.Serializable;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "MHS.acompanhamento_medico")
public class AcompanhamentoMedico implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "id_crm")
	private CRM crm;
	
	@ManyToOne
	@JoinColumn(name = "id_paciente")
	private Pessoa paciente;
        
        @OneToOne
        @JoinColumn(name = "protocolo_consulta")
        private Consulta consulta;
	
	@Column(name = "inicio_vigencia")
	private Date inicioVigencia;
	
	@Column(name = "fim_vigencia")
	private Date fimVigencia;
	
	@OneToMany(mappedBy = "acompanhamento")
	private Set<ProgressaoPaciente> progressos = new HashSet<>();
	
	public Set<ProgressaoPaciente> getProgressos() {
		return progressos;
	}

	public void setProgressos(Set<ProgressaoPaciente> progressos) {
		this.progressos = progressos;
	}

	public Set<NovidadeAcompanhamento> getNovidades() {
		return novidades;
	}

	public void setNovidades(Set<NovidadeAcompanhamento> novidades) {
		this.novidades = novidades;
	}

	@OneToMany(mappedBy = "acompanhamento")
	private Set<NovidadeAcompanhamento> novidades = new HashSet<>();
	
	public Date getFimVigencia() {
		return fimVigencia;
	}

	public void setFimVigencia(Date fimVigencia) {
		this.fimVigencia = fimVigencia;
	}

	public AcompanhamentoMedico() {
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public CRM getCrm() {
		return crm;
	}

	public void setCrm(CRM crm) {
		this.crm = crm;
	}

	public Pessoa getPaciente() {
		return paciente;
	}

	public void setPaciente(Pessoa paciente) {
		this.paciente = paciente;
	}

	public Date getInicioVigencia() {
		return inicioVigencia;
	}

	public void setInicioVigencia(Date inicioVigencia) {
		this.inicioVigencia = inicioVigencia;
	}

    public Consulta getConsulta() {
        return consulta;
    }

    public void setConsulta(Consulta consulta) {
        this.consulta = consulta;
    }
	
	
}
