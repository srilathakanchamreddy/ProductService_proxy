package com.example.productservice_proxy.Services;

import com.example.productservice_proxy.DTOs.ProductDTO;

public class ProductService implements IProductService {
    @Override
    public String getAllProducts() {
        return "getAllProducts";
    }

    @Override
    public String getSingleProduct(long id) {
        return "getSingleProduct";
    }

    @Override
    public String createProduct(ProductDTO productDTO) {
        return "createProduct";
    }
    @Override
    public String updateProduct(ProductDTO productDTO, long id) {
        return "updateProduct";
    }

    @Override
    public String deleteProduct(long id) {
        return "deleteProduct";
    }
}
