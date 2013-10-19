/**
 * 
 */
package com.alessandrodonato.elledia.service;

import java.util.ArrayList;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alessandrodonato.elledia.dao.FornitoreDao;
import com.alessandrodonato.elledia.model.Fornitore;

/**
 * @author Alessandro Donato
 *
 * 18/feb/2013
 */

@Service ("fornitoreService")
public class FornitoreServiceImpl implements FornitoreService {
	
	private final static Logger log = Logger.getLogger(FornitoreServiceImpl.class);

	@Resource (name = "fornitoreDao")
	FornitoreDao fornitoreDao;

	public FornitoreDao getFornitoreDao() {
		return fornitoreDao;
	}

	public void setFornitoreDao(FornitoreDao fornitoreDao) {
		this.fornitoreDao = fornitoreDao;
	}
	
	@Override
	public short save(Fornitore fornitore) {
		// TODO Auto-generated method stub
		return fornitoreDao.save(fornitore);
	}

	public void update(Fornitore fornitore) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(Fornitore fornitore) {
		// TODO Auto-generated method stub

	}

	@Override
	public Fornitore findFornitoreById(int id) {
		return fornitoreDao.findFornitoreById(id);
	}

	@Override
	public ArrayList<Fornitore> findFornitoriByName(String nome) {
		log.debug("Ricerca per " + nome + " ...");
		return fornitoreDao.findFornitori(nome);
	}

	@Override
	public ArrayList<Fornitore> findAllFornitori() {
		log.debug("Ricerca ...");
		return fornitoreDao.findFornitori("");
	}
	
}
