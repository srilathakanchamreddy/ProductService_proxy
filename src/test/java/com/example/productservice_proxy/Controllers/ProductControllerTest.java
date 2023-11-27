package com.example.productservice_proxy.Controllers;

import com.example.productservice_proxy.DTOs.ProductDto;
import com.example.productservice_proxy.Models.Product;
import com.example.productservice_proxy.Repositaries.ProductRepo;
import com.example.productservice_proxy.Services.IProductService;
import com.example.productservice_proxy.Services.SelfProductService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
class ProductControllerTest {
    @Autowired
    ProductController productController;
    @MockBean
    SelfProductService productService;
    @Captor
    ArgumentCaptor<Long> idCaptor;


    //I am not able to implement this because of when original method is called it
    //is asking bean of type ProductRepo and if I autowire or mock it , it is not working
    // because product service is itself is a mock bean
    //Test to check if same argument is passed to get single product method of product service
    // from get single product method of product controller
    @Test
    @DisplayName("When getSingleProduct is called with id = 1, then product service's getSingleProduct is called with id = 1")
    void getSingleProduct() {
        //write code to mock the product service's getSingleProduct method and make it implement the original method
        when(productService.getSingleProduct(anyLong())).thenCallRealMethod();

        //call the getSingleProduct method of product controller
        productController.getSingleProduct(1);
        //verify if the same argument is passed to the product service's getSingleProduct method
        verify(productService).getSingleProduct(idCaptor.capture());
        assertEquals(1L, idCaptor.getValue());

    }



}