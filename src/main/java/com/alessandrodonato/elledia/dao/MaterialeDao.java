/**
 * 
 */
package com.alessandrodonato.elledia.dao;

import java.util.ArrayList;

import com.alessandrodonato.elledia.model.Materiale;

/**
 * @author Alessandro Donato
 *
 * 18/feb/2013
 */
public interface MaterialeDao {

	public Materiale findMaterialeById (int id);
	public ArrayList <Materiale> findMaterialiByCertificatoId (int id);
	public ArrayList <Materiale> findMaterialeByColata (String colata);
	public ArrayList <Materiale> findAllMateriali ();
}
