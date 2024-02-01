package com.system.medical.mhs.acessos.api.pwa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.system.medical.mhs.dados.mySql.repositories.pwa.AgendaRepositoryMysql;
import com.system.medical.mhs.dominios.RespostaDomain;
import com.system.medical.mhs.dominios.app.acoes.agendamento.ActionsConsulta;
import com.system.medical.mhs.dominios.app.modelos.agendamento.AgendamentoConsulta;
import com.system.medical.mhs.dominios.pwa.acoes.ActionsAgendaRepository;
import com.system.medical.mhs.dominios.pwa.modelos.agenda.AgendarConsulta;
import com.system.medical.mhs.dominios.pwa.modelos.agenda.DadosConsulta;
import com.system.medical.mhs.dominios.pwa.modelos.agenda.HorarioIndisponivel;
import com.system.medical.mhs.dominios.pwa.modelos.agenda.SolicitacaoAlteracaoHorarioConsulta;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author Leonardo Ariel
 *
 */
@RestController
@RequestMapping("pwa/agenda")
public class AgendaController {

    @Autowired
    private AgendaRepositoryMysql agendaRepository;

    @RequestMapping("/dados")
    public ResponseEntity<?> getDados(Long cpf) throws Exception {
        if (this.agendaRepository.obterDados(cpf).contemErros()) {
            throw new Exception(this.agendaRepository.obterDados(cpf).converterErrosParaString());
        }
        try {
            return new ResponseEntity(new ActionsAgendaRepository(this.agendaRepository)
                    .obterDados(cpf).get(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(),
                    HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping("/plano")
    public ResponseEntity<?> getPlanoSaude(Long cpf) throws Exception {
        if(this.agendaRepository.obterPlanoSaude(cpf).contemErros()) { throw new Exception(this.agendaRepository.obterPlanoSaude(cpf).converterErrosParaString()); }
        try {
            return new ResponseEntity(new ActionsAgendaRepository(this.agendaRepository)
                    .obterPlanoSaude(cpf).get(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(),
                    HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping("/alergias")
    public ResponseEntity<?> getAlergias(Long cpf) {
        try {
            return new ResponseEntity(new ActionsAgendaRepository(this.agendaRepository)
                    .consultarAlergias(cpf).get(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(),
                    HttpStatus.BAD_REQUEST);
        }
    }


    @RequestMapping("/tratamentos")
    public ResponseEntity<?> getTratamentos(Long cpf) throws Exception{
        if (this.agendaRepository.consultarTratamentos(cpf).contemErros()) {
            throw new Exception(this.agendaRepository.consultarTratamentos(cpf).converterErrosParaString());
        }
        try {
            return new ResponseEntity(new ActionsAgendaRepository(this.agendaRepository)
                    .consultarTratamentos(cpf).get(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(),
                    HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping("/doencas/historico")
    public ResponseEntity<?> getHistorico(Long cpf) throws Exception{
    	if(this.agendaRepository.consultarHistorico(cpf).contemErros()){ throw new Exception(this.agendaRepository.consultarHistorico(cpf).converterErrosParaString()); }
        try {
            return new ResponseEntity(new ActionsAgendaRepository(this.agendaRepository)
                    .consultarHistorico(cpf).get(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(),
                    HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping("/exames/recentes")
    public ResponseEntity<?> getExamesRecentes(Long cpf) {
        try {
            return new ResponseEntity(new ActionsAgendaRepository(this.agendaRepository)
                    .consultarExamesRecentes(cpf).get(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(),
                    HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping("/consultas/recentes")
    public ResponseEntity<?> getConsultasRecentes(Long cpf) {
        try {
            return new ResponseEntity(new ActionsAgendaRepository(this.agendaRepository)
                    .consultarConsultasRecentes(cpf).get(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(),
                    HttpStatus.BAD_REQUEST);
        }
    }
    
    @RequestMapping("/disponibilidade/dia")
    public ResponseEntity<?> getDisponibilidadeDia(@RequestParam Long crm, @RequestParam String uf, @RequestParam Long dia){
        try {
            RespostaDomain resposta = new ActionsAgendaRepository(this.agendaRepository).buscarDisponibilidade(crm, uf, dia);
            if(resposta.contemErros())
                throw new Exception(resposta.converterErrosParaString());
            
            return new ResponseEntity(resposta.get(), HttpStatus.OK);
        } catch (Exception e) {
            
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    
    @RequestMapping(value = "/indisponibilizar", method = RequestMethod.POST)
    public ResponseEntity<?> indisponibilizarHorario(@RequestBody HorarioIndisponivel indisponiveis){
        try {
            RespostaDomain resposta = new ActionsAgendaRepository(this.agendaRepository).indisponibilizarHorario(indisponiveis);
            if(resposta.contemErros())
                throw new Exception(resposta.converterErrosParaString());
            
            return new ResponseEntity(resposta.get(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    
    @RequestMapping(value = "/consultas/dia", method = RequestMethod.GET)
    public ResponseEntity<?> getConsultasDia(@RequestParam Long crm, @RequestParam  String uf, @RequestParam  Long dia){
        try {
            RespostaDomain resposta = new ActionsAgendaRepository(this.agendaRepository).buscarConsultasDia(crm, uf, dia);
            if(resposta.contemErros())
                throw new Exception(resposta.converterErrosParaString());
            
            return new ResponseEntity(resposta.get(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    
    @RequestMapping(value = "/consultas/semana", method = RequestMethod.GET)
    public ResponseEntity<?> getConsultasSemana(@RequestParam Long crm, @RequestParam  String uf, @RequestParam  Long dia){
        try {
            RespostaDomain resposta = new ActionsAgendaRepository(this.agendaRepository).buscarConsultasSemana(crm, uf, dia);
            if(resposta.contemErros())
                throw new Exception(resposta.converterErrosParaString());
            
            return new ResponseEntity(resposta.get(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    
    @RequestMapping(value = "/consultas/mes", method = RequestMethod.GET)
    public ResponseEntity<?> getConsultasMes(@RequestParam Long crm, @RequestParam  String uf, @RequestParam  Long dia){
        try {
            RespostaDomain resposta = new ActionsAgendaRepository(this.agendaRepository).buscarConsultasMes(crm, uf, dia);
            if(resposta.contemErros())
                throw new Exception(resposta.converterErrosParaString());
            
            return new ResponseEntity(resposta.get(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    
    @RequestMapping(value = "/consultas/horario", method = RequestMethod.PUT)
    public ResponseEntity<?> alterarHorario(@RequestBody SolicitacaoAlteracaoHorarioConsulta solicitacao){
        try {
            RespostaDomain resposta = new ActionsAgendaRepository(this.agendaRepository).alterarHorarioConsulta(solicitacao);
            if(resposta.contemErros()) throw new Exception(resposta.converterErrosParaString());
            return new ResponseEntity(resposta.get(), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    
    @RequestMapping(value = "/consulta/dados", method = RequestMethod.GET)
    public ResponseEntity<?> getDadosConsulta(@RequestParam Long cpf){
        try {
            RespostaDomain resposta = new ActionsAgendaRepository(this.agendaRepository).obterDadosConsulta(cpf);
            if(resposta.contemErros())
                throw new Exception(resposta.converterErrosParaString());
            
            return new ResponseEntity(resposta.get(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    
    @RequestMapping(method = RequestMethod.POST, value = "/consulta/agendar")
    public ResponseEntity<?> agendarConsulta(@RequestBody AgendarConsulta agendar){
        try {
            return new ResponseEntity(new ActionsAgendaRepository(this.agendaRepository).adicionarNovaConsulta(agendar).get(), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }        
    }
    
    @RequestMapping(method = RequestMethod.GET, value = "/consulta/obter/medicos")
    public ResponseEntity<?> obterMedicos(@RequestParam Long idClinica){
        try {
            return new ResponseEntity(new ActionsAgendaRepository(this.agendaRepository).obterMedicosClinica(idClinica).get(), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }        
    }
    
    @RequestMapping(method = RequestMethod.GET, value = "/consulta/obter/planos")
    public ResponseEntity<?> obterPlanos(@RequestParam Long idClinica){
        try {
            return new ResponseEntity(new ActionsAgendaRepository(this.agendaRepository).obterPlanosClinica(idClinica).get(), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }        
    }
}
