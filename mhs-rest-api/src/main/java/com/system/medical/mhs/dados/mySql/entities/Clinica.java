/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.system.medical.mhs.dados.mySql.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author Felipe
 */
@Entity
@Table(name = "MHS.clinica")
public class Clinica implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @Column(name = "CNPJ")
    private String CNPJ;
    
    @Column(name = "nome_fantasia")
    private String nome;
    
    @Column(name = "razao_social")
    private String razaoSocial;
    
    @Column(name = "cnes")
    private Long CNES;
    
    @OneToOne(mappedBy = "clinica")
    private EnderecoClinica endereco;
    
    @OneToMany(mappedBy = "clinica")
    private Set<ClinicaEspecialidade> especialidades = new HashSet<>();
    
    @ManyToMany
    @JoinTable(name = "MHS.filiacao", joinColumns = @JoinColumn(name = "id_matriz"), inverseJoinColumns = @JoinColumn(name = "id_filial"))
    private Set<Clinica> filiais = new HashSet<>();
   
    @ManyToMany
    @JoinTable(name = "MHS.clinica_plano", joinColumns = @JoinColumn(name = "id_clinica"), inverseJoinColumns = @JoinColumn(name = "id_plano"))
    private Set<Plano> planosCobertos = new HashSet<>();
    
    public Clinica() {
    }

    public Clinica(Long id) {
        this.id = id;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public String getCNPJ() {
        return CNPJ;
    }

    public void setCNPJ(String CNPJ) {
        this.CNPJ = CNPJ;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getRazaoSocial() {
        return razaoSocial;
    }

    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }

    public Long getCNES() {
        return CNES;
    }

    public void setCNES(Long CNES) {
        this.CNES = CNES;
    }
    
    public EnderecoClinica getEndereco() {
        return endereco;
    }

    public void setEndereco(EnderecoClinica endereco) {
        this.endereco = endereco;
    }

    public Set<ClinicaEspecialidade> getEspecialidades() {
        return especialidades;
    }

    public void setEspecialidades(Set<ClinicaEspecialidade> especialidades) {
        this.especialidades = especialidades;
    }

    public Set<Clinica> getFiliais() {
        return filiais;
    }

    public void setFiliais(Set<Clinica> filiais) {
        this.filiais = filiais;
    }

    public Set<Plano> getPlanosCobertos() {
        return planosCobertos;
    }

    public void setPlanosCobertos(Set<Plano> planosCobertos) {
        this.planosCobertos = planosCobertos;
    }

    @Override
    public String toString() {
        return this.nome + " - CNPJ: " + this.CNPJ + " - Endereço: " + this.endereco;
    }
    
    
    
}
