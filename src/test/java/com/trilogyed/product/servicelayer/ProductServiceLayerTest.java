package com.trilogyed.product.servicelayer;

import com.trilogyed.product.dao.ProductDao;
import com.trilogyed.product.dao.ProductDaoJdbcTemplate;
import com.trilogyed.product.model.Product;
import com.trilogyed.product.viewmodel.ProductViewModel;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

public class ProductServiceLayerTest {
    @Autowired
    ProductDao productDao;

    @Autowired
    ProductServiceLayer productServiceLayer;

    private void setUpProductDaoMock(){
        productDao = mock(ProductDaoJdbcTemplate.class);

        Product product = new Product();
        product.setProductId(1);
        product.setProductName("iPhone");
        product.setProductDescription("Black - 8GB Memory");
        product.setListPrice(new BigDecimal(125.99));
        product.setUnitCost(new BigDecimal(250.00));

        Product product1 = new Product();
        product1.setProductName("iPhone");
        product1.setProductDescription("Black - 8GB Memory");
        product1.setListPrice(new BigDecimal(125.99));
        product1.setUnitCost(new BigDecimal(250.00));

        List<Product> cList = new ArrayList<>();
        cList.add(product);

        doReturn(product).when(productDao).createProduct(product1);
        doReturn(product).when(productDao).getProduct(1);
        doReturn(cList).when(productDao).getAllProducts();

    }

    @Before
    public void setUp()throws Exception{
        setUpProductDaoMock();
        productServiceLayer = new ProductServiceLayer(productDao);
    }

    @Test
    public void createProduct(){
        ProductViewModel product = new ProductViewModel();
        product.setProductId(1);
        product.setProductName("iPhone");
        product.setProductDescription("Black - 8GB Memory");
        product.setListPrice(new BigDecimal(125.99));
        product.setUnitCost(new BigDecimal(250.00));
        product = productServiceLayer.createProduct(product);

        ProductViewModel product1 = new ProductViewModel();
        product1.setProductId(1);
        product1.setProductName("iPhone");
        product1.setProductDescription("Black - 8GB Memory");
        product1.setListPrice(new BigDecimal(125.99));
        product1.setUnitCost(new BigDecimal(250.00));

        ProductViewModel fromService = productServiceLayer.createProduct(product1);
        assertEquals(product,fromService);

    }

    @Test
    public void getProduct(){

        ProductViewModel product = new ProductViewModel();
        product.setProductId(1);
        product.setProductName("iPhone");
        product.setProductDescription("Black - 8GB Memory");
        product.setListPrice(new BigDecimal(125.99));
        product.setUnitCost(new BigDecimal(250.00));
        product = productServiceLayer.createProduct(product);

        ProductViewModel fromService = productServiceLayer.getProduct(1);
        assertEquals(product,fromService);

    }

    @Test
    public void getAllProduct(){

        ProductViewModel product = new ProductViewModel();
        product.setProductId(1);
        product.setProductName("iPhone");
        product.setProductDescription("Black - 8GB Memory");
        product.setListPrice(new BigDecimal(125.99));
        product.setUnitCost(new BigDecimal(250.00));
        product = productServiceLayer.createProduct(product);

        List<ProductViewModel> fromService = productServiceLayer.getAllProduct();
        assertEquals(1,fromService.size());
        assertEquals(product,fromService.get(0));

    }
}
