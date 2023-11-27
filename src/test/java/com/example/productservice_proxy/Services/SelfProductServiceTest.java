package com.example.productservice_proxy.Services;

import com.example.productservice_proxy.Models.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class SelfProductServiceTest {
    @Autowired
    SelfProductService selfProductService;

    @Test
    void updateProduct() {
        Product product = new Product();
        product.setId(1);
        product.setDescription("test");
        Product updatedProduct = selfProductService.updateProduct(product, 1);

    }
}