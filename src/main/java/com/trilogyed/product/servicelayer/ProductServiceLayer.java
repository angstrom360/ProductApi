package com.trilogyed.product.servicelayer;

import com.trilogyed.product.dao.ProductDao;
import com.trilogyed.product.model.Product;
import com.trilogyed.product.viewmodel.ProductViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Component
@RefreshScope
public class ProductServiceLayer {
    /**------------- Importing all objects and variables that will be used to build the ServiceLayer----------------- */
    @Autowired
    ProductDao productDao;

    @Autowired
    public ProductServiceLayer(ProductDao productDao) {
        this.productDao = productDao;
    }
    private ProductViewModel buildProductViewModel (Product product){
        ProductViewModel productViewModel = new ProductViewModel();
        productViewModel.setProductId(product.getProductId());
        productViewModel.setProductName(product.getProductName());
        productViewModel.setProductDescription(product.getProductDescription());
        productViewModel.setListPrice(product.getListPrice());
        productViewModel.setUnitCost(product.getUnitCost());

        return productViewModel;
    }
    /**---------------------------------------------------------------------------------------------------------------*/

    /**The Business Logic for the service Layer is CRUD */
    @Transactional
    public ProductViewModel createProduct(ProductViewModel productViewModel){
        Product product = new Product();
        product.setProductName(productViewModel.getProductName());
        product.setProductDescription(productViewModel.getProductDescription());
        product.setListPrice(productViewModel.getListPrice());
        product.setUnitCost(productViewModel.getUnitCost());
        product = productDao.createProduct(product);
        productViewModel.setProductId(product.getProductId());

        return productViewModel;
    }

    public ProductViewModel getProduct(int id){
        Product product = productDao.getProduct(id);
        if(product == null)
            return null;
        else
            return buildProductViewModel(product);

    }

    public List<ProductViewModel> getAllProduct(){
        List<Product> productList = productDao.getAllProducts();
        List<ProductViewModel> productViewModelList = new ArrayList<>();

        for(Product cList : productList){
            ProductViewModel productViewModel = buildProductViewModel(cList);
            productViewModelList.add(productViewModel);
        }

        return productViewModelList;
    }

    public void updateProduct(ProductViewModel productViewModel){
        Product product = new Product();
        product.setProductId(productViewModel.getProductId());
        product.setProductName(productViewModel.getProductName());
        product.setProductDescription(productViewModel.getProductDescription());
        product.setListPrice(productViewModel.getListPrice());
        product.setUnitCost(productViewModel.getUnitCost());
        productDao.updateProduct(product);

    }
    public void deleteProduct(int id){
        productDao.deleteProduct(id);
    }


}
