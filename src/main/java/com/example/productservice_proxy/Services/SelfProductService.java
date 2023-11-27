package com.example.productservice_proxy.Services;

import com.example.productservice_proxy.Exceptions.CategoryNotPresentException;
import com.example.productservice_proxy.Models.Category;
import com.example.productservice_proxy.Models.Product;
import com.example.productservice_proxy.Repositaries.ProductRepo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
public class SelfProductService implements com.example.productservice_proxy.Services.IProductService{
    ProductRepo productRepo;
    ICategoryService categoryService;
    SelfProductService(ProductRepo productRepo, ICategoryService categoryService){
        this.productRepo = productRepo;
        this.categoryService = categoryService;
    }
    @Override
    public List<Product> getAllProducts() {
        return productRepo.findAll();
    }

    @Override
    public Product getSingleProduct(long id) {
        return productRepo.getProductById(id);
    }

    @Override
    public Product updateProduct(Product product, long id) {
        return productRepo.save(product);



    }

    @Override
    @Transactional
    public Product createProduct(Product product) {
        //check if category exists
       Category category = categoryService.getCategoryByName(product.getCategory().getName());
       //if category does not exist, create it
       if(category == null) {
           category = categoryService.createCategory(product.getCategory());
       }
       //set category of product
        product.setCategory(category);
        productRepo.save(product);
        return product;
    }



    @Override
    public Product deleteProduct(long id) {
        return productRepo.deleteProductById(id);
    }
}
