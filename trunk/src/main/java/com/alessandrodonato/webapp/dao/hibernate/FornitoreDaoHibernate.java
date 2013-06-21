/**
 * 
 */
package com.alessandrodonato.webapp.dao.hibernate;

import java.util.List;

import org.appfuse.dao.GenericDao;
import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.alessandrodonato.webapp.dao.FornitoreDao;
import com.alessandrodonato.webapp.model.Fornitore;

/**
 * @author Alessandro Donato
 *
 * 18/giu/2013
 */
@Repository ("fornitoreDao")
public class FornitoreDaoHibernate extends GenericDaoHibernate<Fornitore, Long>
		implements FornitoreDao {

	@Autowired
  SessionFactory sessionFactory;
	
	/**
	 * @param persistentClass
	 */
	public FornitoreDaoHibernate() {
		super(Fornitore.class);
		super.setSessionFactory(sessionFactory);
	}

	@Override
	public Fornitore getFornitoreById(Long id) {
		System.out.println(" -------------------> " + getSession());
		//Session session = getSessionFactory().openSession();
		Fornitore f = (Fornitore) getSession().createCriteria(Fornitore.class).list().get(0);
		//session.close();
		return f;
	}

	@Override
	public List<Fornitore> getFornitoreByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

}
