package com.system.medical.mhs.acessos.api.pwa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.system.medical.mhs.dados.mock.pwa.PacientesRepositorioMock;
import com.system.medical.mhs.dados.mySql.repositories.pwa.PacientesRepositoryMySql;
import com.system.medical.mhs.dominios.pwa.acoes.ActionsPaciente;

@RestController
@RequestMapping("pwa/pacientes")
public class PacientesController {

    @Autowired
    private PacientesRepositoryMySql pacientesRepository;

    @RequestMapping("/alergias")
    public ResponseEntity<?> getAlergias(Long cpf) throws Exception {
    	if(pacientesRepository.consultarAlergiasConhecidas(cpf).contemErros()){ throw new Exception(pacientesRepository.consultarAlergiasConhecidas(cpf).converterErrosParaString()); }
    	try {
        	return new ResponseEntity(new ActionsPaciente(new PacientesRepositorioMock()).consultarAlergiasConhecidas((long) 123).get(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(),
                    HttpStatus.BAD_REQUEST);
        }
    }
    
    @RequestMapping("/dados")
    public ResponseEntity<?> getDados(Long cpf) throws Exception {
    	if(pacientesRepository.obterDados(cpf).contemErros()){ throw new Exception(pacientesRepository.obterDados(cpf).converterErrosParaString());}
    	try {
        	return new ResponseEntity(new ActionsPaciente(this.pacientesRepository).obterDados(cpf).get(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(),
                    HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping("/dadosPessoais")
    public ResponseEntity<?> getDadosPessoais(Long cpf) throws Exception {
    	if(pacientesRepository.obterDadosPessoais(cpf).contemErros()){ throw new Exception(pacientesRepository.obterDadosPessoais(cpf).converterErrosParaString());}
    	try {
        	return new ResponseEntity(new ActionsPaciente(this.pacientesRepository).obterDadosPessoais(cpf).get(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(),
                    HttpStatus.BAD_REQUEST);
        }
    }
    
    @RequestMapping("/doencas/historico")
    public ResponseEntity<?> getHistoricoDoencas(Long cpf) throws Exception {
    	try {
    		return new ResponseEntity(new ActionsPaciente(this.pacientesRepository).consultarHistoricoDoencas(cpf).get(), HttpStatus.OK);
    	} catch (Exception e) {
            return new ResponseEntity(e.getMessage(),
                    HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping("/exames/recentes")
    public ResponseEntity<?> getExamesRecentes(Long cpf) throws Exception {
        if(pacientesRepository.consultarExamesRecentes(cpf).contemErros()){ throw new Exception(pacientesRepository.consultarExamesRecentes(cpf).converterErrosParaString()); }
    	try {
        	return new ResponseEntity(new ActionsPaciente(this.pacientesRepository).consultarExamesRecentes(cpf).get(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(),
                    HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping("/exames/recentes/completo")
    public ResponseEntity<?> getExamesRecentesCompleto(Long cpf) throws Exception {
    	if(this.pacientesRepository.consultarExamesRecentesCompleto(cpf).contemErros()){ throw new Exception(this.pacientesRepository.consultarExamesRecentesCompleto(cpf).converterErrosParaString()+"TESTE"); }
    	try {
    		//return new ResponseEntity(new ActionsPaciente(this.pacientesRepository).consultarExamesRecentesCompleto(cpf).get(), HttpStatus.OK);
    		return new ResponseEntity(new ActionsPaciente(this.pacientesRepository).consultarExamesRecentesCompleto((long) 123).get(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(),
                    HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping("/medicacoes/frequentes")
    public ResponseEntity<?> getMedicacaoFrequente(Long cpf) throws Exception {
    	try {
        	//return new ResponseEntity(new ActionsPaciente(this.pacientesRepository).consultarMedicacoesFrequentes(cpf).get(), HttpStatus.OK);
        	//teste mock
        	return new ResponseEntity(new ActionsPaciente(this.pacientesRepository).consultarMedicacoesFrequentes(cpf).get(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(),
                    HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping("/prescricao")
    public ResponseEntity<?> getPrescricao(Long cpf) throws Exception {
    	try {
    		return new ResponseEntity(new ActionsPaciente(this.pacientesRepository).consultarPrescricao(cpf).get(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(),
                    HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping("/procedimentos/atuais")
    public ResponseEntity<?> getProcedimentosAtuais(Long cpf) throws Exception {
    	try {
//            return new ResponseEntity(pacientesRepository.consultarProcedimentosAtuais(cpf).getProcedimentosAtuais(),
//                    HttpStatus.OK);
              return new ResponseEntity(new ActionsPaciente(new PacientesRepositorioMock()).consultarProcedimentosAtuais((long) 123).get(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(),
                    HttpStatus.BAD_REQUEST);
        }
    }
}
