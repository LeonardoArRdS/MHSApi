package com.system.medical.mhs.dados.mySql.repositories.pwa;

import org.springframework.stereotype.Repository;

import com.system.medical.mhs.dados.mySql.AbstractRepository;
import com.system.medical.mhs.dados.mySql.AbstractRepository.Parametros;
import com.system.medical.mhs.dados.mySql.entities.PlanoPessoa;
import com.system.medical.mhs.dominios.RespostaDomain;
import com.system.medical.mhs.dominios.pwa.interfaces.dashboard.IPlanoRepository;

/**
*
* @author Leonardo Ariel
*/
@Repository
public class PlanoRepositoryMysql extends AbstractRepository implements IPlanoRepository {

	@Override
	public RespostaDomain validarNumero(String empresa, Long numero) {
		RespostaDomain resposta;
		String query = "pp from PlanoPessoa pp "
					+ "join fetch pp.id_plano.plano p "
					+ "join fetch p.id_convenio.convenio c "
					+ "where pp.numero = :numero and "
					+ "c.nome = :empresa";
		
        Parametros parametros = new Parametros();
        parametros.put("empresa", empresa);
        parametros.put("numero", numero);

        PlanoPessoa planoPessoa;
        try {
            planoPessoa = this.getSingleResult(query, PlanoPessoa.class, parametros);
        } catch (Exception e) {
            resposta = new RespostaDomain(null);
            resposta.adicionarMensagemErro("Paciente não encontrado para a carteirinha "+numero+" para fornecedor "+empresa);
            resposta.adicionarMensagemErro(e.getMessage());
            return resposta;
        }
        resposta = new RespostaDomain(
            planoPessoa.getPessoa().getCpf()
        );
        return resposta;
	}
}
