package com.dmnoky.prd.service;

import com.dmnoky.prd.model.Product;

import java.util.List;

public interface ProductService {
    void addProduct(Product product);
    void updateProduct(Product product);
    boolean removeProduct(int id);
    Product getProduct(int id);
    List<Product> getAllProducts();
}
