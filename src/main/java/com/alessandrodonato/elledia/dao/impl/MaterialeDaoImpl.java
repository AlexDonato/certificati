/**
 * 
 */
package com.alessandrodonato.elledia.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
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
import com.alessandrodonato.elledia.dao.MaterialeDao;
import com.alessandrodonato.elledia.mapper.MaterialeRowMapper;
import com.alessandrodonato.elledia.model.Fornitore;
import com.alessandrodonato.elledia.model.Materiale;

/**
 * @author Alessandro Donato
 *
 * 18/feb/2013
 */

@Service ("materialeDao")
public class MaterialeDaoImpl implements MaterialeDao {

	private static final Logger log = Logger.getLogger(MaterialeDaoImpl.class);
	
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	public void setDataSource (DataSource dataSource) {
		this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
	}
	

	@Override
	public Materiale findMaterialeById(int id) {
		log.debug("init findMaterialeById [" + id + "]");
		
		String query = "SELECT * FROM materiale WHERE id = :id";
		
		SqlParameterSource namedParameters = new MapSqlParameterSource("id", Integer.toString(id));
		
		Materiale materiale = namedParameterJdbcTemplate.queryForObject(query, namedParameters, new MaterialeRowMapper());
		
		log.debug("end findMaterialeById [" + materiale + "]");

		return materiale;
	}


	@Override
	public ArrayList<Materiale> findMaterialeByColata(String colata) {
		log.debug("init findMaterialeByColata [" + colata + "]");
		
		String query = "SELECT * FROM materiale WHERE colata = :colata";
		
		SqlParameterSource namedParameters = new MapSqlParameterSource("colata", colata);
		
		List <Materiale> listaMateriali = namedParameterJdbcTemplate.query(query, namedParameters, new MaterialeRowMapper());
		
		log.debug("end findMaterialeByColata [" + colata + "]");

		return new ArrayList<Materiale> (listaMateriali);
	}


	@Override
	public ArrayList<Materiale> findAllMateriali() {
		log.debug("init findAllMateriali");
		
		String query = "SELECT * FROM materiale where 1 = :fake order by id";
		
		SqlParameterSource namedParameters = new MapSqlParameterSource("fake", "1");
		
		List <Materiale> listaMateriali = namedParameterJdbcTemplate.query(query, namedParameters, new MaterialeRowMapper());
		
		log.debug("end findAllMateriali");

		return new ArrayList<Materiale> (listaMateriali);
	}


	@Override
	public ArrayList <Materiale> findMaterialiByCertificatoId(int id) {
		log.debug("init findMaterialiByCertificatoId [" + id + "]");
		
		String query = "SELECT * FROM materiale WHERE certificato_id = :id";
		
		SqlParameterSource namedParameters = new MapSqlParameterSource("id", Integer.toString(id));
		log.debug(namedParameterJdbcTemplate);
		
		List <Materiale> listaMateriali = namedParameterJdbcTemplate.query(query, namedParameters, new MaterialeRowMapper());
		
		log.debug("end findMaterialiByCertificatoId [" + id + "]");

		return new ArrayList<Materiale> (listaMateriali);
	}
	
}
