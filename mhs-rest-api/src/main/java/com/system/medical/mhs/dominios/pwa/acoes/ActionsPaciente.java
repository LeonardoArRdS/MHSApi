/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.system.medical.mhs.dominios.pwa.acoes;

import com.system.medical.mhs.dados.mySql.entities.DoencaPessoa;
import com.system.medical.mhs.dominios.RespostaDomain;
import com.system.medical.mhs.dominios.pwa.interfaces.IPacientesRepository;
import com.system.medical.mhs.dominios.pwa.modelos.pacientes.AlergiasConhecidas;
import com.system.medical.mhs.dominios.pwa.modelos.pacientes.Dados;
import com.system.medical.mhs.dominios.pwa.modelos.pacientes.DadosPessoais;
import com.system.medical.mhs.dominios.pwa.modelos.pacientes.ExamesRecentes;
import com.system.medical.mhs.dominios.pwa.modelos.pacientes.ExamesRecentesCompleto;
import com.system.medical.mhs.dominios.pwa.modelos.pacientes.HistoricoDoencas;
import com.system.medical.mhs.dominios.pwa.modelos.pacientes.MedicacaoFrequente;
import com.system.medical.mhs.dominios.pwa.modelos.pacientes.Prescricao;
import com.system.medical.mhs.dominios.pwa.modelos.pacientes.ProcedimentosAtuais;

/**
 *
 * @author Felipe
 */
public class ActionsPaciente{
    private IPacientesRepository pessoaRepository;

    public ActionsPaciente(IPacientesRepository pessoaRepository) {
        this.pessoaRepository = pessoaRepository;
    }
    
    public RespostaDomain<AlergiasConhecidas> consultarAlergiasConhecidas(Long CPF) {
		return this.pessoaRepository.consultarAlergiasConhecidas(CPF);
	}
	
    public RespostaDomain<Dados> obterDados(Long CPF){
        return this.pessoaRepository.obterDados(CPF);
    }
    
    public RespostaDomain<DadosPessoais> obterDadosPessoais(Long CPF){
        return this.pessoaRepository.obterDadosPessoais(CPF);
    }
    
    public RespostaDomain<ExamesRecentes> consultarExamesRecentes(Long CPF){
        return this.pessoaRepository.consultarExamesRecentes(CPF);
    }
    
    public RespostaDomain<HistoricoDoencas> consultarHistoricoDoencas(Long CPF){
        return this.pessoaRepository.consultarHistoricoDoencas(CPF);
    }
    
    public RespostaDomain<ExamesRecentesCompleto> consultarExamesRecentesCompleto(Long CPF){
        return this.pessoaRepository.consultarExamesRecentesCompleto(CPF);
    }
    
    public RespostaDomain<MedicacaoFrequente> consultarMedicacoesFrequentes(Long CPF){
        return this.pessoaRepository.consultarMedicacaoFrequente(CPF);
    }
    
    public RespostaDomain<Prescricao> consultarPrescricao(Long CPF) {
		return this.pessoaRepository.consultarPrescricao(CPF);
	}
    
    public RespostaDomain<ProcedimentosAtuais> consultarProcedimentosAtuais(Long CPF){
        return this.pessoaRepository.consultarProcedimentosAtuais(CPF);
    }    
}
