package com.system.medical.mhs.dominios.pwa.modelos.agenda;

/**
 * @author Leonardo Ariel
 *
 */
public class AgendarConsulta {
	private Long idPaciente;
    private Long crm;
    private String uf;
    private Long dataHorario;
    private Long idClinica;
    private Long idEspecialidade;
    //private Long retornoDe;
    private Long idPlano;
    
	public Long getCrm() {
        return crm;
    }

    public String getUf() {
        return uf;
    }

    public Long getDataHorario() {
        return dataHorario;
    }

    public Long getIdClinica() {
        return idClinica;
    }

    public Long getIdEspecialidade() {
        return idEspecialidade;
    }

    public Long getIdPaciente() {
        return idPaciente;
    }

    public Long getIdPlano() {
        return idPlano;
    }

    public void setIdPlano(Long idPlano) {
        this.idPlano = idPlano;
    }
    
    @Override
	public String toString() {
		return "AgendamentoConsulta [idPaciente=" + idPaciente + ", crm=" + crm + ", uf=" + uf + ", dataHorario="
				+ dataHorario + ", idClinica=" + idClinica + ", idEspecialidade=" + idEspecialidade + ", idPlano=" + idPlano + "]";
	}
}
