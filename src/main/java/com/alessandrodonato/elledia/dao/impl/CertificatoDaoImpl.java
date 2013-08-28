/**
 * 
 */
package com.alessandrodonato.elledia.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Service;

import com.alessandrodonato.elledia.dao.CertificatoDao;
import com.alessandrodonato.elledia.helper.OperationMessageCodes;
import com.alessandrodonato.elledia.model.Certificato;
import com.alessandrodonato.elledia.model.Fornitore;
import com.alessandrodonato.elledia.model.Materiale;

/**
 * @author Alessandro Donato
 *
 * 06/feb/2013
 */

@Service ("certificatoDao")
public class CertificatoDaoImpl implements CertificatoDao {

	private static final Logger log = Logger.getLogger(CertificatoDaoImpl.class);
	
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	private static final String INSERT_CERTIFICATO_QUERY = "INSERT INTO certificato (data_originale, codice, id_fornitore ) " +
			" VALUES (:data_originale, :codice, :id_fornitore)";

	private static final String INSERT_MATERIALE_QUERY = "INSERT INTO materiale (colata, dimensione, specifica, " + 
			"tipo_materiale, id_tipo_dimensione, certificato_id) " +
			" VALUES (:colata, :dimensione, :specifica, :tipo_materiale, :id_tipo_dimensione, :certificato_id)";
	
	public void setDataSource (DataSource dataSource) {
		this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public short save (Certificato certificato) {
		
		log.debug("inizio salvataggio certificato " + certificato.getCodice());
		
		short retValue = OperationMessageCodes.MSG_SALVATO;
		
		MapSqlParameterSource pars = new MapSqlParameterSource();
		pars.addValue ("data_originale", certificato.getData());
		pars.addValue ("codice", certificato.getCodice());
		pars.addValue ("id_fornitore", String.valueOf(certificato.getFornitore().getId()));
		
		int num = namedParameterJdbcTemplate.update(INSERT_CERTIFICATO_QUERY, pars);
		
		if (num == 1) {
			log.debug("certificato " + certificato.getCodice() + " salvato correttamente. inizio salvataggio materiali");
			
			// Recupero l'id del certificato appena salvato
			int codiceCertificato = findByCodice(certificato.getCodice()).getId();
			
			for (Materiale materiale : certificato.getMateriali()) {
				pars = new MapSqlParameterSource();
				
				//:colata, , :specifica, :tipo_materiale, :id_tipo_dimensione, :certificato_id
				pars.addValue ("colata", materiale.getColata());
				pars.addValue ("dimensione", materiale.getDimensione());
				pars.addValue ("specifica", materiale.getSpecificaMateriale());
				pars.addValue ("tipo_materiale", materiale.getSpecificaMateriale());
				pars.addValue ("id_tipo_dimensione", materiale.getUnitaMisura());
				pars.addValue ("certificato_id", codiceCertificato);
				
				num = namedParameterJdbcTemplate.update(INSERT_MATERIALE_QUERY, pars);
				
				if (num == 1) {
					log.debug("materiale " + materiale.toString() + " salvato correttamente.");
				} else {
					log.error("problema durante il salvataggio del materiale " + materiale.toString());
					retValue = OperationMessageCodes.MSG_ERRORE;
					return retValue;
				}
			}
		} else {
			log.error("problema durante il salvataggio del certificato " + certificato.getCodice());
			retValue = OperationMessageCodes.MSG_ERRORE;
		}
		
		log.debug("fine salvataggio certificato " + certificato.getCodice());
		return retValue;
	}

	public short update(Certificato certificato) {
		log.info("update " + certificato);
		return -1;
	}

	public short delete(Certificato certificato) {
		log.info("delete " + certificato);
		return -1;
	}

	public Certificato findById(int id) {
		log.info("ricerca per id " + id);
		return null;
	}
	
	public Certificato findByCodice (String codice) {
		log.debug("ricerca certificato per codice " + codice);
		
		final String query = "select * from certificato where codice = :codice";
				
		Certificato certificato = namedParameterJdbcTemplate.queryForObject (query, new MapSqlParameterSource("codice", codice), new RowMapper<Certificato>() {

			@Override
			public Certificato mapRow(ResultSet rs, int id) throws SQLException {
				Certificato certificato = new Certificato ();
				certificato.setId(rs.getInt("id"));
				certificato.setCodice(rs.getString("codice"));
				certificato.setData(rs.getDate("data_originale"));
				log.warn("manca il set delle join con materiali e fornitore!!!");
				return certificato;
			}
		});
		
		log.debug("certificato trovato = [" + certificato + "]");

		return certificato;

	}	

	@Override
	public String getNewCodice () {
		
		log.debug("generazione nuovo codice per certificato.");
		
		final String query = "select concat_ws('-',max(id)+1, YEAR(CURDATE())) from certificato where YEAR(data_originale) = YEAR(CURDATE());";
				
		String codice = namedParameterJdbcTemplate.queryForObject (query, new MapSqlParameterSource(), String.class);
		
		log.debug("codice generato = [" + codice + "]");

		return codice;
		
	}
	
}
