package com.example.productservice_proxy.Services;

import com.example.productservice_proxy.Models.Product;
import com.example.productservice_proxy.Repositaries.ProductRepo;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@SpringBootTest
class SelfProductServiceTest {
    @Autowired
    IProductService selfProductService;
    @MockBean
    ProductRepo productRepo;

    @Test
    @DisplayName("When get All products is called with id = 1, then verify the size of the list returned")
    void getAllProducts() {
        Product product = new Product();
        product.setTitle("Mobile");
        product.setDescription("Mobile");

        when(productRepo.findAll()).thenReturn(List.of(product));
        List<Product> products = selfProductService.getAllProducts();
        assertThat(products.size()).isEqualTo(1);
    }
}