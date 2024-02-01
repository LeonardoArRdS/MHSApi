/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.system.medical.mhs.acessos.api.app;

import com.system.medical.mhs.dados.mySql.repositories.app.ResumoProntuarioRepositoryMySql;
import com.system.medical.mhs.dominios.RespostaDomain;
import com.system.medical.mhs.dominios.app.acoes.prontuario.AcoesResumoProntuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Felipe
 */
@RestController
@RequestMapping("/app/prontuario/resumo")
public class ResumoProntuarioController {

    @Autowired
    private ResumoProntuarioRepositoryMySql resumoRepository;

    @RequestMapping("/dados")
    public ResponseEntity<?> getDadosPessoais(@RequestParam Long idPaciente) {
        try {
            RespostaDomain resposta = new AcoesResumoProntuario(this.resumoRepository).getDados(idPaciente);
            if (resposta.contemErros()) {
                throw new Exception(resposta.converterErrosParaString());
            }

            return new ResponseEntity(resposta.get(), HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping("/saude")
    public ResponseEntity<?> get(@RequestParam Long idPaciente) {
        try {
            RespostaDomain resposta = new AcoesResumoProntuario(this.resumoRepository).getDadosSaude(idPaciente);
            if (resposta.contemErros()) {
                throw new Exception(resposta.converterErrosParaString());
            }

            return new ResponseEntity(resposta.get(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping("/alergias")
    public ResponseEntity<?> getAlergias(@RequestParam Long idPaciente) {
        try {
            RespostaDomain resposta = new AcoesResumoProntuario(this.resumoRepository).getAlergias(idPaciente);
            if (resposta.contemErros()) {
                throw new Exception(resposta.converterErrosParaString());
            }

            return new ResponseEntity(resposta.get(), HttpStatus.OK);
        } catch (Exception e) {

            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping("/tratamentos")
    public ResponseEntity<?> getTratamentos(@RequestParam Long idPaciente) {
        try {
            RespostaDomain resposta = new AcoesResumoProntuario(this.resumoRepository).getTratamentos(idPaciente);

            if (resposta.contemErros()) {
                throw new Exception(resposta.converterErrosParaString());
            }

            return new ResponseEntity(resposta.get(), HttpStatus.OK);
        } catch (Exception e) {

            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping("/doencas")
    public ResponseEntity<?> getHistoricoDoencas(@RequestParam Long idPaciente) {
        try {

            RespostaDomain resposta = new AcoesResumoProntuario(this.resumoRepository).getHistoricoDoencas(idPaciente);

            if (resposta.contemErros()) {
                throw new Exception(resposta.converterErrosParaString());
            }

            return new ResponseEntity(resposta.get(), HttpStatus.OK);

        } catch (Exception e) {

            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping("/consultas")
    public ResponseEntity<?> getConsultas(@RequestParam Long idPaciente) {
        try {
            RespostaDomain resposta = new AcoesResumoProntuario(this.resumoRepository).getConsultas(idPaciente);
            
             if (resposta.contemErros()) {
                throw new Exception(resposta.converterErrosParaString());
            }
             
             return new ResponseEntity(resposta.get(), HttpStatus.OK);
            
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
