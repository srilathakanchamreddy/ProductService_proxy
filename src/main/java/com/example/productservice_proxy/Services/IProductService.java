package com.example.productservice_proxy.Services;

import com.example.productservice_proxy.DTOs.ProductDTO;

public interface IProductService {
    String getAllProducts();

    String getSingleProduct(long id);

    String createProduct(ProductDTO productDTO);

    String updateProduct(ProductDTO productDTO, long id);

    String deleteProduct(long id);
}
