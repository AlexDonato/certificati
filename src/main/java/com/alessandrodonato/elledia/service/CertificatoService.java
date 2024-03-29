/**
 * 
 */
package com.alessandrodonato.elledia.service;

import java.util.Date;
import java.util.ArrayList;

import com.alessandrodonato.elledia.model.Certificato;

/**
 * @author Alessandro Donato
 *
 * 16/feb/2013
 */
public interface CertificatoService {
	short save (Certificato certificato);
	short update (Certificato certificato);
	short delete (Certificato certificato);
	String getNewCodice();
	Certificato findById (int id);
	Certificato findByCodice (String codice);
	ArrayList <Certificato> findByParameters (String codice, Date dataFrom, Date dataTo, int idFornitore, String colata);
}
