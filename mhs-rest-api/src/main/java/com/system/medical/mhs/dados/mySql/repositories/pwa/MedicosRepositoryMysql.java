package com.system.medical.mhs.dados.mySql.repositories.pwa;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.system.medical.mhs.dados.mySql.AbstractRepository;
import com.system.medical.mhs.dados.mySql.entities.PlanoPessoa;
import com.system.medical.mhs.dados.mySql.entities.ProtocoloConsulta;
import com.system.medical.mhs.dominios.RespostaDomain;
import com.system.medical.mhs.dominios.pwa.interfaces.dashboard.IMedicosRepository;
import com.system.medical.mhs.dominios.pwa.modelos.dashboard.AgendaDia;
import com.system.medical.mhs.dominios.pwa.modelos.dashboard.ProximasConsultas;
import com.system.medical.mhs.dominios.pwa.modelos.dashboard.UltimasConsultas;
import com.system.medical.mhs.utils.Imagem;

import java.util.Calendar;
import java.util.stream.Collectors;

/**
 *
 * @author Leonardo Ariel
 */
@Repository
public class MedicosRepositoryMysql extends AbstractRepository implements IMedicosRepository {

    @Override
    public RespostaDomain buscarAgenda(Long crm, String uf) {
        RespostaDomain resposta;

        Calendar initOfDay = Calendar.getInstance();
        initOfDay.set(Calendar.HOUR_OF_DAY, 0);
        initOfDay.set(Calendar.MINUTE, 0);
        initOfDay.set(Calendar.SECOND, 0);

        Calendar endOfDay = Calendar.getInstance();
        endOfDay.set(Calendar.HOUR_OF_DAY, 23);
        endOfDay.set(Calendar.MINUTE, 59);
        endOfDay.set(Calendar.SECOND, 59);

        String query = "from ProtocoloConsulta p "
                + "join fetch p.id.crm crm join fetch crm.estado estado "
                + "where crm.valor = :crm and estado.uf = :uf "
                + "p.id.dataHora >= :initOfDay and "
                + "p.id.dataHora <= :endOfDay";

        Parametros parametros = new Parametros();
        parametros.put("crm", crm);
        parametros.put("uf", uf.toUpperCase());
        parametros.put("initOfDay", initOfDay.getTime());
        parametros.put("endOfDay", endOfDay.getTime());

        List<ProtocoloConsulta> consultas;
        try {
            consultas = this.selectByQuery(query, ProtocoloConsulta.class, parametros);
        } catch (Exception e) {
            resposta = new RespostaDomain(null);
            resposta.adicionarMensagemErro(e.toString());
            return resposta;
        }
            ArrayList<AgendaDia> agenda = new ArrayList<AgendaDia>();
        for (ProtocoloConsulta consulta : consultas) {
            agenda.add(
                new AgendaDia(
                    new Imagem(consulta.getPessoa().getFoto()).converterImagemBase64(),
                    consulta.getPessoa().getNomeInteiro(),
                    consulta.getConsulta().prontuarioCompartilhado(),
                    consulta.getHora()
                )
            );
        }
        resposta = new RespostaDomain(agenda);

        return resposta;
    }

    @Override
    public RespostaDomain buscarProximasConsultas(Long crm, String uf) {
        RespostaDomain resposta;
        Calendar now = Calendar.getInstance();

        String query = "from ProtocoloConsulta proct "
        		+ "join fetch proct.id.crm crm "
        		+ "join fetch proct.id.pessoa "
        		+ "where crm.valor = :crm and "
                + "crm.estado.UF = :uf and "
                + "proct.id.dataHora >= :now";

        Parametros parametros = new Parametros();
        parametros.put("crm", crm);
        parametros.put("uf", uf);
        parametros.put("now", now.getTime());

        List<ProtocoloConsulta> consultas;
        try {
            consultas = this.selectByQuery(query, ProtocoloConsulta.class, parametros);
        } catch (Exception e) {
            resposta = new RespostaDomain(null);
            resposta.adicionarMensagemErro(e.toString());
            return resposta;
        }
        ArrayList<ProximasConsultas> pConsultas = new ArrayList<ProximasConsultas>();
        String convenio;
        for (ProtocoloConsulta consulta : consultas) {
            PlanoPessoa plano = consulta.getConsulta().getPlanoUtilizado();
            if (plano == null) {
                convenio = "Particular";
            } else {
                convenio = plano.getPlano().getNome();
            }
            pConsultas.add(
                    new ProximasConsultas(
                        consulta.getPessoa().getFoto(),
                        consulta.getPessoa().getNomeInteiro(),
                        consulta.getConsulta().prontuarioCompartilhado(),
                        convenio,
                        consulta.getHora()
                    )
            );
        }
        resposta = new RespostaDomain(pConsultas);

        return resposta;
    }

    @Override
    public RespostaDomain buscarUltimasConsultas(Long crm, String uf) {
        RespostaDomain resposta;
        String query = "from ProtocoloConsulta pConsulta "
                + "join fetch pConsulta.id.pessoa p join fetch p.crm crm "
                + "join fetch crm.estado estado "
                + "where crm.valor = :crm and estado.uf = :uf";

        List<ProtocoloConsulta> consultas;
        try {
            Parametros parametros = new Parametros();
            parametros.put("crm", crm);
            parametros.put("uf", uf.toUpperCase());
            consultas = this.selectByQuery(query, ProtocoloConsulta.class, parametros);
        } catch (Exception e) {
            resposta = new RespostaDomain(null);
            resposta.adicionarMensagemErro(e.toString());
            return resposta;
        }
        ArrayList<UltimasConsultas> uConsultas = new ArrayList<UltimasConsultas>();
        
        for (ProtocoloConsulta consulta : consultas) {
            List<String> sintomas = consulta.getConsulta().getSintomas().stream().map(
                                    (s) -> s.getSintoma().getNome()).collect(Collectors.toList());
            uConsultas.add(
                //TODO Comentarios conclusivos da consulta
                new UltimasConsultas(
                    new Imagem(consulta.getPessoa().getFoto()).converterImagemBase64(),
                    consulta.getPessoa().getNomeInteiro(),
                    consulta.getDataCompleta(),
                    "Conclusoes...",
                    sintomas
                )
            );
        }
        resposta = new RespostaDomain(uConsultas);

        return resposta;
    }
}
