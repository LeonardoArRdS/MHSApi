package com.system.medical.mhs.acessos.api.pwa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.system.medical.mhs.dados.mySql.repositories.pwa.MedicosRepositoryMysql;
import com.system.medical.mhs.dados.mySql.repositories.pwa.NovidadesRepositoryMysql;
import com.system.medical.mhs.dados.mySql.repositories.pwa.PlanoRepositoryMysql;
import com.system.medical.mhs.dominios.pwa.acoes.dashboard.ActionsMedicosRepository;
import com.system.medical.mhs.dominios.pwa.acoes.dashboard.ActionsNovidadesRepository;
import com.system.medical.mhs.dominios.pwa.acoes.dashboard.ActionsPlanoRepository;

/**
 * @author Leonardo Ariel
 *
 */
@RestController
@RequestMapping("pwa/dashboard")
public class DashboardController {
    @Autowired
    private MedicosRepositoryMysql medicosRepository;

    @Autowired
    private PlanoRepositoryMysql planoRepository;
    
    @Autowired
    private NovidadesRepositoryMysql novidadesRepository;
    
    @RequestMapping("/novidades")
    public ResponseEntity<?> getNovidades(Long crm, String uf) throws Exception {
    	//news!
    	//if(this.novidadesRepository.buscarNovidadesPacientes(crm, uf).contemErros()) { throw new Exception(this.novidadesRepository.buscarNovidadesPacientes(crm, uf).converterErrosParaString());}
    	try {
        	return new ResponseEntity(new ActionsNovidadesRepository(this.novidadesRepository).buscarNovidadesPacientes(crm, uf).get() , HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(),
                    HttpStatus.BAD_REQUEST);
        }
    }
    
    @RequestMapping("/agenda/dia")
    public ResponseEntity<?> getAgenda(Long crm, String uf) throws Exception {
    	//if(this.medicosRepository.buscarAgenda(crm, uf).contemErros()){ throw new Exception(this.medicosRepository.buscarAgenda(crm, uf).converterErrosParaString()); }
    	try {
        	return new ResponseEntity(new ActionsMedicosRepository(this.medicosRepository).buscarAgenda(crm, uf).get(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(),
                    HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping("/consultas/proximas")
	public ResponseEntity<?> getProximasConsultas(Long crm, String uf) throws Exception {
    	if(medicosRepository.buscarProximasConsultas(crm, uf).contemErros()){ throw new Exception(medicosRepository.buscarProximasConsultas(crm, uf).converterErrosParaString()); }
    	try {
        	return new ResponseEntity(new ActionsMedicosRepository(this.medicosRepository).buscarProximasConsultas(crm, uf).get(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(),
                    HttpStatus.BAD_REQUEST);
        }
    }
    
    @RequestMapping("/consultas/ultimas")
    public ResponseEntity<?> getUltimasConsultas(Long crm, String uf) throws Exception {
    	if(medicosRepository.buscarUltimasConsultas(crm, uf).contemErros()){ medicosRepository.buscarUltimasConsultas(crm, uf).converterErrosParaString(); }
    	try {
        	return new ResponseEntity(new ActionsMedicosRepository(this.medicosRepository).buscarUltimasConsultas(crm, uf).get(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(),
                    HttpStatus.BAD_REQUEST);
        }
    }


    @RequestMapping("/plano/validar")
    public ResponseEntity<?> getValidarNumero(String empresa, Long numero) throws Exception {       
    	if(planoRepository.validarNumero(empresa, numero).contemErros()){ throw new Exception(planoRepository.validarNumero(empresa, numero).converterErrosParaString()); }
    	try {
        	return new ResponseEntity(new ActionsPlanoRepository(this.planoRepository).validarNumero(empresa, numero).get(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(),
                    HttpStatus.BAD_REQUEST);
        }
    }
}
