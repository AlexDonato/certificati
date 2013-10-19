/**
 * 
 */
package com.alessandrodonato.elledia.service;

import java.util.Date;
import java.util.ArrayList;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alessandrodonato.elledia.dao.CertificatoDao;
import com.alessandrodonato.elledia.dao.FornitoreDao;
import com.alessandrodonato.elledia.dao.MaterialeDao;
import com.alessandrodonato.elledia.model.Certificato;
import com.alessandrodonato.elledia.model.Fornitore;
import com.alessandrodonato.elledia.model.Materiale;

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
	
	@Resource (name = "fornitoreDao")
	FornitoreDao fornitoreDao;

	@Resource (name = "materialeDao")
	MaterialeDao materialeDao;
	
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
		Certificato certificato = certificatoDao.findById(id);
		certificato = loadCertificatoObjects(certificato);
		return certificato;
	}
	
	@Override
	public String getNewCodice () {
		return certificatoDao.getNewCodice ();
	}

	@Override
	public Certificato findByCodice(String codice) {
		return certificatoDao.findByCodice(codice);
	}
	
	@Override
	public ArrayList <Certificato> findByParameters (String codice, Date dataFrom, Date dataTo, int idFornitore, String colata) {
		ArrayList <Certificato> listaCertificati = new ArrayList <Certificato>();
		
		listaCertificati = certificatoDao.findByParameters(codice, dataFrom, dataTo, idFornitore, colata);
		
		int _numero = (listaCertificati != null ) ? listaCertificati.size() : 0;
		log.debug("trovati n." + _numero + " certificati.");
		
		if (_numero > 0) {
			log.debug("associazione fornitori e materiali");
			
			for (int i = 0 ; i < listaCertificati.size(); i++) {

				final Certificato certificato = loadCertificatoObjects (listaCertificati.get(i)) ;
				
				listaCertificati.set(i, certificato);
				
				log.debug("certificato " + certificato.getId() + " associati a n." + certificato.getMateriali().size() + " materiali.");
			}
		}		
		
		return listaCertificati;
	}
	
	private Certificato loadCertificatoObjects (Certificato certificato) {

		final Fornitore fornitore = fornitoreDao.findFornitoreById(certificato.getIdFornitore()); 
		certificato.setFornitore(fornitore);
		
		log.debug("certificato " + certificato.getId() + " associato fornitore " + fornitore.getRagioneSociale());
		
		final ArrayList<Materiale> listaMateriali = materialeDao.findMaterialiByCertificatoId(certificato.getId());
		certificato.setMateriali(listaMateriali);
	
		return certificato;
	}
}
