package com.example.productservice_proxy.Services;

import com.example.productservice_proxy.Clients.FakeStore.DTO.FakeStoreProductDTO;
import com.example.productservice_proxy.DTOs.ProductDTO;
import com.example.productservice_proxy.Models.Product;
import com.example.productservice_proxy.Repositaries.ProductRepo;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class SelfProductService implements com.example.productservice_proxy.Services.IProductService{
    ProductRepo productRepo;
    SelfProductService(ProductRepo productRepo){
        this.productRepo = productRepo;
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
        return null;
    }

    @Override
    public Product createProduct(Product product) {

        productRepo.save(product);
        return product;
    }



    @Override
    public Product deleteProduct(long id) {
        return null;
    }
}
