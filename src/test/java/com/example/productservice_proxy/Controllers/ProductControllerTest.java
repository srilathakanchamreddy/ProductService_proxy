package com.example.productservice_proxy.Controllers;

import com.example.productservice_proxy.DTOs.ProductDto;
import com.example.productservice_proxy.Models.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class ProductControllerTest {
@Autowired
    ProductController productController;
    @Test
    void updateProduct() {
        ProductDto productDto = new ProductDto();
        productDto.setId(1);
        productDto.setDescription("test");
        ProductDto updatedProduct = productController.updateProduct(productDto, 1).getBody();

    }
}