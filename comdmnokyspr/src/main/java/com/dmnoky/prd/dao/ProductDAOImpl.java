package com.dmnoky.prd.dao;

import com.dmnoky.prd.model.Product;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductDAOImpl implements ProductDAO{
    private static final Logger logger = LoggerFactory.getLogger(ProductDAOImpl.class);
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void addProduct(Product product) {
        Session session = this.sessionFactory.getCurrentSession();
        session.persist(product);
        logger.info("Product: "+product+" successfully added.");
    }

    @Override
    public void updateProduct(Product product) {
        Session session = this.sessionFactory.getCurrentSession();
        session.update(product);
        logger.info("Product: "+product+" successfully updated.");
    }

    @Override
    public boolean removeProduct(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        Product product = session.load(Product.class, id);
        if (product!=null){
            session.delete(product);
            logger.info("Product: "+product+" successfully removed.");
            return true;
        }
        logger.info("Product: unsuccessfully removed.");
        return false;
    }

    @Override
    public Product getProduct(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        Product product = session.load(Product.class, id);
        logger.info("Product: "+product+" successfully loaded.");
        return product;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Product> getAllProducts() {
        Session session = this.sessionFactory.getCurrentSession();
        List<Product> productList = session.createQuery("FROM Product").list();
        logger.info("Products: "+productList.size()+" successfully loaded.");
        return productList;
    }
}
