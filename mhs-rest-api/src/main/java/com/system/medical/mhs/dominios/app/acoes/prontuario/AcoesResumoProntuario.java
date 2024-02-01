/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.system.medical.mhs.dominios.app.acoes.prontuario;

import com.system.medical.mhs.dominios.RespostaDomain;
import com.system.medical.mhs.dominios.app.interfaces.prontuario.IResumoProntuarioRepository;
import com.system.medical.mhs.dominios.app.modelos.prontuario.ResumoAlergias;
import com.system.medical.mhs.dominios.app.modelos.prontuario.ResumoConsulta;
import com.system.medical.mhs.dominios.app.modelos.prontuario.ResumoDadosPessoais;
import com.system.medical.mhs.dominios.app.modelos.prontuario.ResumoHistoricoDoencas;
import com.system.medical.mhs.dominios.app.modelos.prontuario.ResumoSaude;
import com.system.medical.mhs.dominios.app.modelos.prontuario.ResumoTratamentos;
import java.util.List;

/**
 *
 * @author Felipe
 */
public class AcoesResumoProntuario {

    private IResumoProntuarioRepository resumoProntuarioRepository;

    public AcoesResumoProntuario(IResumoProntuarioRepository resumoProntuarioRepository) {
        this.resumoProntuarioRepository = resumoProntuarioRepository;
    }

    public RespostaDomain<ResumoDadosPessoais> getDados(Long idPaciente) {
        return this.resumoProntuarioRepository.getDados(idPaciente);
    }

    public RespostaDomain<ResumoSaude> getDadosSaude(Long idPaciente) {
        return this.resumoProntuarioRepository.getPlanosSaude(idPaciente);
    }

    public RespostaDomain<ResumoAlergias> getAlergias(Long idPaciente) {
        return this.resumoProntuarioRepository.getAlergias(idPaciente);
    }

    public RespostaDomain<ResumoTratamentos> getTratamentos(Long idPaciente) {
        return this.resumoProntuarioRepository.getTratamentos(idPaciente);
    }

    public RespostaDomain<ResumoHistoricoDoencas> getHistoricoDoencas(Long idPaciente) {
        return this.resumoProntuarioRepository.getHistoricoDoencas(idPaciente);
    }

    public RespostaDomain<List<ResumoConsulta>> getConsultas(Long idPaciente) {
        return this.resumoProntuarioRepository.getConsultas(idPaciente);
    }
}
