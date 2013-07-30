/**
 * 
 */
package com.alessandrodonato.elledia.dao.impl;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import com.alessandrodonato.elledia.dao.CertificatoDao;
import com.alessandrodonato.elledia.model.Certificato;

/**
 * @author Alessandro Donato
 *
 * 06/feb/2013
 */

public class CertificatoDaoImpl extends NamedParameterJdbcDaoSupport implements CertificatoDao {

	private static final Logger log = Logger.getLogger(CertificatoDaoImpl.class);
	
	private NamedParameterJdbcTemplate jdbcTemplate = this.getNamedParameterJdbcTemplate();

	public void save(Certificato certificato) {
		final String saveSql = "INSERT INTO certificat";
		log.info("salvataggio " + certificato);
	}

	public void update(Certificato certificato) {
		log.info("update " + certificato);
	}

	public void delete(Certificato certificato) {
		log.info("delete " + certificato);
	}

	public Certificato findById(int id) {
		log.info("ricerca per id " + id);
		return null;
	}

}
