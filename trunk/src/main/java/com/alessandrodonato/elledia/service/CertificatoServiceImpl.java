/**
 * 
 */
package com.alessandrodonato.elledia.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alessandrodonato.elledia.dao.CertificatoDao;
import com.alessandrodonato.elledia.model.Certificato;

/**
 * @author Alessandro Donato
 *
 * 16/feb/2013
 */

@Service ("certificatoService")
public class CertificatoServiceImpl implements CertificatoService {

	private static final Logger log = Logger.getLogger(CertificatoServiceImpl.class);
	
	@Autowired
	CertificatoDao certificatoDao;
	
	public CertificatoDao getCertificatoDao() {
		return certificatoDao;
	}

	public void setCertificatoDao(CertificatoDao certificatoDao) {
		this.certificatoDao = certificatoDao;
	}

	@Override
	public void save(Certificato certificato) {
		log.info("Salvataggio certificato " + certificato);
	}

	@Override
	public void update(Certificato certificato) {
		log.info("Aggiornamento certificato " + certificato);
	}

	@Override
	public void delete(Certificato certificato) {
		log.info("Cancellazione certificato " + certificato);
	}

	@Override
	public Certificato findById(int id) {
		log.info("Ricerca certificato per id " + id);
		return null;
	}

}
