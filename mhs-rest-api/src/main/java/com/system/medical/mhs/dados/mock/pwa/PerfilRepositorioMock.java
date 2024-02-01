package com.system.medical.mhs.dados.mock.pwa;

import java.util.Date;

import com.system.medical.mhs.dominios.RespostaDomain;
//import com.system.medical.mhs.dominios.pwa.interfaces.pacientes.IPerfilRepository;
import com.system.medical.mhs.dominios.pwa.modelos.pacientes.Perfil;

public class PerfilRepositorioMock {

    public RespostaDomain<Perfil> Obter() {
        return new RespostaDomain(
        	new Perfil(
        		"localImagem",
                "Davi",
                "Alves",
                new Date(),
                "Masculino",
                "Espanhol"
        	)
        );
    }

    public RespostaDomain obterTudo() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public RespostaDomain obterPorId(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
