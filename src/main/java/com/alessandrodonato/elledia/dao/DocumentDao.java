/**
 * 
 */
package com.alessandrodonato.elledia.dao;

import java.util.List;

import com.alessandrodonato.elledia.model.Documento;

/**
 * @author Alessandro Donato
 *
 * 16/ago/2013
 */
public interface DocumentDao {
	short save (Documento documento);
	short update (Documento documento);
	short delete (int id);
	
	Documento findById (int id);
	Documento findByCodice (String codice);
	List <Documento> findAll ();
}
