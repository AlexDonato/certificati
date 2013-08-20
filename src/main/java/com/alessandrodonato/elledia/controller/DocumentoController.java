/**
 * 
 */
package com.alessandrodonato.elledia.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

/**
 * @author Alessandro Donato
 *
 * 16/ago/2013
 */

@Controller
@SessionAttributes
public class DocumentoController {

	@RequestMapping (value = "addDocument", method = RequestMethod.POST)
	public int addDocumento () {
		return 0;
		
	}

}
