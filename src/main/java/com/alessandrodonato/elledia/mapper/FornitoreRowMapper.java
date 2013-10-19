package com.alessandrodonato.elledia.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.alessandrodonato.elledia.model.Fornitore;

public class FornitoreRowMapper implements RowMapper<Fornitore> {

	@Override
	public Fornitore mapRow(ResultSet rs, int index) throws SQLException {
		
		Fornitore fornitore = new Fornitore();
		fornitore.setId(rs.getInt("ID"));
		fornitore.setPiva(rs.getString("PIVA"));
		fornitore.setRagioneSociale(rs.getString("RAGIONE_SOCIALE"));
		fornitore.setTelefono(rs.getString("TELEFONO"));
		fornitore.setCitta(rs.getString("CITTA"));
		fornitore.setCap(rs.getString("CAP"));
		fornitore.setEmail(rs.getString("EMAIL"));
		fornitore.setFax(rs.getString("FAX"));
		fornitore.setIndirizzo(rs.getString("INDIRIZZO"));
		
		return fornitore;
	}
	
}
