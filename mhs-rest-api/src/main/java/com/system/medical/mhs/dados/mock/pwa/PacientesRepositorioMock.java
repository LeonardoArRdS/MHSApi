package com.system.medical.mhs.dados.mock.pwa;

import java.util.ArrayList;
import java.util.Date;

import com.system.medical.mhs.dominios.RespostaDomain;
import com.system.medical.mhs.dominios.pwa.interfaces.IPacientesRepository;
import com.system.medical.mhs.dominios.pwa.modelos.Doencas;
import com.system.medical.mhs.dominios.pwa.modelos.MedicacaoTratamento;
import com.system.medical.mhs.dominios.pwa.modelos.OpcoesContato;
import com.system.medical.mhs.dominios.pwa.modelos.Tratamento;
import com.system.medical.mhs.dominios.pwa.modelos.pacientes.AlergiasConhecidas;
import com.system.medical.mhs.dominios.pwa.modelos.pacientes.Dados;
import com.system.medical.mhs.dominios.pwa.modelos.pacientes.DadosPessoais;
import com.system.medical.mhs.dominios.pwa.modelos.pacientes.ExamesRecentes;
import com.system.medical.mhs.dominios.pwa.modelos.pacientes.ExamesRecentesCompleto;
import com.system.medical.mhs.dominios.pwa.modelos.pacientes.HistoricoDoencas;
import com.system.medical.mhs.dominios.pwa.modelos.pacientes.MedicacaoFrequente;
import com.system.medical.mhs.dominios.pwa.modelos.pacientes.Prescricao;
import com.system.medical.mhs.dominios.pwa.modelos.pacientes.ProcedimentosAtuais;
import java.util.Arrays;

/**
 * @author Leonardo Ariel
 *
 */
public class PacientesRepositorioMock implements IPacientesRepository {
	@Override
	public RespostaDomain obterDados(Long cpfPaciente) {
		RespostaDomain resposta;
		
		if(cpfPaciente.equals("123.456.789-10")) {
			resposta = new RespostaDomain(
				new Dados(
					"Davi Alves",
					new Date(1998, 3, 3),
					1.65,
					"Branca",
					"Feminino",
					52,
					"Brasileiro"

				)
			);
		}
		else{
			resposta = new RespostaDomain(null);
			resposta.adicionarMensagemErro("Paciente não encontrado.");
		}
		
		return resposta;
	}

	@Override
	public RespostaDomain obterDadosPessoais(Long cpfPaciente) {
		RespostaDomain resposta;
		
		if(cpfPaciente == (long) 123) {
			resposta = new RespostaDomain(
				new DadosPessoais(
					"08090-710",
					"São Paulo",
					"São Paulo",
					"Brasil",
					"Rua dos Cajus",
					703,
					"davi.alvesjunior@gmail.com",
					"(11) 91234-5678",
					12345678910L,
					"12.345.567-8",
					123456778L,//"123456778",
					"Nana Alves Pereira",
					"Davi Alves Pereira",
					"Sou Trabalhador Sr.",
					"Tenho convênio não Sr.",
					new OpcoesContato(true, true)
				)
			);
		}
		else{
			resposta = new RespostaDomain(null);
			resposta.adicionarMensagemErro("Paciente não encontrado.");
		}
		return resposta;
	}
	
	@Override
	public RespostaDomain consultarAlergiasConhecidas(Long cpfPaciente) {
		RespostaDomain resposta;
		
		
		
		if(cpfPaciente == (long) 123) {
			ArrayList<String> medicamentos = new ArrayList<String>();
			ArrayList<String> outras = new ArrayList<String>();
			
			medicamentos.add("Penicilina");
			medicamentos.add("Sulfa");
			
			outras.add("Amendoim");
			outras.add("Lactose");
			
			resposta = new RespostaDomain(
				new AlergiasConhecidas(
					medicamentos, 
					outras
				)
	        );
		}
		else{
			resposta = new RespostaDomain(null);
			resposta.adicionarMensagemErro("Paciente não encontrado.");
		}
		return resposta;
	}

	@Override
	public RespostaDomain consultarExamesRecentes(Long cpfPaciente) {
		RespostaDomain resposta;

		if(cpfPaciente == (long) 123) {
			ArrayList<ExamesRecentes> exames = new ArrayList<ExamesRecentes>();
			
			exames.add(
				new ExamesRecentes(
					new Date(),//"25.01.2018",
					"Exame de Sangue",
					true//""
				)
			);
			
			exames.add(
				new ExamesRecentes(
					new Date(),//"12.07.2017",
					"Exame de Sangue",
					true//""
				)
			);
			
			resposta = new RespostaDomain(exames);
		}
		else{
			resposta = new RespostaDomain(null);
			resposta.adicionarMensagemErro("Paciente não encontrado.");
		}
		
		return resposta;
	}

	@Override
	public RespostaDomain consultarExamesRecentesCompleto(Long cpfPaciente) {
		RespostaDomain resposta;
		
		if(cpfPaciente == (long) 123) {
			ArrayList<ExamesRecentesCompleto> exames = new ArrayList<ExamesRecentesCompleto>();
			ArrayList<String> sintomas;
			ArrayList<Tratamento> tratamentos;
			
			//Exame 1
			sintomas = new ArrayList<String>();
			sintomas.add("Dor de cabeça");
			sintomas.add("Leve tontura");
			
			tratamentos = new ArrayList<Tratamento>();
			tratamentos.add(
				new Tratamento(
						"Dramin b6",
						new ArrayList<MedicacaoTratamento>(), 
						"7 dias", 
						"1 x 12h"
				)
			);
			tratamentos.add(
				new Tratamento(
						"Dramin b7",
						new ArrayList<MedicacaoTratamento>(),  
						"14 dias", 
						"1 x 8h"
				)
			);
			
			exames.add(
				new ExamesRecentesCompleto(
					new Date(),//"27.07.2017",
					"Clínico Geral",
					Arrays.asList("Estado clinico estável"),
					sintomas,
					tratamentos,
					"Não se aplica",
					Arrays.asList("Paciente apresentava quadro clínico simples com leves dores de cabeça, não apresentava histórico de enxaqueca nem tinha histórico familiar eliminando a nescessidade de um tratamento mais completo.")
				)
			);
			
			//Exame 2
			sintomas = new ArrayList<String>();
			sintomas.add("Dor de cabeça");
			sintomas.add("Leve tontura");
			
			tratamentos = new ArrayList<Tratamento>();
			tratamentos.add(
				new Tratamento(
						"Dramin b6",
						new ArrayList<MedicacaoTratamento>(),  
						"7 dias", 
						"1 x 12h"
				)
			);
			tratamentos.add(
				new Tratamento(
						"Dramin b7",
						new ArrayList<MedicacaoTratamento>(),  
						"14 dias", 
						"1 x 8h"
				)
			);
			
			exames.add(
				new ExamesRecentesCompleto(
					new Date(),//"27.07.2017",
					"Clínico Geral",
					Arrays.asList("Estado clinico estável"),
					sintomas,
					tratamentos,
					"Não se aplica",
					Arrays.asList("Paciente apresentava quadro clínico simples com leves dores de cabeça, não apresentava histórico de enxaqueca nem tinha histórico familiar eliminando a nescessidade de um tratamento mais completo.")
				)
			);
			
			resposta = new RespostaDomain(exames);
		}
		else{
			resposta = new RespostaDomain(null);
			resposta.adicionarMensagemErro("Paciente não encontrado.");
		}
		
		return resposta;
	}
	@Override
	public RespostaDomain consultarHistoricoDoencas(Long cpfPaciente) {
		RespostaDomain resposta;

		if(cpfPaciente == (long) 123) {
			ArrayList<Doencas> doencasAtuais = new ArrayList<Doencas>();
			ArrayList<Doencas> doencasPassadas = new ArrayList<Doencas>();
			ArrayList<Doencas> doencasFamilia = new ArrayList<Doencas>();
	
			doencasAtuais.add(new Doencas("Hipotiroidismo", "E02"));
			doencasAtuais.add(new Doencas("Esofagite", "K20"));
			
			
			doencasPassadas.add(new Doencas("Faringite", "K24"));
			doencasPassadas.add(new Doencas("Gastrite", "H54"));
	
			doencasFamilia.add(new Doencas("AVC", "L45"));
			doencasFamilia.add(new Doencas("Hipertensão", "T09"));
			doencasFamilia.add(new Doencas("Pneumonia", "L12"));
			
			resposta = new RespostaDomain(
					new HistoricoDoencas(
					doencasAtuais,
					doencasPassadas,
					doencasFamilia
				)
			);
		}
		else{
			resposta = new RespostaDomain(null);
			resposta.adicionarMensagemErro("Paciente não encontrado.");
		}

		return resposta;
	}

	@Override
	public RespostaDomain consultarMedicacaoFrequente(Long cpfPaciente) {
		RespostaDomain resposta;
		
		if(cpfPaciente == (long) 123) {
			MedicacaoFrequente medicacaoFrequente = new MedicacaoFrequente();
		
			medicacaoFrequente.addMedicacao("Allegra D");
			medicacaoFrequente.addMedicacao("Diane 35");
			medicacaoFrequente.addMedicacao("Purant 4");
			
			resposta = new RespostaDomain(medicacaoFrequente);
		}
		else{
			resposta = new RespostaDomain(null);
			resposta.adicionarMensagemErro("Paciente não encontrado.");
		}
		
		return resposta;
	}

	@Override
	public RespostaDomain consultarPrescricao(Long cpfPaciente) {
		RespostaDomain resposta;
		
		if(cpfPaciente == (long) 123) {
			ArrayList<Prescricao> prescricoes = new ArrayList<Prescricao>();
			
			prescricoes.add(
				new Prescricao(
					true,
					"20/12/2015",//Data
					"Varfariná Sódica",//Medicamento
					"5 mg",//Dose
					"Oral",//Via
					"1 x Dia"//Frequência
				)
			);
			prescricoes.add(
				new Prescricao(
					false,
					"20/12/2015",//Data
					"Ácido Acetilsalicilico (500MG)",//Medicamento
					"200 mg",//Dose
					"Oral",//Via
					"2 x Dia"//Frequência
				)
			);
			
			resposta = new RespostaDomain(prescricoes);
		}
		else{
			resposta = new RespostaDomain(null);
			resposta.adicionarMensagemErro("Paciente não encontrado.");
		}
		
		return resposta;
	}

	@Override
	public RespostaDomain consultarProcedimentosAtuais(Long cpfPaciente) {
		RespostaDomain resposta;
		
		if(cpfPaciente == (long) 123) {
			resposta = new RespostaDomain(
				new ProcedimentosAtuais(
					new Doencas(
						"Esofagite",
						"K20"
					),
					new Doencas(
						"Hipotireoidismo",
						"E02"
					)
				)
			);
		}
		else{
			resposta = new RespostaDomain(null);
			resposta.adicionarMensagemErro("Paciente não encontrado.");
		}
		
		return resposta;
	}

	@Override
	public RespostaDomain obterPerfil(Long cpf) {
		// TODO Auto-generated method stub
		return null;
	}
}
