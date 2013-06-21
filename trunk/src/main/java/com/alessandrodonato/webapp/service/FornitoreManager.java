/**
 * 
 */
package com.alessandrodonato.webapp.service;

import java.util.List;

import org.appfuse.service.GenericManager;

import com.alessandrodonato.webapp.model.Fornitore;

/**
 * @author Alessandro Donato
 *
 * 09/giu/2013
 */
public interface FornitoreManager extends GenericManager<Fornitore, Long> {
	Fornitore getFornitoreById (Long id);
	List<Fornitore> getFornitoreByName (String name);
	List<Fornitore> getAllFornitore ();
}
