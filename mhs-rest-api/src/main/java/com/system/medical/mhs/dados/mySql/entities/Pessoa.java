/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.system.medical.mhs.dados.mySql.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 *
 * @author Felipe
 */
@Table(name = "MHS.pessoa")
@Entity
public class Pessoa implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @Column(name = "foto")
    private String foto;

    @Column(name = "nome")
    private String nome;

    @Column(name = "sobrenome")
    private String sobrenome;
    
    @Column(name = "rg")
    private Long rg;
    
    @Column(name = "cpf")
    private Long cpf;
    
    @Column(name = "numero_sus")
    private Long numeroSUS;
    
    @Column(name = "email")
    private String email;
    
    @Column(name = "telefone")
    private Long telefone;
    
    @ManyToOne
    @JoinColumn(name = "id_ocupacao")
    private Ocupacao ocupacao;
    
    @ManyToOne
    @JoinColumn(name = "id_cidade_nascimento")
    private Cidade localNascimento;
    
    @ManyToOne
    @JoinColumn(name = "id_sexo")
    private Sexo sexo;
    
    @ManyToOne
    @JoinColumn(name = "id_nacionalidade")
    private Nacionalidade nacionalidade;
    
    @ManyToOne
    @JoinColumn(name = "id_raca")
    private Raca raca;
    
    @ManyToOne
    @JoinColumn(name = "id_tipo_sanguineo")
    private TipoSanguineo tipoSanguineo;
    
    @Column(name = "dt_nascimento")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dataNascimento;
    
    @OneToOne(mappedBy = "pessoa")
    private CRM crm;
    
    @Column(name = "nome_mae")
    private String nomeMae;
    
    @Column(name = "nome_pai")
    private String nomePai;
    
    @OneToOne(mappedBy = "pessoa")
    private EnderecoPessoa endereco;
    
    @Column(name = "doador_orgaos")
    private Boolean doadorOrgaos;
    
    @OneToMany(mappedBy = "id.responsavel")
    private Set<Dependencia> dependencias = new HashSet<>();
    
    @OneToMany(mappedBy = "id.pessoa")
    private List<CaracteristicaFisicaPessoa> caracteristicasFisicas = new ArrayList<>();
    
    @OneToMany(mappedBy = "id.pessoa")
    private Set<OpcoesCaracteristicaPessoa> caracteristicas = new HashSet<>();
    
    @OneToMany(mappedBy = "pessoa")
    private Set<DoencaPessoa> doencas = new HashSet<>();
    
    @OneToMany(mappedBy = "id.pessoa")
    private Set<ProtocoloConsulta> consultas = new HashSet<>();
    
    @OneToMany(mappedBy = "pessoa")
    private Set<Contratacao> contratacoes;
    
    @OneToMany(mappedBy = "pessoa")
    private Set<Medicacao> medicacoes = new HashSet<>();
    
    @OneToMany(mappedBy = "pessoa")
    private Set<ExamePessoa> exames = new HashSet<>();
    
    @OneToMany(mappedBy = "pessoa")
    private Set<TratamentoPessoa> tratamentos = new HashSet<>();
    
    @OneToMany(mappedBy = "pessoa")
    private List<PlanoPessoa> planos = new ArrayList<>();
    
    @OneToMany(mappedBy = "pessoa")
    private List<Familiar> familiares = new ArrayList<>();
    
    @OneToMany(mappedBy = "pessoa")
    private List<Alergia> alergias = new ArrayList<>();
    
    @ManyToMany
    @JoinTable(name = "alergia_medicamento", joinColumns = @JoinColumn(name = "id_pessoa"), inverseJoinColumns = @JoinColumn(name = "id_medicamento"))
    private List<Medicamento> alergiasMedicamentos = new ArrayList<>();
    
    
    public Pessoa() {
    }   

    public Pessoa(Long id) {
        this.id = id;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public Sexo getSexo() {
        if(sexo == null) return new Sexo();
        return sexo;
    }

    public void setSexo(Sexo sexo) {
        this.sexo = sexo;
    }

    public Set<Contratacao> getContratacoes() {
        return contratacoes;
    }

    public void setContratacoes(Set<Contratacao> contratacoes) {
        this.contratacoes = contratacoes;
    }
    
    public Nacionalidade getNacionalidade() {
        return nacionalidade;
    }
    
    public String getNacionalidadeCompleta(){
        return nacionalidade.getDescricao() + " de " + this.localNascimento.getNome() + " - " + this.localNascimento.getEstado().getUF();
    }

    public void setNacionalidade(Nacionalidade nacionalidade) {
        this.nacionalidade = nacionalidade;
    }

    public CRM getCrm() {
        return crm;
    }

    public void setCrm(CRM crm) {
        this.crm = crm;
    }

    public EnderecoPessoa getEndereco() {
        return endereco;
    }

    public void setEndereco(EnderecoPessoa endereco) {
        this.endereco = endereco;
    }

    public Set<Dependencia> getDependencias() {
        return dependencias;
    }

    public Raca getRaca() {
        if(raca == null) return new Raca();
        return raca;
    }

    public void setRaca(Raca raca) {
        this.raca = raca;
    }
    
    public void setDependencias(Set<Dependencia> dependencias) {
        this.dependencias = dependencias;
    }

    public List<CaracteristicaFisicaPessoa> getCaracteristicasFisicas() {
        return caracteristicasFisicas;
    }

    public void setCaracteristicasFisicas(List<CaracteristicaFisicaPessoa> caracteristicas) {
        this.caracteristicasFisicas = caracteristicas;
    }

    public Set<OpcoesCaracteristicaPessoa> getCaracteristicas() {
        return caracteristicas;
    }

    public void setCaracteristicas(Set<OpcoesCaracteristicaPessoa> caracteristicas) {
        this.caracteristicas = caracteristicas;
    }

    public List<PlanoPessoa> getPlanos() {
        return planos;
    }

    public void setPlanos(List<PlanoPessoa> planos) {
        this.planos = planos;
    }

    public Ocupacao getOcupacao() {
        return ocupacao;
    }

    public void setOcupacao(Ocupacao ocupacao) {
        this.ocupacao = ocupacao;
    }

    public Cidade getLocalNascimento() {
        return localNascimento;
    }

    public void setLocalNascimento(Cidade localNascimento) {
        this.localNascimento = localNascimento;
    }
    
    public Set<DoencaPessoa> getDoencas() {
        return doencas;
    }

    public void setDoencas(Set<DoencaPessoa> doencas) {
        this.doencas = doencas;
    }

    public String getNomeInteiro() {
        return this.nome + " " + this.sobrenome;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }
    
    public Set<ProtocoloConsulta> getConsultas() {
        return consultas;
    }

    public void setConsultas(Set<ProtocoloConsulta> consultas) {
        this.consultas = consultas;
    }

    public Set<Medicacao> getMedicacoes() {
        return medicacoes;
    }

    public void setMedicacoes(Set<Medicacao> medicacoes) {
        this.medicacoes = medicacoes;
    }

    public Set<TratamentoPessoa> getTratamentos() {
        return tratamentos;
    }

    public void setTratamentos(Set<TratamentoPessoa> tratamentos) {
        this.tratamentos = tratamentos;
    }

    public Set<ExamePessoa> getExames() {
        return exames;
    }

    public void setExames(Set<ExamePessoa> exames) {
        this.exames = exames;
    }

    public Long getRg() {
        return rg;
    }

    public void setRg(Long rg) {
        this.rg = rg;
    }

    public Long getNumeroSUS() {
        return numeroSUS;
    }

    public void setNumeroSUS(Long numeroSUS) {
        this.numeroSUS = numeroSUS;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getTelefone() {
        return telefone;
    }

    public void setTelefone(Long telefone) {
        this.telefone = telefone;
    }
    
    public Long getCpf() {
        return cpf;
    }

    public void setCpf(Long cpf) {
        this.cpf = cpf;
    }

    public String getNomeMae() {
        return nomeMae;
    }

    public void setNomeMae(String nomeMae) {
        this.nomeMae = nomeMae;
    }

    public String getNomePai() {
        return nomePai;
    }

    public void setNomePai(String nomePai) {
        this.nomePai = nomePai;
    }

    public TipoSanguineo getTipoSanguineo() {
        return tipoSanguineo;
    }

    public void setTipoSanguineo(TipoSanguineo tipoSanguineo) {
        this.tipoSanguineo = tipoSanguineo;
    }

    public Boolean getDoadorOrgaos() {
        return doadorOrgaos;
    }

    public void setDoadorOrgaos(Boolean doadorOrgaos) {
        this.doadorOrgaos = doadorOrgaos;
    }

    public List<Familiar> getFamiliares() {
        return familiares;
    }

    public void setFamiliares(List<Familiar> familiares) {
        this.familiares = familiares;
    }

    public List<Alergia> getAlergias() {
        return alergias;
    }

    public void setAlergias(List<Alergia> alergias) {
        this.alergias = alergias;
    }

    public List<Medicamento> getAlergiasMedicamentos() {
        return alergiasMedicamentos;
    }

    public void setAlergiasMedicamentos(List<Medicamento> alergiasMedicamentos) {
        this.alergiasMedicamentos = alergiasMedicamentos;
    }
    
}
