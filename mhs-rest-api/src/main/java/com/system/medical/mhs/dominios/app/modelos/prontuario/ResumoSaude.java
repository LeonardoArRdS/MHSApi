/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.system.medical.mhs.dominios.app.modelos.prontuario;

import java.util.List;

/**
 *
 * @author Felipe
 */
public class ResumoSaude {
   private String tipoSanguineo;
   private Boolean doadorOrgaos;
   private List<ResumoPlanoSaude> planosSaude;   

    public ResumoSaude(String tipoSanguineo, Boolean doadorOrgaos, List<ResumoPlanoSaude> planosSaude) {
        this.tipoSanguineo = tipoSanguineo;
        this.doadorOrgaos = doadorOrgaos;
        this.planosSaude = planosSaude;
    }

    public String getTipoSanguineo() {
        return tipoSanguineo;
    }

    public Boolean getDoadorOrgaos() {
        return doadorOrgaos;
    }

    public List<ResumoPlanoSaude> getPlanosSaude() {
        return planosSaude;
    }
 
    
}
