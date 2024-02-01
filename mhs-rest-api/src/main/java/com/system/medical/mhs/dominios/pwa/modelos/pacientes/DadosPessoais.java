package com.system.medical.mhs.dominios.pwa.modelos.pacientes;

import com.system.medical.mhs.dominios.pwa.modelos.OpcoesContato;

public class DadosPessoais {
	private String cep;
	private String cidade;
	private String estado;
	private String pais;
	private String rua;
	private Integer numero;
	private String email;
	private String celular;
	private Long cpf;
	private String rg;
	private Long numeroSus;
	private String mae;
	private String pai;
	private String ocupacao;
	private String convenios;
	private OpcoesContato opcoesContato;
	
	public DadosPessoais(String cep, String cidade, String estado, String pais, String rua, Integer numero, String email, String celular, Long cpf, String rg, Long numeroSus, String mae, String pai, String ocupacao, String convenios, OpcoesContato opcoesContato){
		this.cep = cep;
		this.cidade = cidade;
		this.estado = estado;
		this.pais = pais;
		this.rua = rua;
		this.numero = numero;
		this.email = email;
		this.celular = celular;
		this.cpf = cpf;
		this.rg = rg;
		this.numeroSus = numeroSus;
		this.mae = mae;
		this.pai = pai;
		this.ocupacao = ocupacao;
		this.convenios = convenios;
		this.opcoesContato = opcoesContato;
	}

	public String getCep() { return cep; }

	public String getCidade() { return cidade; }

	public String getEstado() { return estado; }

	public String getPais() { return pais; }

	public String getRua() { return rua; }

	public Integer getNumero() { return numero; }

	public String getEmail() { return email; }

	public String getCelular() { return celular; }

	public Long getCpf() { return cpf; }

	public String getRg() { return rg; }

	public Long getNumeroSus() { return numeroSus; }

	public String getMae() { return mae; }

	public String getPai() { return pai; }

	public String getOcupacao() { return ocupacao; }

	public String getConvenios() { return convenios; }

	public OpcoesContato getOpcoesContato() { return opcoesContato; }
}
