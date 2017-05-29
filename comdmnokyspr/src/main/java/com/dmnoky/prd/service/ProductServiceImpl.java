package com.dmnoky.prd.service;

import com.dmnoky.prd.dao.ProductDAO;
import com.dmnoky.prd.model.Product;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    private ProductDAO productDAO;

    public void setProductDAO(ProductDAO productDAO) {
        this.productDAO = productDAO;
    }

    @Override
    @Transactional
    public void addProduct(Product product) {
        this.productDAO.addProduct(product);
    }

    @Override
    @Transactional
    public void updateProduct(Product product) {
        this.productDAO.updateProduct(product);
    }

    @Override
    @Transactional
    public boolean removeProduct(int id) {
        return this.productDAO.removeProduct(id);
    }

    @Override
    @Transactional
    public Product getProduct(int id) {
        return this.productDAO.getProduct(id);
    }

    @Override
    @Transactional
    public List<Product> getAllProducts() {
        return this.productDAO.getAllProducts();
    }
}
