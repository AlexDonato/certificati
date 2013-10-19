/**
 * 
 */
package com.alessandrodonato.elledia.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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

import org.apache.commons.beanutils.converters.DateTimeConverter;
import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.w3c.dom.ls.LSInput;

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

	@RequestMapping("/new-certificate")
	public ModelAndView certificati () {

		Map<String, Object> models = new HashMap <String, Object> ();
		
		models.put("fornitoreCombo", fornitoreService.findAllFornitori());
		
		Certificato certificato = new Certificato();
		certificato.setCodice(certificatoService.getNewCodice());
		
		models.put("certificato", certificato);
		
		return new ModelAndView("new-certificate", models);
	}
	
	@RequestMapping("/load-certificate")
	public ModelAndView caricaCertificato (@RequestParam (value="id", required=true) String id) {
		
		Map<String, Object> models = new HashMap <String, Object> ();
		models.put("fornitoreCombo", fornitoreService.findAllFornitori());
		
		Certificato certificato = certificatoService.findById(Integer.parseInt(id));
		
		models.put("certificato", certificato);
		
		return new ModelAndView("new-certificate", models);
	}
	
	
	/**
	 * Aggiunge un nuovo certificato
	 */
	@RequestMapping(value = "/new-certificate", method = RequestMethod.POST)
	public String addMateriale (MultipartHttpServletRequest request, Model model) {

		String risposta;
		int result = -1;

		try {
			Certificato certificato = new Certificato();
			certificato.setData(new java.util.Date());
			certificato.setCodice(request.getParameter("codice"));
			
			// Recupero e assegno il fornitore indicato
			String codiceFornitore = request.getParameter("fornitore");
			Fornitore fornitore = fornitoreService.findFornitoreById(Integer.parseInt(codiceFornitore));
			certificato.setFornitore(fornitore);
			
			// Recupero il file del certificato
			MultipartFile file = request.getFile("formCertificato-fileCertificato");
			certificato.setFileName(file.getOriginalFilename());
			certificato.setFileContent(file.getBytes());
			certificato.setFileLength(file.getSize());
			
			// Creazione lista dei materiali
			ArrayList<Materiale> listaMateriali = new ArrayList<Materiale>();

			//  ... ricerca dei parametri riferiti alle righe dei materiali
			Map<String, Object> formMap = request.getParameterMap();
			final Pattern pattern = Pattern.compile("(formCertificato-rowMateriale)+([0-9]*)(-colata)");
			Matcher matcher;

			// Per ogni materiale, creo un bean
			for (String name : formMap.keySet()) {
				log.debug("parametro [" + name + "]: ");
				matcher = pattern.matcher(name);

				if (matcher.find()) {
					log.debug("trovata colata " + request.getParameter(name) + " - " + matcher.group(2));

					int rigaMateriale = Integer.parseInt(matcher.group(2));

					Materiale materiale = new Materiale();
					materiale.setColata(request.getParameter("formCertificato-rowMateriale" + rigaMateriale	+ "-colata"));
					materiale.setUnitaMisura(request.getParameter("formCertificato-rowMateriale" + rigaMateriale + "-unitaMisura"));
					materiale.setTipoMateriale(request.getParameter("formCertificato-rowMateriale" + rigaMateriale + "-tipoMateriale"));
					materiale.setDimensione(request.getParameter("formCertificato-rowMateriale" + rigaMateriale	+ "-dimensione"));
					materiale.setSpecificaMateriale(request.getParameter("formCertificato-rowMateriale" + rigaMateriale	+ "-specifica"));

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
		
		model.addAttribute("result", result);
		return "list-certificates";
		
	}
	
	@RequestMapping (value = "/list-certificates", method = RequestMethod.GET)
	public ModelAndView cercaCertificati () {

		Map<String, Object> model = new HashMap <String, Object> ();
		
		model.put("fornitoreCombo", fornitoreService.findAllFornitori());
		
		Fornitore fornitore = new Fornitore ();
		
		model.put("fornitore", fornitore);
		
		return new ModelAndView ("list-certificates", model);
		
	}

	@RequestMapping (value = "/list-certificates", method = RequestMethod.POST)
	public @ResponseBody String cercaCertificati (HttpServletRequest request, HttpServletResponse response) {
		log.debug("ricerca certificati");
		
		List <Certificato> listaCertificati = new ArrayList<Certificato>();
		
		String codice = request.getParameter("codice");
		String colata = request.getParameter("colata");
		
		java.util.Date dataFrom = null;
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		try {
			dataFrom = formatter.parse(request.getParameter("dataFrom"));
		} catch (ParseException e) {
			e.printStackTrace();
		}

		java.util.Date dataTo = null;
		try {
			dataTo = formatter.parse(request.getParameter("dataTo"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		int idFornitore = (!"-1".equals(request.getParameter("fornitore"))) ? Integer.parseInt(request.getParameter("fornitore")) : -1;
		
		listaCertificati = certificatoService.findByParameters(codice, dataFrom, dataTo, idFornitore, colata);
		log.debug(listaCertificati.get(0).getData());
		
		DateFormat df = new SimpleDateFormat("dd/mm/yyyy");
		ObjectMapper mapper = new ObjectMapper();
		mapper.setDateFormat(df);		
		try {
			return mapper.writeValueAsString(listaCertificati);
		} catch (Exception e) {
			e.printStackTrace();
			return "errore";
		}
		
	}
	
	@RequestMapping(value = "/download-certificate-file", method = RequestMethod.GET)
	public void downloadCertificato (@RequestParam (value="id", required=true) String id, HttpServletResponse response) {
	
		try {
			Certificato certificato = certificatoService.findById(Integer.parseInt(id));
			InputStream is = new ByteArrayInputStream ( certificato.getFileContent() );
			
			response.setContentType("application/pdf");
			IOUtils.copy(is, response.getOutputStream());
			response.flushBuffer();
		} catch (Exception ex) {
			log.error("Errore: impossibile scaricare il file ...");
			ex.printStackTrace();
		}
	}

}