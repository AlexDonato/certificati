/**
 * 
 */
package com.alessandrodonato.elledia.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.alessandrodonato.elledia.dao.FornitoreDao;
import com.alessandrodonato.elledia.mapper.FornitoreRowMapper;
import com.alessandrodonato.elledia.model.Fornitore;

/**
 * @author Alessandro Donato
 *
 * 18/feb/2013
 */

public class FornitoreDaoImpl implements FornitoreDao {

	private static final Logger log = Logger.getLogger(FornitoreDaoImpl.class);
	
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	public void setDataSource (DataSource dataSource) {
		this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public short save (Fornitore fornitore) {
		log.debug("init save [" + fornitore.getRagioneSociale() + "]");
		
		short result = -1;
		
		// Prima verifico se esiste gia' un fornitore con il nome passato
		Fornitore f = findFornitoreByName(fornitore.getRagioneSociale());
		if (f != null) {
			log.debug("\tfornitore esistente " + f);
			return MSG_DUPLICATO;
		}
		
		String query = "INSERT INTO fornitore (ragione_sociale, indirizzo, cap, citta, country, note, email, telefono, fax, piva ) " +
				" VALUES (:ragione_sociale, :indirizzo, :cap, :citta, :country, :note, :email, :telefono, :fax, :piva)";
		
		Map namedParameters = new HashMap();
		namedParameters.put("ragione_sociale", StringUtils.upperCase(fornitore.getRagioneSociale()));
		namedParameters.put("indirizzo", StringUtils.upperCase(fornitore.getIndirizzo()));
		namedParameters.put("cap", fornitore.getCap());
		namedParameters.put("citta", StringUtils.upperCase(fornitore.getCitta()));
		namedParameters.put("country", StringUtils.upperCase(fornitore.getCountry()));
		namedParameters.put("note", fornitore.getNote());
		namedParameters.put("email", fornitore.getEmail());
		namedParameters.put("telefono", fornitore.getTelefono());
		namedParameters.put("fax", fornitore.getFax());
		namedParameters.put("piva", StringUtils.upperCase(fornitore.getPiva()));
		
		int num = namedParameterJdbcTemplate.update(query, namedParameters);
		
		log.debug("end save [" + fornitore.getRagioneSociale() + "]");
		
		if (num == 1)
			return FornitoreDao.MSG_SALVATO;
		else
			return FornitoreDao.MSG_ERRORE;
		
	}

	@Override
	public void update(Fornitore fornitore) {
		// TODO Auto-generated method stub
		log.debug("FAKE update [" + fornitore.getRagioneSociale() + "]");
	}

	@Override
	public void delete(Fornitore fornitore) {
		// TODO Auto-generated method stub
		log.debug("FAKE delete [" + fornitore.getRagioneSociale() + "]");
	}

	@Override
	public Fornitore findFornitoreById(int id) {
		log.debug("named jdbc " + namedParameterJdbcTemplate);
		log.debug("init findFornitoreById [" + id + "]");
		
		String query = "SELECT * FROM fornitore WHERE id = :id";
		
		SqlParameterSource namedParameters = new MapSqlParameterSource("id", Integer.toString(id));
		
		Fornitore fornitore = namedParameterJdbcTemplate.queryForObject(query, namedParameters, new FornitoreRowMapper());
		
		log.debug("end findFornitoreById [" + fornitore + "]");

		return fornitore;
	}

	@Override
	public ArrayList<Fornitore> findFornitori() {
		log.debug("FAKE findFornitori [tutti i fornitori]");
		return null;
	}

	@Override
	public Fornitore findFornitoreByName(String ragioneSociale) {
		// TODO Auto-generated method stub
		
		log.debug("init findFornitoreByName [" + ragioneSociale + "]");
		
		String query = "SELECT * FROM fornitore WHERE LOWER(ragione_sociale) = LOWER(:ragione_sociale)";
		
		Map<String, String> namedParameters = new HashMap<String, String>();
		namedParameters.put("ragione_sociale", ragioneSociale);		
		Fornitore fornitore = (Fornitore) namedParameterJdbcTemplate.query (query, namedParameters, new FornitoreRowMapper());
		
		log.debug("end findFornitoreByName [" + fornitore + "] trovato.");
		
		return fornitore;
	}

	@Override
	public ArrayList<Fornitore> findFornitori (String nome) {
		
		log.debug("named jdbc " + namedParameterJdbcTemplate);
		log.debug("init findFornitori [" + nome + "]");
		
		String query = "SELECT * FROM fornitore WHERE LOWER(ragione_sociale) like LOWER(:ragione_sociale) ORDER BY ragione_sociale";
		
		Map<String, String> namedParameters = new HashMap<String, String>();
		namedParameters.put("ragione_sociale", "%" + nome + "%");		
		ArrayList<Fornitore> listaFornitori = 
				(ArrayList<Fornitore>) namedParameterJdbcTemplate.query (query, namedParameters, new FornitoreRowMapper());
		
		log.debug("end findFornitori [" + nome + "] trovati " + listaFornitori.size());
		
		return listaFornitori;
	}
	
}
