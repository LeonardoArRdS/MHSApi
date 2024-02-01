package com.system.medical.mhs.dominios.pwa.interfaces;

//<<<<<<< HEAD
import com.system.medical.mhs.dominios.RespostaDomain;
//>>>>>>> 8e99ebf31f69c2abd5665de505819dc43fcd2732
/**
 * @author Leonardo Ariel
 *
 */
public interface IPacientesRepository {
  RespostaDomain obterDados(Long cpfPaciente);
  RespostaDomain obterDadosPessoais(Long cpfPaciente);
  RespostaDomain obterPerfil(Long cpf);
  RespostaDomain consultarAlergiasConhecidas(Long cpfPaciente);
  RespostaDomain consultarExamesRecentes(Long cpfPaciente);
  RespostaDomain consultarExamesRecentesCompleto(Long cpfPaciente);
  RespostaDomain consultarHistoricoDoencas(Long cpfPaciente);
  RespostaDomain consultarMedicacaoFrequente(Long cpfPaciente);
  RespostaDomain consultarPrescricao(Long cpfPaciente);
  RespostaDomain consultarProcedimentosAtuais(Long cpfPaciente);
}

