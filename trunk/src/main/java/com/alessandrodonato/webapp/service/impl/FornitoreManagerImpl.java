/**
 * 
 */
package com.alessandrodonato.webapp.service.impl;

import java.util.List;

import org.appfuse.dao.GenericDao;
import org.appfuse.service.impl.GenericManagerImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alessandrodonato.webapp.dao.FornitoreDao;
import com.alessandrodonato.webapp.model.Fornitore;
import com.alessandrodonato.webapp.service.FornitoreManager;

/**
 * @author Alessandro Donato
 *
 * 09/giu/2013
 */
@Service("fornitoreManager")
public class FornitoreManagerImpl extends GenericManagerImpl<Fornitore, Long> implements FornitoreManager {

	FornitoreDao fornitoreDao;
	
	@Autowired
	public FornitoreManagerImpl (FornitoreDao fornitoreDao) {
		super (fornitoreDao);
		this.fornitoreDao = fornitoreDao;
	}

	@Override
	public Fornitore getFornitoreById(Long id) {
		
		return fornitoreDao.getFornitoreById(id);
	}

	@Override
	public List<Fornitore> getFornitoreByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}



	/* (non-Javadoc)
	 * @see com.alessandrodonato.webapp.service.FornitoreManager#getAllFornitore()
	 */
	@Override
	public List<Fornitore> getAllFornitore() {
		// TODO Auto-generated method stub
		return null;
	}

}
