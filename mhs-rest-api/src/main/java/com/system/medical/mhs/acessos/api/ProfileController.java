package com.system.medical.mhs.acessos.api;

import java.io.IOException;
import java.io.InputStream;
import org.apache.commons.io.IOUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("profile")
public class ProfileController {
	
	@RequestMapping(method = RequestMethod.GET, value = "/medicos/photo", produces = org.springframework.http.MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<?> profilePhoto(Long crm, String uf) throws IOException{
    	try {
    		String imageUri = "/profiles/medicos/" + crm + uf.toUpperCase() + ".jpg";
    		
    		InputStream in = this.getClass().getResourceAsStream(imageUri);
    		return new ResponseEntity(IOUtils.toByteArray(in), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
    	
    	
    }
	
}
