/**
 * 
 */
package com.alessandrodonato.webapp.dao;

import static org.junit.Assert.*;

import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;
import org.appfuse.dao.GenericDao;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.alessandrodonato.webapp.model.Fornitore;

/**
 * @author Alessandro Donato
 *
 * 10/giu/2013
 */
public class FornitoreDaoTest extends BaseDaoTestCase {

	@Autowired
	private FornitoreDao fornitoreDao;
	
	@Test
	public void testNotNull() {
		List<Fornitore> fornitoreList = fornitoreDao.getAll();
		assertNotNull(fornitoreList);
	}

	@Test
	public void testMoreThenOne() {
		List<Fornitore> fornitoreList = fornitoreDao.getAll();
		assertTrue(fornitoreList.size() > 0);
	}
	
	@Test
	public void testAddAndRemove () throws Exception {
		Fornitore f = new Fornitore();
		
		f.setRagioneSociale("Ragione Sociale Test");
		f.setIndirizzo("via le mani dalla pizza, 12");
		f.setCitta("Fino Mornasco");
		
		log.debug("Aggiunta fornitore test");
		f = fornitoreDao.save(f);
		flush();
		
		assertEquals("Ragione Sociale Test", f.getRagioneSociale());
		assertNotNull(f.getId());
		
		log.debug("rimozione del fornitore di test.");
		fornitoreDao.remove(f.getId());
		flush();
		
		// deve generare un'eccezione
		fornitoreDao.getFornitoreById(f.getId());
	}
}
