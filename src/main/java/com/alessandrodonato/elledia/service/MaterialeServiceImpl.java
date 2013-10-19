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
import com.alessandrodonato.elledia.dao.MaterialeDao;
import com.alessandrodonato.elledia.model.Fornitore;
import com.alessandrodonato.elledia.model.Materiale;

/**
 * @author Alessandro Donato
 *
 * 18/feb/2013
 */

@Service ("materialeService")
public class MaterialeServiceImpl implements MaterialeService {
	
	private final static Logger log = Logger.getLogger(MaterialeServiceImpl.class);

	@Resource (name = "materialeDao")
	MaterialeDao materialeDao;

	@Override
	public Materiale findMaterialeById(int id) {
		return materialeDao.findMaterialeById(id);
	}

	@Override
	public ArrayList<Materiale> findMaterialiByColata(String colata) {
		return materialeDao.findMaterialeByColata(colata);
	}

	@Override
	public ArrayList<Materiale> findAllMateriali() {
		return materialeDao.findAllMateriali();
	}

}
