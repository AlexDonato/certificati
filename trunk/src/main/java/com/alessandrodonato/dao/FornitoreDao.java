/**
 * 
 */
package com.alessandrodonato.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.alessandrodonato.webapp.model.Fornitore;

/**
 * @author Alessandro Donato
 *
 * 18/giu/2013
 */
public interface FornitoreDao extends GenericDao <Fornitore, Long> {
	public Fornitore getFornitoreById (Long id);
	public List<Fornitore> getFornitoreByName (String name);
}
