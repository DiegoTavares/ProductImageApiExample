package com.apiapp.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import com.apiapp.model.Image;
import com.apiapp.model.Product;
import com.apiapp.model.ProductWithReferencesDecorator;

public class ProductService {
	private static SessionFactory sessionFactory = null;

	private static SessionFactory configureSessionFactory() throws HibernateException {
		sessionFactory = new Configuration().configure().buildSessionFactory();

		return sessionFactory;
	}

	private static List<Product> getProductsByQuery(String query) {
		configureSessionFactory();

		Session session = null;
		try {
			session = sessionFactory.openSession();
			session.beginTransaction();

			// Use left join fetch to avoid Cartesian Product problem
			List<Product> productsList = session.createQuery(query).list();

			return productsList;

		} catch (Exception ex) {
			ex.printStackTrace();

			session.getTransaction().rollback();
		} finally {
			if (session != null) {
				session.close();
			}
		}

		return new ArrayList<Product>();
	}

	public static List<ProductWithReferencesDecorator> getAllProductsComplete() {
		List<Product> productsList = getProductsByQuery(
				"select p from Product p LEFT JOIN FETCH p.children LEFT JOIN FETCH p.images");
		return ProductWithReferencesDecorator.fromProductsList(productsList);
	}

	public static List<Product> getAllProducts() {
		return getProductsByQuery("from Product");
	}

	public static Product getProductByQuery(String queryStr, int id) {
		configureSessionFactory();

		Session session = null;
		try {
			session = sessionFactory.openSession();
			session.beginTransaction();

			Query query = session.createQuery(queryStr);
			query.setParameter("id", id);
			Product product = (Product) query.getSingleResult();

			session.evict(product);
			return product;

		} catch (Exception ex) {
			ex.printStackTrace();
			session.getTransaction().rollback();
		} finally {
			if (session != null) {
				session.close();
			}
		}

		return null;
	}

	public static ProductWithReferencesDecorator getProductComplete(int id) {
		Product product = getProductByQuery("from Product where id = :id", id);

		return new ProductWithReferencesDecorator(product);
	}

	public static Product getProduct(int id) {
		return getProductByQuery("from Product where id = :id", id);
	}

	public static Set<Image> getProductImages(int productId) {
		Product product = getProductByQuery("SELECT p from Product p JOIN FETCH p.images where p.id = :id", productId);

		return product.getImages();
	}

	public static Set<Product> getProductChildren(int productId) {
		Product product = getProductByQuery("SELECT p from Product p JOIN FETCH p.children where p.id = :id", productId);

		return product.getChildren();
	}

	public static Product saveOrUpdateProduct(Product product) {
		configureSessionFactory();

		Session session = null;
		try {
			session = sessionFactory.openSession();
			session.beginTransaction();

			session.saveOrUpdate(product);
			session.getTransaction().commit();

			return product;
		} catch (Exception ex) {
			ex.printStackTrace();
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

		if (p1 != null && p2 != null) {
			p1.setParent(p2);
		}

		saveOrUpdateProduct(p1);

		return p1;
	}

	public static Product setChild(int id, int parentId) {
		Product p1 = getProduct(id);
		Product p2 = getProduct(parentId);

		if (p1 != null && p2 != null) {
			p2.setParent(p1);
		}

		saveOrUpdateProduct(p2);

		return p1;
	}

}
