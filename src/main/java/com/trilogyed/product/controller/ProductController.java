package com.trilogyed.product.controller;

import com.sun.jersey.api.NotFoundException;
import com.trilogyed.product.servicelayer.ProductServiceLayer;
import com.trilogyed.product.viewmodel.ProductViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class ProductController {
    /**----------------------------------- REST CONTROLLER ----------------------------------------------------------*/
    @Autowired
    ProductServiceLayer service;

    @RequestMapping(value = "/products", method = RequestMethod.POST)
    public ProductViewModel createProducts(@RequestBody @Valid ProductViewModel productViewModel){
        return service.createProduct(productViewModel);
    }

    @RequestMapping(value = "/products/{id}", method = RequestMethod.GET)
    public ProductViewModel getProducts(@PathVariable int id){
        ProductViewModel productViewModel = service.getProduct(id);
        if(productViewModel == null)
            throw new NotFoundException("Product could not be found for id "+id);
        return productViewModel;

    }

    @RequestMapping(value = "/products/all", method = RequestMethod.GET)
    public List<ProductViewModel> getAllProduct(){
        return service.getAllProduct();
    }

    @RequestMapping(value = "/products/{id}", method = RequestMethod.PUT)
    public void updateProducts(@RequestBody @Valid ProductViewModel productViewModel, @PathVariable int id) {
        if(productViewModel.getProductId() == 0)
            productViewModel.setProductId(id);
        if(id != productViewModel.getProductId()) {

            throw new IllegalArgumentException("Post ID on path must match the ID in the Post Object.");
        }
        service.updateProduct(productViewModel);
    }

    @RequestMapping(value = "/products/{id}", method = RequestMethod.DELETE)
    public void deletePost(@PathVariable int id) {
        service.deleteProduct(id);

    }

    /**----------------------------------------------------------------------------------------------------------------*/


}
