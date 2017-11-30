package com.apiapp.service;

import javax.persistence.Query;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class SessionBuilder {
	public static SessionFactory session() throws HibernateException {
		return new Configuration().configure().buildSessionFactory();
	}

	public static void truncate(String myTable) {
		Session session = null;
		try {
			session = session().openSession();
			session.beginTransaction();
			String hql = String.format("DELETE FROM %s", myTable);			
			Query query = session.createQuery(hql);
			query.executeUpdate();				
		} catch (Exception ex) {
			ex.printStackTrace();
			session.getTransaction().rollback();
		} finally {
			if (session != null) {
				session.close();
			}
		}
	}
}
