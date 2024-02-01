package com.system.medical.mhs.dominios;

import java.util.ArrayList;

public abstract class RespostaBase {
    private ArrayList<String> mensagensErro = new ArrayList<String>();

    public ArrayList<String> getMensagensErro() {
        return mensagensErro;
    }

    public void adicionarMensagemErro(String mensagem) {
        mensagensErro.add(mensagem);
    }

    public void adicionarMensagensErros(ArrayList<String> mensagens) {
        mensagensErro.addAll(mensagens);
    }

    public boolean contemErros() {
        return mensagensErro.size() > 0;
    }
    
    public String converterErrosParaString(){
		String mensagens = "";
    	for(String mensagem : mensagensErro){
			mensagens = mensagens+" "+mensagem;
		}
    	return mensagens;
    }
} 