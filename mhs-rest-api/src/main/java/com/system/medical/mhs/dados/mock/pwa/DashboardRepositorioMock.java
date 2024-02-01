/**
 * 
 */
package com.system.medical.mhs.dados.mock.pwa;

import java.util.ArrayList;
import java.util.Date;

import org.springframework.stereotype.Repository;

import com.system.medical.mhs.dominios.RespostaDomain;
import com.system.medical.mhs.dominios.pwa.interfaces.dashboard.IMedicosRepository;
import com.system.medical.mhs.dominios.pwa.interfaces.dashboard.INovidadesRepository;
import com.system.medical.mhs.dominios.pwa.interfaces.dashboard.IPlanoRepository;
import com.system.medical.mhs.dominios.pwa.modelos.dashboard.AgendaDia;
import com.system.medical.mhs.dominios.pwa.modelos.dashboard.ProximasConsultas;
import com.system.medical.mhs.dominios.pwa.modelos.dashboard.UltimasConsultas;
import com.system.medical.mhs.utils.Imagem;
import com.system.medical.mhs.dominios.pwa.modelos.dashboard.Novidade;

/**
 * @author Leonardo Ariel
 *
 */
@Repository
public class DashboardRepositorioMock implements IMedicosRepository, INovidadesRepository {
	@Override
	public RespostaDomain buscarAgenda(Long crm, String uf) {
		RespostaDomain resposta;

		if(crm == ((long) 123)){
			ArrayList<AgendaDia> agenda = new ArrayList<AgendaDia>();
			
			agenda.add(
				new AgendaDia(
					new Imagem("C:/Imagens/Perfil1.jpg").converterImagemBase64(),
					"Cristiano Ronaldo dos Santos",
					true,
					"09:00"
				)
			);
			agenda.add(
				new AgendaDia(
					new Imagem("C:/Imagens/Perfil2.jpg").converterImagemBase64(),
					"Alice Matos",
					true,
					"09:30"
				)
			);
			agenda.add(
				new AgendaDia(
					new Imagem("C:/Imagens/Perfil3.jpg").converterImagemBase64(),
					"Alex Ferguson da Silva",
					false,
					"10:00"
				)
			);
			agenda.add(
				new AgendaDia(
					new Imagem("C:/Imagens/Perfil4.jpg").converterImagemBase64(),
					"Deive Pazos",
					true,
					"10:30"
				)
			);
			agenda.add(
				new AgendaDia(
					new Imagem("C:/Imagens/Perfil5.jpg").converterImagemBase64(),
					"Sergio Ramos Garcia",
					true,
					"11:00"
				)
			);
			agenda.add(
				new AgendaDia(
					new Imagem("C:/Imagens/Perfil6.jpg").converterImagemBase64(),
					"Carlitos Teves Nuñez",
					false,
					"11:30"
				)
			);
			agenda.add(
				new AgendaDia(
					new Imagem("C:/Imagens/Perfil8.jpg").converterImagemBase64(),
					"Zlatan Ibrahimovic Pereira",
					true,
					"12:30"
				)
			);
			agenda.add(
				new AgendaDia(
					new Imagem("C:/Imagens/Perfil7.jpg").converterImagemBase64(),
					"Camila Anhanguera",
					false,
					"13:00"
				)
			);
			agenda.add(
				new AgendaDia(
					new Imagem("C:/Imagens/Perfil9.jpg").converterImagemBase64(),
					"Dayse Soares",
					false,
					"13:30"
				)
			);
			agenda.add(
				new AgendaDia(
					new Imagem("C:/Imagens/Perfil10.jpg").converterImagemBase64(),
					"Davi Brasil",
					false,
					"14:00"
				)
			);
			agenda.add(
				new AgendaDia(
					new Imagem("C:/Imagens/Perfil11.jpg").converterImagemBase64(),
					"Douglas Dajnine",
					true,
					"14:30"
				)
			);
			agenda.add(
				new AgendaDia(
					new Imagem("C:/Imagens/Perfil12.jpg").converterImagemBase64(),
					"Gabriel Savio",
					true,
					"15:00"
				)
			);
			
			resposta = new RespostaDomain(agenda);
		}
		else{
			resposta = new RespostaDomain(null);
			resposta.adicionarMensagemErro("Médico não encontrado.");
		}
		
		return resposta;
	}

	@Override
	public RespostaDomain buscarProximasConsultas(Long crm, String uf) {
		RespostaDomain resposta;
		
		if(crm == ((long) 123)){
			ArrayList<ProximasConsultas> consultas = new ArrayList<ProximasConsultas>();
		
			consultas.add(
				new ProximasConsultas(
					new Imagem("C:/Imagens/Perfil1.jpg").converterImagemBase64(),
					"Cristiano Ronaldo dos Santos",
					true,
					"Unimed",
					"9:00"
				)
			);
			consultas.add(
				new ProximasConsultas(
					new Imagem("C:/Imagens/Perfil2.jpg").converterImagemBase64(),
					"Alice Matos",
					true,
					"Particular",
					"9:00"
				)
			);
			consultas.add(
				new ProximasConsultas(
					new Imagem("C:/Imagens/Perfil4.jpg").converterImagemBase64(),
					"Deive Pazos",
					true,
					"SulAmerica",
					"10:30"
				)
			);
			consultas.add(
				new ProximasConsultas(
					new Imagem("C:/Imagens/Perfil7.jpg").converterImagemBase64(),
					"Zlatan Ibrahimovic Pereira",
					false,
					"Particular",
					"9:00"
				)
			);
			consultas.add(
				new ProximasConsultas(
					new Imagem("C:/Imagens/Perfil3.jpg").converterImagemBase64(),
					"Alex Ferguson da Silva",
					false,
					"Allianz",
					"9:00"
				)
			);
			consultas.add(
				new ProximasConsultas(
					new Imagem("C:/Imagens/Perfil6.jpg").converterImagemBase64(),
					"Sergio Ramos Garcia",
					true,
					"Amil",
					"9:00"
				)
			);
			resposta = new RespostaDomain(consultas);
		}
		else{
			resposta = new RespostaDomain(null);
			resposta.adicionarMensagemErro("Médico não encontrado.");
		}
		
		return resposta;
	}

	@Override
	public RespostaDomain buscarUltimasConsultas(Long crm, String uf) {
		RespostaDomain resposta;

		if(crm == ((long) 123)){
			ArrayList<UltimasConsultas> consultas = new ArrayList<UltimasConsultas>();
			ArrayList<String> sintomas;
			
			sintomas = new ArrayList<String>();
			sintomas.add("Dor de cabeça");	
			
			consultas.add(
				new UltimasConsultas(
					new Imagem("C:/Imagens/Perfil6.jpg").converterImagemBase64(),
					"Carlitos Teves Nuñez",
					new Date(),
					"Estado clinico estável",
					sintomas
				)
			);
			
			sintomas = new ArrayList<String>();
			sintomas.add("Desânimo");
			sintomas.add("Fraqueza");
			
			consultas.add(
				new UltimasConsultas(
					new Imagem("C:/Imagens/Perfil8.jpg").converterImagemBase64(),
					"Camila Anhanguera",
					new Date(),
					"Hipotiroidismo crônico",
					sintomas
				)
			);
			
			resposta = new RespostaDomain(consultas);
		}
		else{
			resposta = new RespostaDomain(null);
			resposta.adicionarMensagemErro("Médico não encontrado.");
		}
		
		return resposta;
	}

	@Override
	public RespostaDomain buscarNovidadesPacientes(Long crm, String uf) {
		RespostaDomain resposta;
		
		if(crm == ((long) 123)){
			ArrayList<Novidade> novidades = new ArrayList<Novidade>();
			novidades.add(
				new Novidade(
					"Cristiano Ronaldo dos Santos",
					"Compartilhou seu prontuário",
                                        123L,
					"12.07.2017 - 08h42",
					"comp_prontuario"
				)
			);
			novidades.add(
				new Novidade(
					"Consulta Urgente!",
					"Solicitação de consulta imediata",
                                        123L,
					"12.07.2017 - 07h30",
					"consulta_urgente"
				)
			);
			novidades.add(
				new Novidade(
					"Eric Cantona",
					"Concluiu 80% de seu tratamento",
                                        123L,
					"11.07.2017 - 17h42",
					"status_tratamento"
				)
			);
			novidades.add(
				new Novidade(
					"Zlatan Ibrahimovic Pereira",
					"Pagou por sua consulta",
                                        123L,
					"11.07.2017 - 13h42",
					"consulta_pagamento"
				)
			);
			novidades.add(
				new Novidade(
					"Deive Pazos",
					"Compartilhou seu prontuário",
                                        123L,
					"11.07.2017 - 12h22",
					"comp_prontuario"
				)
			);
			novidades.add(
				new Novidade(
					"Alice Matos",
					"Compartilhou seu prontuário",
                                        123L,
					"11.07.2017 - 10h52",
					"comp_prontuario"
				)
			);
			novidades.add(
				new Novidade(
					"Sergio Ramos Garcia",
					"Concluiu 80% de seu tratamento",
                                        123L,
					"11.07.2017 - 18h16",
					"status_tratamento"
				)
			);
			novidades.add(
				new Novidade(
					"Alice Matos",
					"Pagou por sua consulta",
                                        123L,
					"11.07.2017 - 06h46",
					"consulta_pagamento"
				)
			);
			novidades.add(
				new Novidade(
					"Eric Cantona",
					"Pagou por sua consulta",
                                        123L,
					"11.07.2017 - 06h46",
					"consulta_pagamento"
				)
			);
			
			resposta = new RespostaDomain(novidades);
		}
		else{
			resposta = new RespostaDomain(null);
			resposta.adicionarMensagemErro("Médico não encontrado.");
		}
		
		return resposta;
	}
	
}
