package com.alessandrodonato.elledia.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.alessandrodonato.elledia.model.Fornitore;
import com.alessandrodonato.elledia.model.Materiale;

public class MaterialeRowMapper implements RowMapper<Materiale> {

	@Override
	public Materiale mapRow(ResultSet rs, int index) throws SQLException {
		
		Materiale materiale = new Materiale();
		materiale.setId(rs.getInt("ID"));
		materiale.setColata(rs.getString("colata"));
		materiale.setDimensione(rs.getString("dimensione"));
		materiale.setSpecificaMateriale(rs.getString("specifica"));
		materiale.setUnitaMisura(rs.getString("unita_misura"));
		materiale.setTipoMateriale(rs.getString("tipo_materiale"));
		return materiale;
	}
	
}
