/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.system.medical.mhs.acessos.api.app;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.ServletContext;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.system.medical.mhs.dados.mySql.repositories.app.ConsultasMedicoRepositoryMySql;
import com.system.medical.mhs.dados.mySql.repositories.app.MedicosRepositoryMySql;
import com.system.medical.mhs.dominios.RespostaDomain;
import com.system.medical.mhs.dominios.app.acoes.ActionsConsultaMedico;
import com.system.medical.mhs.dominios.app.acoes.ActionsMedico;
import com.system.medical.mhs.utils.exceptions.InvalidInputParameterException;

/**
 *
 * @author Felipe
 */
@Controller
@RequestMapping("/app/medicos")
public class MedicoController {

    @Autowired
    private ConsultasMedicoRepositoryMySql consultasRepository;

    @Autowired
    private MedicosRepositoryMySql medicosRepository;
    
    @Autowired
    private ServletContext servletContext;

    @RequestMapping("/horarios")
    public ResponseEntity<?> buscarHorariosPorCRM(Long crm, String uf) {
        try {
            return new ResponseEntity(new ActionsConsultaMedico(this.consultasRepository).getHorariosDisponiveis(crm, uf).get(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping("/horarios/dia")
    public ResponseEntity<?> buscarHorariosPorCRMDia(Long dia, Long crm, String uf, Long idEspecialidade) {
        try {
            return new ResponseEntity(new ActionsConsultaMedico(this.consultasRepository).getHorariosDisponiveisPorDia(crm, uf, dia, idEspecialidade).get(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping("/search")
    public ResponseEntity<?> buscarMedicos(@RequestParam(required = false) Long idEspecialidade, @RequestParam(required = false) Long idRegiao, @RequestParam(required = false) Long idPlano) {
        RespostaDomain resposta;
        try {
            resposta = new ActionsMedico(this.medicosRepository).buscar(idPlano, idEspecialidade, idRegiao);
            return new ResponseEntity(resposta.get(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

//        Object response;
//        try {
//            switch (by) {
//                case "plano":
//                    response = this.buscarMedicosByPlano(id);
//                    break;
//                case "especialidade":
//                default:
//                    response = this.buscarMedicosByEspecialidade(id);
//
//            }
//        } catch (Exception e) {
//            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
//        }
//
//        return new ResponseEntity(response, HttpStatus.OK);
    }

    private List buscarMedicosByEspecialidade(Long idEspecialidade) {
        return new ActionsMedico(this.medicosRepository).buscarPorEspecialidade(idEspecialidade).get();
    }

    private List buscarMedicosByPlano(Long idPlano) {
        return new ActionsMedico(this.medicosRepository).buscarPorPlano(idPlano).get();
    }

    @RequestMapping("/detalhes")
    public ResponseEntity<?> buscarDetalhes(Long crm, String uf, Long idEspecialidade) {
        try {
        	RespostaDomain resposta = new ActionsMedico(this.medicosRepository).getDetalhe(crm, uf, idEspecialidade);
        	if(resposta.contemErros())
        		throw new Exception(resposta.converterErrosParaString());
            return new ResponseEntity(resposta.get(), HttpStatus.OK);
        } catch(InvalidInputParameterException e){
        	return new ResponseEntity(e.getMappedErrors(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    

}
