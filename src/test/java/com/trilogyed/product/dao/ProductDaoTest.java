package com.trilogyed.product.dao;

import com.trilogyed.product.model.Product;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class ProductDaoTest {

    @Autowired
    ProductDao productDao;
    @Before
    public void SetUp() throws Exception{

        List<Product> cList = productDao.getAllProducts();

        for(Product c : cList){
            productDao.deleteProduct(c.getProductId());
        }

    }

    @Test
    public void addGetDeleteProduct(){
        Product product = new Product();
        product.setProductName("iPhone");
        product.setProductDescription("Black - 8GB Memory");
        product.setListPrice(new BigDecimal("125.99"));
        product.setUnitCost(new BigDecimal("250.00"));
        product = productDao.createProduct(product);

        Product product1 = productDao.getProduct(product.getProductId());

        assertEquals(product1,product);

        productDao.deleteProduct(product.getProductId());

        product1 = productDao.getProduct(product.getProductId());

        assertNull(product1);

    }

    @Test
    public void getAllProducts(){

        Product product = new Product();
        product.setProductName("iPhone");
        product.setProductDescription("Black - 8GB Memory");
        product.setListPrice(new BigDecimal("125.99"));
        product.setUnitCost(new BigDecimal("250.00"));
        product = productDao.createProduct(product);

        product =new Product();
        product.setProductName("PS4");
        product.setProductDescription("Black - 500GB Memory");
        product.setListPrice(new BigDecimal("350.00"));
        product.setUnitCost(new BigDecimal("250.00"));
        productDao.createProduct(product);

        List<Product> cList = productDao.getAllProducts();

        assertEquals(cList.size(),2);
    }

    @Test
    public void updateProduct(){
        Product product = new Product();
        product.setProductName("iPhone");
        product.setProductDescription("Black - 8GB Memory");
        product.setListPrice(new BigDecimal("125.99"));
        product.setUnitCost(new BigDecimal("250.00"));
        product = productDao.createProduct(product);

        product.setListPrice(new BigDecimal("100.00"));
        productDao.updateProduct(product);

        Product product1 = productDao.getProduct(product.getProductId());

        assertEquals(product1,product);
    }

}
