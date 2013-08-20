/**
 * 
 */
package com.alessandrodonato.elledia.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.alessandrodonato.elledia.model.Certificato;
import com.alessandrodonato.elledia.service.CertificatoService;

/**
 * @author Alessandro Donato
 * 
 *         15/feb/2013
 */

@Controller
@SessionAttributes
public class CertificatoController {
	
	CertificatoService certificatoService;
	
	@RequestMapping(value = "/insertCertificato", method = RequestMethod.POST)
	public String addContact (@ModelAttribute("certificato") Certificato certificato,
															BindingResult result) {

		System.out.println(certificato.toString());
		
		return "redirect:certificati.html";
	}

	@RequestMapping("/newCertificato")
	public ModelAndView certificati () {
		return new ModelAndView("newCertificato", "certificato", new Certificato());
	}
	
	/**
	 * Handles request for adding new supplier
	 */
	@RequestMapping(value = "/addMateriale", method = RequestMethod.POST)
	public @ResponseBody String addMateriale (
			@RequestParam(value="colata", required=true) String colata,
			@RequestParam(value="specificaMateriale", required=true) String specificaMateriale,
			@RequestParam(value="unitaMisura", required=true) String unitaMisura,
			@RequestParam(value="dimensione", required=true) String dimensione,	Model model) {
		
//		Fornitore fornitore = new Fornitore ();
//		fornitore.setNome(nome);
//		fornitore.setTelefono(telefono);
//		fornitore.setPiva(piva);
//
//		short result = fornitoriService.save(fornitore);
//		fornitoriService.findAllFornitori();
//
//		String risposta;
//		
//		switch (result) {
//			case FornitoreService.MSG_SALVATO		: 
//					risposta = "Salvato.";
//					break;
//			case FornitoreService.MSG_DUPLICATO	: 
//					risposta = "Questo fornitore esiste gia'.";
//					break;
//			case FornitoreService.MSG_ERRORE		: 
//					risposta = "Si e' verificato un errore.";
//					break;
//			default : 
//				risposta = "Si e' verificato un errore.";
//				
//		}
//		
//		return risposta;
		
			return "ciao";
		
	}
	
}