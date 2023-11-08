package com.example.productservice_proxy.Repositaries;

import com.example.productservice_proxy.Models.Category;
import com.example.productservice_proxy.Models.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class ProductRepoTest {
    @Autowired
    private ProductRepo productRepo;
@Test
    public void testSave() {
        // test save method

        Product product = new Product();
        product.setTitle("T-shirt");
        product.setDescription("This is a T-shirt");
        Category category = new Category();
        category.setName("Fashion");
        category.setDescription("This is a fashion category");
        product.setCategory(category);
        productRepo.save(product);

    }

}