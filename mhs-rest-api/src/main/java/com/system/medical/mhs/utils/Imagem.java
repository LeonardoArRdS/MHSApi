package com.system.medical.mhs.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.apache.tomcat.util.codec.binary.Base64;
/**
*
* @author Leonardo Ariel
*/
public class Imagem {
	private String caminho;
	
	public Imagem(String caminho) {
		this.caminho = caminho;
	}
	
	public String getCaminho() { return caminho; }
	
	public String converterImagemBase64() {
		if(caminho == null || caminho.equals("")){ return null; } //Se o valor do caminho for nulo, retorna nulo, evitando tentar converter.
    	File file =  new File(caminho);
        String encodedString = null;
        try {
            byte[] bytes = loadFile(file);
    		byte[] encoded = Base64.encodeBase64(bytes);
    		encodedString = new String(encoded);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return encodedString;
    }
    private static byte[] loadFile(File file) throws IOException {
	    InputStream is = new FileInputStream(file);

	    long length = file.length();
	    if (length > Integer.MAX_VALUE) {
	        // File is too large
	    }
	    byte[] bytes = new byte[(int)length];
	    
	    int offset = 0;
	    int numRead = 0;
	    while (offset < bytes.length
	           && (numRead=is.read(bytes, offset, bytes.length-offset)) >= 0) {
	        offset += numRead;
	    }

	    if (offset < bytes.length) {
	        throw new IOException("Could not completely read file "+file.getName());
	    }

	    is.close();
	    return bytes;
	}
}
