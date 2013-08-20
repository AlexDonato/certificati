/**
 * 
 */
package com.alessandrodonato.elledia.service;

import java.util.ArrayList;

import com.alessandrodonato.elledia.model.Fornitore;

/**
 * @author Alessandro Donato
 *
 * 18/feb/2013
 */
public interface FornitoreService {
	
	public short save (Fornitore fornitore);
	public void update (Fornitore fornitore);
	public void delete (Fornitore fornitore);
	public Fornitore findFornitoreById (int id);
	public ArrayList <Fornitore> findFornitoriByName (String nome);
	public ArrayList <Fornitore> findAllFornitori ();

}
