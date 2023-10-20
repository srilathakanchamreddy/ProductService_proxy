package com.example.productservice_proxy.Services;

import com.example.productservice_proxy.DTOs.ProductDTO;
import com.example.productservice_proxy.Models.Product;

import java.util.List;

public interface IProductService {
    List<Product> getAllProducts();

    Product getSingleProduct(long id);

    Product createProduct(ProductDTO productDTO);

    Product updateProduct(ProductDTO productDTO, long id);

    Product deleteProduct(long id);
}
