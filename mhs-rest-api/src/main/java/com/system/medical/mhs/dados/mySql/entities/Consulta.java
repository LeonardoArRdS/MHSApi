/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.system.medical.mhs.dados.mySql.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author Felipe
 */
@Table(name = "MHS.consulta")
@Entity
public class Consulta implements Serializable{
    @Id
    private Long protocolo;
        
    @ManyToOne
    @JoinColumn(name = "id_especialidade")
    private Especialidade especialidade;
    
    @ManyToOne
    @JoinColumn(name = "id_status_consulta")
    private StatusConsulta status;
    
    @ManyToOne
    @JoinColumn(name = "id_plano_pessoa")
    private PlanoPessoa planoUtilizado;
    
    @OneToMany(mappedBy = "consulta")
    private Set<AnormalidadeConsulta> anormalidades = new HashSet<>();
    
    @OneToMany(mappedBy = "id.consulta", cascade = {CascadeType.ALL})
    private Set<SintomaConsulta> sintomas = new HashSet<>();
    
    @OneToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "protocolo_consulta_pai")
    private Consulta consultaPai;
    
    @OneToOne(mappedBy = "consulta")
    private ProtocoloConsulta protocoloConsulta;
    
    @OneToMany(mappedBy = "consulta", cascade = {CascadeType.ALL})
    private Set<AndamentoConsulta> andamento = new HashSet<>();
    
    @OneToOne(mappedBy = "consulta")
    private AcompanhamentoMedico acompanhamento;
    
    @OneToMany(mappedBy = "consulta")
    private List<ComentarioConsulta> comentarios;
    
    @ManyToOne
    @JoinColumn(name = "id_tipo_consulta", nullable = false, columnDefinition = "int default 1")
    private TipoConsulta tipo;
    
    public Long getProtocolo() {
        return protocolo;
    }

    public void setProtocolo(Long protocolo) {
        this.protocolo = protocolo;
    }
    

    public Especialidade getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(Especialidade especialidade) {
        this.especialidade = especialidade;
    }

    public StatusConsulta getStatus() {
        return status;
    }

    public void setStatus(StatusConsulta status) {
        this.status = status;
    }

    public Set<SintomaConsulta> getSintomas() {
        return sintomas;
    }

    public void setSintomas(Set<SintomaConsulta> sintomas) {
        this.sintomas = sintomas;
    }

    public Consulta getConsultaPai() {
        return consultaPai;
    }

    public void setConsultaPai(Consulta consultaPai) {
        this.consultaPai = consultaPai;
    }

    public PlanoPessoa getPlanoUtilizado() {
        return planoUtilizado;
    }

    public void setPlanoUtilizado(PlanoPessoa planoUtilizado) {
        this.planoUtilizado = planoUtilizado;
    }

    public ProtocoloConsulta getProtocoloConsulta() {
        return protocoloConsulta;
    }

    public void setProtocoloConsulta(ProtocoloConsulta protocoloConsulta) {
        this.protocoloConsulta = protocoloConsulta;
    }
    
    public Set<AndamentoConsulta> getAndamento() {
        return andamento;
    }

    public void setAndamento(Set<AndamentoConsulta> andamento) {
        this.andamento = andamento;
    }

    public TipoConsulta getTipo() {
        return tipo;
    }

    public void setTipo(TipoConsulta tipo) {
        this.tipo = tipo;
    }

    public AcompanhamentoMedico getAcompanhamento() {
        return acompanhamento;
    }

    public void setAcompanhamento(AcompanhamentoMedico acompanhamento) {
        this.acompanhamento = acompanhamento;
    }
    public Boolean prontuarioCompartilhado(){
        return this.acompanhamento != null;
    }

    public Set<AnormalidadeConsulta> getAnormalidades() {
        return anormalidades;
    }

    public void setAnormalidades(Set<AnormalidadeConsulta> anormalidades) {
        this.anormalidades = anormalidades;
    }

    public List<ComentarioConsulta> getComentarios() {
        return comentarios;
    }

    public void setComentarios(List<ComentarioConsulta> comentarios) {
        this.comentarios = comentarios;
    }
    
}
