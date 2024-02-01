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
public class ActionsPacienteRepository{
    private IPacientesRepository pessoaRepository;

    public ActionsPacienteRepository(IPacientesRepository pessoaRepository) {
        this.pessoaRepository = pessoaRepository;
    }
    
    public RespostaDomain<AlergiasConhecidas> consultarAlergiasConhecidas(Long cpf) {
		return this.pessoaRepository.consultarAlergiasConhecidas(cpf);
	}
	
    public RespostaDomain<Dados> obterDados(Long cpf){
        return this.pessoaRepository.obterDados(cpf);
    }
    
    public RespostaDomain<DadosPessoais> obterDadosPessoais(Long cpf){
        return this.pessoaRepository.obterDadosPessoais(cpf);
    }
    
	public RespostaDomain obterPerfil(Long cpf) {
		return this.pessoaRepository.obterPerfil(cpf);
	}    
    
    /*
    public RespostaDomain<DoencaPessoa> consultarDoencasFrequentes(Long cpf){
        return this.pessoaRepository.consultarDoencaFrequente(cpf);
    }
    */
    
    public RespostaDomain<ExamesRecentes> consultarExamesRecentes(Long cpf){
        return this.pessoaRepository.consultarExamesRecentes(cpf);
    }
    
    public RespostaDomain<HistoricoDoencas> consultarHistoricoDoencas(Long cpf){
        return this.pessoaRepository.consultarHistoricoDoencas(cpf);
    }
    
    public RespostaDomain<ExamesRecentesCompleto> consultarExamesRecentesCompleto(Long cpf){
        return this.pessoaRepository.consultarExamesRecentesCompleto(cpf);
    }
    
    public RespostaDomain<MedicacaoFrequente> consultarMedicacoesFrequentes(Long cpf){
        return this.pessoaRepository.consultarMedicacaoFrequente(cpf);
    }
    
    public RespostaDomain<Prescricao> consultarPrescricao(Long cpf) {
		return this.pessoaRepository.consultarPrescricao(cpf);
	}
    
    public RespostaDomain<ProcedimentosAtuais> consultarProcedimentosAtuais(Long cpf){
        return this.pessoaRepository.consultarProcedimentosAtuais(cpf);
    }
}
