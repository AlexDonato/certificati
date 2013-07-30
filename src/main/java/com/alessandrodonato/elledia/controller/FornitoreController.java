/**
 * 
 */
package com.alessandrodonato.elledia.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.alessandrodonato.elledia.model.Fornitore;
import com.alessandrodonato.elledia.service.FornitoreService;
import com.alessandrodonato.elledia.service.FornitoreServiceImpl;


/**
 * @author Alessandro Donato
 * 
 *         15/feb/2013
 */

@Controller
@SessionAttributes
public class FornitoreController {
	
	private static final Logger log = Logger.getLogger(FornitoreController.class);
	
	@Autowired
	private FornitoreServiceImpl fornitoriService;

	/**
	 * Handles request for adding new supplier
	 */
	@RequestMapping(value = "/addFornitore", method = RequestMethod.POST)
	public @ResponseBody String addFornitore (
			@RequestParam(value="ragioneSociale", required=true) String ragioneSociale,
			@RequestParam(value="telefono", required=true) String telefono,
			@RequestParam(value="piva", required=true) String piva, Model model) {
		
		Fornitore fornitore = new Fornitore ();
		fornitore.setRagioneSociale(ragioneSociale);
		fornitore.setTelefono(telefono);
		fornitore.setPiva(piva);

		short result = fornitoriService.save(fornitore);
		fornitoriService.findAllFornitori();

		String risposta;
		
		switch (result) {
			case FornitoreService.MSG_SALVATO		: 
					risposta = "Salvato.";
					break;
			case FornitoreService.MSG_DUPLICATO	: 
					risposta = "Questo fornitore esiste gia'.";
					break;
			case FornitoreService.MSG_ERRORE		: 
					risposta = "Si e' verificato un errore.";
					break;
			default : 
				risposta = "Si e' verificato un errore.";
				
		}
		
		return risposta;
		
	}

	@RequestMapping(value = "/addFornitore", method = RequestMethod.GET)
	public ModelAndView showFornitori() {

		return new ModelAndView("addFornitore", "command", new Fornitore ());
	}
}