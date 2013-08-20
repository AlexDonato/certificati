/**
 * 
 */
package com.alessandrodonato.elledia.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.alessandrodonato.elledia.helper.OperationMessageCodes;
import com.alessandrodonato.elledia.model.Fornitore;
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
	public @ResponseBody int addFornitore (HttpServletRequest request, HttpServletResponse response) {
		
		Fornitore fornitore = new Fornitore ();
		fornitore.setRagioneSociale(request.getParameter("ragioneSociale"));
		fornitore.setTelefono(request.getParameter("telefono"));
		fornitore.setPiva(request.getParameter("piva"));

		log.debug("Fornitore ragione sociale [" + fornitore.getRagioneSociale() + "]");
		short result = fornitoriService.save(fornitore);
		fornitoriService.findAllFornitori();

		switch (result) {
			case OperationMessageCodes.MSG_SALVATO		: 
					log.debug("Salvato.");
					break;
			case OperationMessageCodes.MSG_DUPLICATO	: 
					log.debug("Questo fornitore esiste gia'.");
					break;
			case OperationMessageCodes.MSG_ERRORE		: 
					log.debug("Si e' verificato un errore.");
					break;
			default : 
				log.debug("Si e' verificato un errore.");
				
		}
		
		return result;
		
	}

	@RequestMapping(value = "/addFornitore", method = RequestMethod.GET)
	public ModelAndView showFornitori() {

		return new ModelAndView("addFornitore", "command", new Fornitore ());
	}
}