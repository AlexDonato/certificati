/**
 * 
 */
package com.alessandrodonato.elledia.service;

import com.alessandrodonato.elledia.model.Certificato;

/**
 * @author Alessandro Donato
 *
 * 16/feb/2013
 */
public interface CertificatoService {
	void save (Certificato certificato);
	void update (Certificato certificato);
	void delete (Certificato certificato);
	Certificato findById (int id);
}
