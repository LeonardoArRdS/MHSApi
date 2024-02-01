package com.system.medical.mhs.utils;

import java.util.Calendar;
import java.util.Date;

import org.joda.time.LocalDateTime;

/**
 * @author Leonardo Ariel
 *
 */
public class Funcoes {
	public static int calcularIdade(Calendar nascimento){
		int idade,
			dias, meses, anos,
			totalDias, diasMeses, diasAnos;
		LocalDateTime hoje = LocalDateTime.now();
		
		anos = hoje.getYear()- nascimento.get(nascimento.YEAR);
		diasAnos = anos*365;
		meses = hoje.getMonthOfYear()-nascimento.get(nascimento.MONTH);
		diasMeses = meses*30;
		dias = hoje.getDayOfMonth()-nascimento.get(nascimento.DAY_OF_MONTH);
		totalDias= diasAnos-diasMeses-dias;
		idade = (int) totalDias/365;
		
		return idade;
	}
	
	public static int calcularDiasEntre(Date dataInicio, Date dataTermino){
		int dias, meses, anos,
			totalDias, diasMeses, diasAnos;
		
		Calendar termino = Calendar.getInstance();
		termino.setTime(dataTermino);
		
		Calendar inicio = Calendar.getInstance();
		inicio.setTime(dataInicio);
		
		anos = inicio.get(inicio.YEAR) - termino.get(termino.YEAR);
		diasAnos = anos*365;
		meses = inicio.get(inicio.MONTH) - termino.get(termino.MONTH);
		diasMeses = meses*30;
		dias = inicio.get(inicio.DAY_OF_MONTH) - termino.get(termino.DAY_OF_MONTH);
		totalDias= diasAnos-diasMeses-dias;
		
		return totalDias;
	}
}
