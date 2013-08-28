/**
 * 
 */
package com.alessandrodonato.elledia.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.alessandrodonato.elledia.helper.OperationMessageCodes;
import com.alessandrodonato.elledia.model.Certificato;
import com.alessandrodonato.elledia.model.Fornitore;
import com.alessandrodonato.elledia.model.Materiale;
import com.alessandrodonato.elledia.service.CertificatoService;
import com.alessandrodonato.elledia.service.FornitoreService;

/**
 * @author Alessandro Donato
 * 
 *         15/feb/2013
 */

@Controller
@SessionAttributes
public class CertificatoController {

	private static final Logger log = Logger.getLogger(CertificatoController.class);
	
	@Resource (name="certificatoService")
	CertificatoService certificatoService;
	
	@Resource (name="fornitoreService")
	FornitoreService fornitoreService;
	
	@RequestMapping("/newCertificato")
	public ModelAndView certificati () {
		SortedMap <String, String> fornitoreCombo = new TreeMap<String, String>();
		fornitoreCombo = fornitoreService.findAllFornitoriCombo();
		
		Map<String, Object> models = new HashMap <String, Object> ();
		models.put("fornitoreCombo", fornitoreCombo);
		
		Certificato certificato = new Certificato();
		certificato.setCodice(certificatoService.getNewCodice());
		
		models.put("certificato", certificato);
		
		return new ModelAndView("newCertificato", models);
	}
	
	/**
	 * Aggiunge un nuovo certificato
	 */
	@RequestMapping(value = "/salvaCertificato", method = RequestMethod.POST)
	public @ResponseBody int addMateriale (HttpServletRequest request, HttpServletResponse response) {

		String risposta;
		int result = -1;

		try {
			Certificato certificato = new Certificato();
			certificato.setData(new java.util.Date());
			certificato.setCodice(request.getParameter("codice"));
			ArrayList<Materiale> listaMateriali = new ArrayList<Materiale>();
			// Recupero e assegno il fornitore indicato
			String codiceFornitore = request.getParameter("fornitore");
			Fornitore fornitore = fornitoreService.findFornitoreById(Integer
					.parseInt(codiceFornitore));
			certificato.setFornitore(fornitore);
			Map<String, Object> map = request.getParameterMap();
			// Ricerca dei parametri riferiti alle righe dei materiali
			final Pattern pattern = Pattern
					.compile("(formCertificato-rowMateriale)+([0-9]*)(-colata)");
			Matcher matcher;
			// Per ogni materiale, creo un bean
			for (String name : map.keySet()) {
				log.debug("parametro [" + name + "]: ");
				matcher = pattern.matcher(name);

				if (matcher.find()) {
					log.debug("trovata colata " + request.getParameter(name) + " - "
							+ matcher.group(2));

					int rigaMateriale = Integer.parseInt(matcher.group(2));

					Materiale materiale = new Materiale();
					materiale.setColata(request
							.getParameter("formCertificato-rowMateriale" + rigaMateriale
									+ "-colata"));
					materiale.setUnitaMisura(request
							.getParameter("formCertificato-rowMateriale" + rigaMateriale
									+ "-unitaMisura"));
					materiale.setDimensione(request
							.getParameter("formCertificato-rowMateriale" + rigaMateriale
									+ "-dimensione"));
					materiale.setSpecificaMateriale(request
							.getParameter("formCertificato-rowMateriale" + rigaMateriale
									+ "-specifica"));

					listaMateriali.add(materiale);

					log.debug("Creato materiale " + materiale.toString());

				}
			}
			// Assegno la lista dei materiali al certificato
			certificato.setMateriali(listaMateriali);
			
			//----------- Salvataggio Certificato
			result = certificatoService.save(certificato);
			
		} catch (Exception ex) {
			log.error("Errore!!!!");
			ex.printStackTrace();
		}
		
		switch (result) {
			case OperationMessageCodes.MSG_SALVATO		: 
					risposta = "Certificato salvato";
					break;
			case OperationMessageCodes.MSG_DUPLICATO	: 
					risposta = "Certificato duplicato: non salvato";
					break;
			case OperationMessageCodes.MSG_ERRORE		: 
					risposta = "Errore nel salvataggio del certificato";
					break;
			default : 
				risposta = "Errore nel salvataggio del certificato";
				
		}
		
		log.debug(risposta);
		
		return result;
		
	}
	
	@RequestMapping (value = "/salvaFileCertificato", method = RequestMethod.POST)
	public @ResponseBody int saveCertificatoFile (MultipartHttpServletRequest request, HttpServletResponse response) {
		
		System.out.println(request.getFile("formCertificato-file"));
		
		return 1;
		
	}
	
}