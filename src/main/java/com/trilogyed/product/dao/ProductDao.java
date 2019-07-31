package com.trilogyed.product.dao;

import com.trilogyed.product.model.Product;

import java.util.List;

public interface ProductDao {

    //-------------- Basic CRUD Methods ------------//

    Product createProduct(Product product);
    Product getProduct(int id);
    List<Product> getAllProducts();
    void updateProduct(Product product);
    void deleteProduct(int id);

    // -------------------------------------------//

}
