/**
 * 
 */
package com.alessandrodonato.elledia.dao;

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
	
	void save (Certificato certificato);
	void update (Certificato certificato);
	void delete (Certificato certificato);
	Certificato findById (int id);
}
