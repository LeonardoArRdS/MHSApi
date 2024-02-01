package com.system.medical.mhs.utils.exceptions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InvalidInputParameterException extends Exception{

	private static final long serialVersionUID = 1L;
	
	private Map<String, String> fieldErrors = new HashMap<>();
	
	public void addError(String field, String error){
		this.fieldErrors.put(field, error);
	}
	
	public List<String> getErrors() {
		List<String> errors = new ArrayList<>();
		for(Map.Entry<String, String> fieldError : fieldErrors.entrySet()){
			errors.add(fieldError.getKey() + " - " + fieldError.getValue());
		}
		
		return errors;
	}
	
	public Map<String, String> getMappedErrors(){
		return this.fieldErrors;
	}
	
	@Override
	public String toString() {
		String str = "{InvalidInputParameterException: [";
		for(Map.Entry<String, String> fieldError : fieldErrors.entrySet()){
			str += "{ field: " + fieldError.getKey() + ", value: " + fieldError.getValue() + "}";
		}
		str += "]";
		
		return str;
	}
	
	@Override
	public String getMessage(){
		return this.toString();
	}
	
}
