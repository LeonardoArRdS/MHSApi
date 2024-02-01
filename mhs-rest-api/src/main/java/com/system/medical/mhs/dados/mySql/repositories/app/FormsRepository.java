/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.system.medical.mhs.dados.mySql.repositories.app;

import com.system.medical.mhs.dados.mySql.AbstractRepository;
import com.system.medical.mhs.dados.mySql.entities.Caracteristica;
import com.system.medical.mhs.dados.mySql.entities.CaracteristicaFisica;
import com.system.medical.mhs.dados.mySql.entities.CaracteristicaFisicaPessoa;
import com.system.medical.mhs.dados.mySql.entities.CategoriaCaracteristica;
import com.system.medical.mhs.dados.mySql.entities.OpcoesCaracteristica;
import com.system.medical.mhs.dados.mySql.entities.OpcoesCaracteristicaPessoa;
import com.system.medical.mhs.dados.mySql.entities.Pessoa;
import com.system.medical.mhs.dominios.RespostaDomain;
import com.system.medical.mhs.dominios.app.interfaces.forms.IFormsRepository;
import com.system.medical.mhs.dominios.app.modelos.forms.FormsOpcaoPergunta;
import com.system.medical.mhs.dominios.app.modelos.forms.FormsPergunta;
import com.system.medical.mhs.dominios.app.modelos.forms.FormsResposta;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.transaction.Transactional;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Felipe
 */
@Repository
public class FormsRepository extends AbstractRepository implements IFormsRepository {

    @Override
    public RespostaDomain buscarPerguntasIniciais() {
        Long idBasicas = new Long(1);
        String queryBasicas = "from CategoriaCaracteristica catg where catg.id = :id";
        CategoriaCaracteristica categoria = this.getSingleResult(queryBasicas, CategoriaCaracteristica.class, new Parametros("id", idBasicas));
        List<FormsPergunta> perguntas = new ArrayList<>();
        categoria.getCaracteristicasFisica().forEach((caract) -> {
            perguntas.add(new FormsPergunta(caract.getId(), caract.getNome(), true, null));
        });

        categoria.getCaracteristicas().forEach((caract) -> {
            List<FormsOpcaoPergunta> opcoes = caract.getOpcoes().stream().map((opcao) -> new FormsOpcaoPergunta(opcao.getId(), opcao.getOpcao())).collect(Collectors.toList());
            perguntas.add(new FormsPergunta(caract.getId(), caract.getDescricao(), false, opcoes));
        });

        return new RespostaDomain(perguntas);
    }

    @Override
    @Transactional
    public RespostaDomain responder(List<FormsResposta> respostas) {
        List<Object> caracts = new ArrayList<>();
        respostas.stream().forEach((resposta) -> {
            Object obj;
            if (resposta.getCaracteristicaFisica()) {
                obj = tratarCaracteristicaFisica(resposta);
            } else {
                obj = tratarCaracteristica(resposta);
            }

            if (!(obj == null)) {
                caracts.add(obj);
            }
        });

        try {
            this.bulkPersist(caracts);
            return new RespostaDomain(true);
        } catch (Exception e) {
            e.printStackTrace();
            RespostaDomain resposta = new RespostaDomain(false);
            resposta.adicionarMensagemErro(e.getMessage());

            return resposta;
        }
    }

    private CaracteristicaFisicaPessoa tratarCaracteristicaFisica(FormsResposta resposta) {
        Pessoa p = this.findById(Pessoa.class, resposta.getIdPaciente());
        CaracteristicaFisica c = this.findById(CaracteristicaFisica.class, resposta.getIdPergunta());
        CaracteristicaFisicaPessoa caractFPessoa = new CaracteristicaFisicaPessoa(p, c);
        caractFPessoa.setValor(resposta.getValorCaractFisica());

        if (resposta.getValorCaractFisica() == null) {
            return null;
        }

        return caractFPessoa;
    }

    private Object tratarCaracteristica(FormsResposta resposta) {
        Pessoa p = this.findById(Pessoa.class, resposta.getIdPaciente());
        Caracteristica c = this.findById(Caracteristica.class, resposta.getIdPergunta());
        OpcoesCaracteristica o = this.findById(OpcoesCaracteristica.class, resposta.getIdOpcao());
        OpcoesCaracteristicaPessoa caractPessoa = new OpcoesCaracteristicaPessoa(c, o, p);
        caractPessoa.setDescricao(resposta.getValorado());

        return caractPessoa;
    }

}
