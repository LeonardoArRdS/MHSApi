package com.system.medical.mhs.dominios.pwa.modelos;

import java.util.List;

public class Tratamento {

    private String tratamento;
    private List<MedicacaoTratamento> medicacao;
    private String duracao;
    private String frequencia;

    public Tratamento(String tratamento, List<MedicacaoTratamento> medicacao, String duracao, String frequencia) {
        this.tratamento = tratamento;
        this.medicacao = medicacao;
        this.duracao = duracao;
        this.frequencia = frequencia;
    }
    
    

    public String getTratamento() {
        return tratamento;
    }

    public List<MedicacaoTratamento> getMedicacao() {
        return medicacao;
    }

    public String getFrequencia() {
        return frequencia;
    }

    public String getDuracao() {
        return duracao;
    }
    
    
}
