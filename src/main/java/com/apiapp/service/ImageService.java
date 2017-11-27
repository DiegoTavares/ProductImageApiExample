package com.apiapp.service;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import com.apiapp.model.Image;
import com.apiapp.model.Product;

public class ImageService {
	private static SessionFactory sessionFactory = null;

	private static SessionFactory configureSessionFactory() throws HibernateException {
		sessionFactory = new Configuration().configure().buildSessionFactory();

		return sessionFactory;
	}

	public static List<Image> getAllImages() {
		configureSessionFactory();

		Session session = null;
		try {
			session = sessionFactory.openSession();
			session.beginTransaction();

			List<Image> imagesList = session.createQuery("from Image").list();

			return imagesList;

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

		return new ArrayList<Image>();
	}

	public static Image getImage(int id) {
		configureSessionFactory();

		Session session = null;
		try {
			session = sessionFactory.openSession();
			session.beginTransaction();

			Query query = session.createQuery("from Image where id = :id");
			query.setParameter("id", id);
			Image image = (Image) query.getSingleResult();
//			image.getChildImages().size();

			return image;

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

	public static Image saveOrUpdateImage(Image image) {
		configureSessionFactory();
		
		Session session = null;
		try {
			session = sessionFactory.openSession();
			session.beginTransaction();
			int id = image.getProduct().getId();
			Product product = ProductService.getProduct(id);
			image.setProduct(product);

			// Creating Contact entity that will be save to the sqlite database
			session.saveOrUpdate(image);
			session.getTransaction().commit();

			return image;
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

	public static void deleteImage(int id) {
		configureSessionFactory();

		Session session = null;
		try {
			session = sessionFactory.openSession();
			session.beginTransaction();

			Query query = session.createQuery("delete Image where id = :id");
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
	
	public static Image setProduct(int imageId, int productId) {
		Product product = ProductService.getProduct(productId);
		Image image = getImage(imageId);
		
		if(product != null && image != null) {
			image.setProduct(product);
		}
		
		saveOrUpdateImage(image);	
		
		return image;
	}
}
