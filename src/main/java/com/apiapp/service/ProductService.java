package com.apiapp.service;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import com.apiapp.model.Product;

public class ProductService {
	private static SessionFactory sessionFactory = null;

	private static SessionFactory configureSessionFactory() throws HibernateException {
		sessionFactory = new Configuration().configure().buildSessionFactory();

		return sessionFactory;
	}

	public static List<Product> getAllProducts() {
		configureSessionFactory();

		Session session = null;
		try {
			session = sessionFactory.openSession();
			session.beginTransaction();

			List<Product> productsList = session.createQuery("from Product").list();

			return productsList;

		} catch (Exception ex) {
			ex.printStackTrace();

			// Rolling back the changes to make the data consistent in case of any failure
			// in between multiple database write operations.
			session.getTransaction().rollback();
		} finally {
			if (session != null) {
				session.close();
			}
		}

		return new ArrayList<Product>();
	}

	public static Product getProduct(int id) {
		configureSessionFactory();

		Session session = null;
		try {
			session = sessionFactory.openSession();
			session.beginTransaction();

			Query query = session.createQuery("from Product where id = :id");
			query.setParameter("id", id);
			Product product = (Product) query.getSingleResult();
//			product.getChildProducts().size();

			return product;

		} catch (Exception ex) {
			ex.printStackTrace();

			// Rolling back the changes to make the data consistent in case of any failure
			// in between multiple database write operations.
			session.getTransaction().rollback();
		} finally {
			if (session != null) {
				session.close();
			}
		}

		return null;
	}

	public static Product saveOrUpdateProduct(Product product) {
		configureSessionFactory();
		
		Session session = null;
		try {
			session = sessionFactory.openSession();
			session.beginTransaction();

			// Creating Contact entity that will be save to the sqlite database
			session.saveOrUpdate(product);
			session.getTransaction().commit();

			return product;
		} catch (Exception ex) {
			ex.printStackTrace();

			// Rolling back the changes to make the data consistent in case of any failure
			// in between multiple database write operations.
			session.getTransaction().rollback();
		} finally {
			if (session != null) {
				session.close();
			}
		}

		return null;
	}	

	public static void deleteProduct(int id) {
		configureSessionFactory();

		Session session = null;
		try {
			session = sessionFactory.openSession();
			session.beginTransaction();

			Query query = session.createQuery("delete Product where id = :id");
			query.setParameter("id", id);
			query.executeUpdate();
			session.getTransaction().commit();

		} catch (Exception ex) {
			ex.printStackTrace();

			// Rolling back the changes to make the data consistent in case of any failure
			// in between multiple database write operations.
			session.getTransaction().rollback();
		} finally {
			if (session != null) {
				session.close();
			}
		}
	}

	public static Product setParent(int id, int parentId) {
		Product p1 = getProduct(id);
		Product p2 = getProduct(parentId);
		
		if(p1 != null && p2 != null) {
			p1.setParent(p2);
		}
		
		saveOrUpdateProduct(p1);	
		
		return p1;
	}
	
	public static Product setChild(int id, int parentId) {
		Product p1 = getProduct(id);
		Product p2 = getProduct(parentId);
		
		if(p1 != null && p2 != null) {
			p2.setParent(p1);
		}
		
		saveOrUpdateProduct(p2);	
		
		return p1;
	}

}
