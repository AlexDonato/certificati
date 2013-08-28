/**
 * 
 */
package com.alessandrodonato.elledia.service;

import javax.annotation.Resource;

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
	
	@Resource (name = "certificatoDao")
	CertificatoDao certificatoDao;
	
	public CertificatoDao getCertificatoDao() {
		return certificatoDao;
	}

	public void setCertificatoDao(CertificatoDao certificatoDao) {
		this.certificatoDao = certificatoDao;
	}

	@Override
	public short save(Certificato certificato) {
		log.info("Salvataggio certificato " + certificato);
		return certificatoDao.save(certificato);
	}

	@Override
	public short update(Certificato certificato) {
		log.info("Aggiornamento certificato " + certificato);
		return -1;
	}

	@Override
	public short delete(Certificato certificato) {
		log.info("Cancellazione certificato " + certificato);
		return -1;
	}

	@Override
	public Certificato findById(int id) {
		log.info("Ricerca certificato per id " + id);
		return null;
	}
	
	@Override
	public String getNewCodice () {
		return certificatoDao.getNewCodice ();
	}

	@Override
	public Certificato findByCodice(String codice) {
		return certificatoDao.findByCodice(codice);
	}

}
