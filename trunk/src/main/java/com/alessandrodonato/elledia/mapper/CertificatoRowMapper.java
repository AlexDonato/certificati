package com.alessandrodonato.elledia.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.alessandrodonato.elledia.model.Certificato;

public class CertificatoRowMapper implements RowMapper<Certificato> {

	@Override
	public Certificato mapRow(ResultSet rs, int id) throws SQLException {
		Certificato certificato = new Certificato ();
		certificato.setId(rs.getInt("id"));
		certificato.setCodice(rs.getString("codice"));
		certificato.setData(rs.getDate("data_originale"));
		certificato.setFileContent(rs.getBytes("filecontent"));
		certificato.setFileLength(rs.getLong("filesize"));
		certificato.setFileName(rs.getString("filename"));
		certificato.setIdFornitore(rs.getInt("id_fornitore"));
		return certificato;
	}

}
