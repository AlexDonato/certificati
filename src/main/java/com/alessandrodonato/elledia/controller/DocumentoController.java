/**
 * 
 */
package com.alessandrodonato.elledia.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.alessandrodonato.elledia.model.Documento;

/**
 * @author Alessandro Donato
 *
 * 16/ago/2013
 */

@Controller
@SessionAttributes
public class DocumentoController {

	@RequestMapping (value = "new-document", method = RequestMethod.GET)
	public ModelAndView addDocumento () {
		
		Documento documento = new Documento();
		
		return new ModelAndView ("new-document", "documento", documento);
		
	}

}
