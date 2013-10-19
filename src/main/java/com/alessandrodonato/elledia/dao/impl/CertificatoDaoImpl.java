/**
 * 
 */
package com.alessandrodonato.elledia.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

import com.alessandrodonato.elledia.dao.CertificatoDao;
import com.alessandrodonato.elledia.helper.OperationMessageCodes;
import com.alessandrodonato.elledia.mapper.CertificatoRowMapper;
import com.alessandrodonato.elledia.model.Certificato;
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
	
	private static final String INSERT_CERTIFICATO_QUERY = "INSERT INTO certificato (data_originale, codice, id_fornitore, " +
			"filecontent, filename, filesize) " +
			" VALUES (:data_originale, :codice, :id_fornitore, :filecontent, :filename, :filesize)";

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
		pars.addValue("filecontent", certificato.getFileContent());
		pars.addValue("filename", certificato.getFileName());
		pars.addValue("filesize", Long.valueOf(certificato.getFileLength()));
		
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
		log.debug("ricerca certificato per id " + id);
		
		final String query = "select * from certificato where id = :id";
				
		Certificato certificato = namedParameterJdbcTemplate.queryForObject (
				query, new MapSqlParameterSource("id", id), new CertificatoRowMapper());
		
		log.debug("certificato trovato = [" + certificato + "]");

		return certificato;
	}
	
	public Certificato findByCodice (String codice) {
		log.debug("ricerca certificato per codice " + codice);
		
		final String query = "select * from certificato where codice = :codice";
				
		Certificato certificato = namedParameterJdbcTemplate.queryForObject (query, 
					new MapSqlParameterSource("codice", codice), new CertificatoRowMapper());
		
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
	
	@Override
	public ArrayList <Certificato> findByParameters (String codice, Date dataFrom, Date dataTo, int idFornitore, String idColata) {
		log.debug("ricerca certificato per parametri ... codice[" + codice + "] - dataFrom [" + dataFrom 
								+ "] - dataTo[" + dataTo + "] - fornitore[" + idFornitore + "] - colata [" + idColata + "]");
		
		List <Certificato> lista = new ArrayList<Certificato> ();
		
		String query = "SELECT distinct certificato.*, fornitore.ragione_sociale FROM certificato, materiale, fornitore " +
			"where materiale.certificato_id = certificato.id and fornitore.id = certificato.id_fornitore ";
		String and = "";
		
		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		
		if (codice != null && !"".equals(codice)) {
			and = " and ";
			query += and + "codice = :codice";
			paramSource.addValue("codice", codice);
		}
		if (dataFrom != null) {
			and = " and ";
			query += and + "data_originale >= :dataFrom";
			paramSource.addValue("dataFrom", dataFrom);
		}
		if (dataTo != null) {
			and = " and ";
			query += and + "data_originale <= :dataTo";
			paramSource.addValue("dataTo", dataTo);
		}
		if (idFornitore != -1) {
			and = " and ";
			query += and + "id_fornitore = :idFornitore";
			paramSource.addValue("idFornitore", new Integer(idFornitore));
		}
		if (idColata != null && !"".equals(idColata)) {
			and = " and ";
			query += and + " materiale.colata = :idColata";
			paramSource.addValue("idColata", idColata);
		}
		
		query += " order by certificato.id asc";
		
		log.debug("query = " + query);

		lista = namedParameterJdbcTemplate.query(query, paramSource, new CertificatoRowMapper());
		
		return new ArrayList<Certificato>(lista);
	}
	
}
