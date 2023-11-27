package com.example.productservice_proxy.Controllers;

import com.example.productservice_proxy.DTOs.ProductDto;
import com.example.productservice_proxy.DTOs.ProductMapper;
import com.example.productservice_proxy.Models.Category;
import com.example.productservice_proxy.Models.Product;
import com.example.productservice_proxy.Services.IProductService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ProductController.class)

class ProductControllerMVCTest {
    @MockBean
    IProductService productService;
    @MockBean
    ProductMapper productMapper;
    @Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper objectMapper;
    @Test
    @DisplayName("when get all products is called, then return list of products")
    void getAllProducts() throws Exception {
        List<ProductDto> productDtos = new ArrayList<>();
        ProductDto productDto = new ProductDto();
        productDto.setId(1);
        productDto.setTitle("test");
        productDto.setDescription("test");
        productDto.setImage_url("test");
        productDto.setPrice(1);
        productDtos.add(productDto);
        when(productService.getAllProducts()).thenReturn(new ArrayList<>());
        when(productMapper.toDtoList(any())).thenReturn(productDtos);
        mockMvc.perform(get("/products/"))
                .andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(productDtos)));
    }
    @Test
    @DisplayName("when get all products is called, then throw exception")
    void getAllProductsException() throws Exception {
        when(productService.getAllProducts()).thenThrow(new RuntimeException("test"));
        mockMvc.perform(get("/products/"))
                .andExpect(status().isInternalServerError())
                .andExpect(content().string("test"));
    }
    @Test
    @DisplayName("when get all products is called, then return empty list")
    void getAllProductsEmpty() throws Exception {
        when(productService.getAllProducts()).thenReturn(new ArrayList<>());
        mockMvc.perform(get("/products/"))
                .andExpect(status().isOk())
                .andExpect(content().string("[]"));
    }
    @Test
    @DisplayName("when get single product is called, then return product")
    void getSingleProduct() throws Exception {
        ProductDto productDto = new ProductDto();
        productDto.setId(1L);
        productDto.setTitle("test");
        productDto.setDescription("test");
        productDto.setImage_url("test");
        productDto.setPrice(1);
        when(productService.getSingleProduct(anyLong())).thenReturn(new Product());
        when(productMapper.toDto(any())).thenReturn(productDto);
        mockMvc.perform(get("/products/1"))
                .andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(productDto)));
    }
    @Test
    @DisplayName("when get single product is called, then throw exception")
    void getSingleProductException() throws Exception {
        when(productService.getSingleProduct(anyLong())).thenThrow(new RuntimeException("test"));
        mockMvc.perform(get("/products/1"))
                .andExpect(status().isInternalServerError())
                .andExpect(content().string("test"));
    }
    @Test
    @DisplayName("when get single product is called, then return empty list")
    void getSingleProductEmpty() throws Exception {
        when(productService.getSingleProduct(anyLong())).thenReturn(null);
        mockMvc.perform(get("/products/1"))
                .andExpect(status().isNotFound())
                .andExpect(content().string("Product not found"));
    }
    @Test
    @DisplayName("when create product is called, then return product")
    void createProduct() throws Exception {
        ProductDto productDto = new ProductDto();
        //productDto.setId(1L);
        productDto.setTitle("test");
        productDto.setDescription("test");
        productDto.setImage_url("test");
        productDto.setCategory("fashion");
        productDto.setPrice(1);
        Product product = new Product();
        product.setCategory(new Category());

        when(productMapper.toEntity(any())).thenReturn(product);
       when(productService.createProduct(any())).thenReturn(new Product());
        when(productMapper.toDto(any())).thenReturn(productDto);
        //have to send json string as request body
        mockMvc.perform(post("/products/").contentType("application/json").content(objectMapper.writeValueAsString(productDto)))
                .andExpect(status().isCreated())
                .andExpect(content().string(objectMapper.writeValueAsString(productDto)));
    }
    @Test
    @DisplayName("when create product is called, then throw runtime exception")
    void createProductException() throws Exception {
        ProductDto productDto = new ProductDto();
        //productDto.setId(1L);
        productDto.setTitle("test");
        productDto.setDescription("test");
        productDto.setImage_url("test");
        productDto.setCategory("fashion");
        productDto.setPrice(1);
        Product product = new Product();
        product.setCategory(new Category());

        when(productMapper.toEntity(any())).thenReturn(product);
        when(productService.createProduct(any())).thenThrow(new RuntimeException("test"));
        when(productMapper.toDto(any())).thenReturn(productDto);

        mockMvc.perform(post("/products/").contentType("application/json").content(objectMapper.writeValueAsString(productDto)))
                .andExpect(status().isInternalServerError())
                .andExpect(content().string("test"));
    }
    @Test
    @DisplayName("when create product is called with category as null, then throw category not found exception")
    void createProductExceptionCategoryNull() throws Exception {
        ProductDto productDto = new ProductDto();
        //productDto.setId(1L);
        productDto.setTitle("test");
        productDto.setDescription("test");
        productDto.setImage_url("test");
        productDto.setCategory(null);
        productDto.setPrice(1);
        Product product = new Product();


        when(productMapper.toEntity(any())).thenReturn(product);


        mockMvc.perform(post("/products/").contentType("application/json").content(objectMapper.writeValueAsString(productDto)))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("category is required"));
    }

    @Test
    @DisplayName("when update product is called, then return product")
    void updateProduct() throws Exception {
        ProductDto productDto = new ProductDto();
        productDto.setId(1L);
        productDto.setTitle("test");
        productDto.setDescription("test");
        productDto.setImage_url("test");
        productDto.setCategory("fashion");
        productDto.setPrice(1);
        Product product = new Product();
        product.setCategory(new Category());

        when(productMapper.toEntity(any())).thenReturn(product);
        when(productService.createProduct(any())).thenReturn(new Product());
        when(productMapper.toDto(any())).thenReturn(productDto);

        //have to send json string as request body
        mockMvc.perform(put("/products/1").contentType("application/json").content(objectMapper.writeValueAsString(productDto)))
                .andExpect(status().isCreated())
                .andExpect(content().string(objectMapper.writeValueAsString(productDto)));
    }

    @Test
    @DisplayName("when update product is called, then throw runtime exception")
    void updateProductException() throws Exception {
        ProductDto productDto = new ProductDto();
        productDto.setId(1L);
        productDto.setTitle("test");
        productDto.setDescription("test");
        productDto.setImage_url("test");
        productDto.setCategory("fashion");
        productDto.setPrice(1);
        Product product = new Product();
        product.setCategory(new Category());

        when(productMapper.toEntity(any())).thenReturn(product);
        when(productService.createProduct(any())).thenThrow(new RuntimeException("test"));
        when(productMapper.toDto(any())).thenReturn(productDto);

        mockMvc.perform(put("/products/1").contentType("application/json").content(objectMapper.writeValueAsString(productDto)))
                .andExpect(status().isInternalServerError())
                .andExpect(content().string("test"));
    }

    @Test
    @DisplayName("when update product is called with category as null, then throw category not found exception")
    void updateProductExceptionCategoryNull() throws Exception {
        ProductDto productDto = new ProductDto();
        productDto.setId(1L);
        productDto.setTitle("test");
        productDto.setDescription("test");
        productDto.setImage_url("test");
        productDto.setCategory(null);
        productDto.setPrice(1);
        Product product = new Product();
        when(productMapper.toEntity(any())).thenReturn(product);
        mockMvc.perform(put("/products/1").contentType("application/json").content(objectMapper.writeValueAsString(productDto)))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("category is required"));
    }

    @Test
    @DisplayName("when partial update product is called, then return product")
    void partialUpdateProduct() throws Exception {
        ProductDto productDto = new ProductDto();
        productDto.setId(1L);
        productDto.setTitle("test");
        productDto.setDescription("test");
        productDto.setImage_url("test");
        productDto.setCategory("fashion");
        productDto.setPrice(1);
        Product product = new Product();
        product.setCategory(new Category());

        when(productMapper.toEntity(any())).thenReturn(product);
        when(productService.createProduct(any())).thenReturn(new Product());
        when(productMapper.toDto(any())).thenReturn(productDto);

        //have to send json string as request body
        mockMvc.perform(patch("/products/1").contentType("application/json").content(objectMapper.writeValueAsString(productDto)))
                .andExpect(status().isCreated())
                .andExpect(content().string(objectMapper.writeValueAsString(productDto)));
    }

    @Test
    @DisplayName("when partial update product is called, then throw runtime exception")
    void partialUpdateProductException() throws Exception {
        ProductDto productDto = new ProductDto();
        productDto.setId(1L);
        productDto.setTitle("test");
        productDto.setDescription("test");
        productDto.setImage_url("test");
        productDto.setCategory("fashion");
        productDto.setPrice(1);
        Product product = new Product();
        product.setCategory(new Category());

        when(productMapper.toEntity(any())).thenReturn(product);
        when(productService.createProduct(any())).thenThrow(new RuntimeException("test"));
        when(productMapper.toDto(any())).thenReturn(productDto);

        mockMvc.perform(patch("/products/1").contentType("application/json").content(objectMapper.writeValueAsString(productDto)))
                .andExpect(status().isInternalServerError())
                .andExpect(content().string("test"));
    }






}