package com.system.medical.mhs.utils;

import com.system.medical.mhs.utils.exceptions.InvalidInputParameterException;

public class Validations {
	public static Boolean isValidCRM(Long crm, String uf) throws InvalidInputParameterException{
		Boolean validCrm = (crm != null) && (crm > 0);
		Boolean validUf = (uf != null) && (uf.length() == 2);
		
		if(validCrm && validUf) return true;
		
		InvalidInputParameterException ex = new InvalidInputParameterException();
		
		if(!validCrm)
			ex.addError("crm", "CRM inválido");
		
		if(!validUf)
			ex.addError("uf", "UF inválida");
		
		throw ex;
		
	}
}
