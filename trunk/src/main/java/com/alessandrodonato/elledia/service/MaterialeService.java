/**
 * 
 */
package com.alessandrodonato.elledia.service;

import java.util.ArrayList;
import java.util.SortedMap;

import com.alessandrodonato.elledia.model.Fornitore;
import com.alessandrodonato.elledia.model.Materiale;

/**
 * @author Alessandro Donato
 *
 * 18/feb/2013
 */
public interface MaterialeService {
	
	public Materiale findMaterialeById (int id);
	public ArrayList <Materiale> findMaterialiByColata (String colata);
	public ArrayList <Materiale> findAllMateriali ();

}
