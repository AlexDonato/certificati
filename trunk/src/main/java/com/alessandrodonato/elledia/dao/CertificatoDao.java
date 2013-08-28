/**
 * 
 */
package com.alessandrodonato.elledia.dao;

import javax.sql.DataSource;

import com.alessandrodonato.elledia.model.Certificato;

/**
 * @author Alessandro Donato
 *
 * 06/feb/2013
 */
public interface CertificatoDao {

	public static short MSG_SALVATO = 0;
	public static short MSG_DUPLICATO = 1;
	public static short MSG_ERRORE = 2;

	public void setDataSource (DataSource dataSource);
	
	short save (Certificato certificato);
	short update (Certificato certificato);
	short delete (Certificato certificato);
	Certificato findById (int id);
	Certificato findByCodice (String codice);
	String getNewCodice ();
}
