package com.example.productservice_proxy.Controllers;

import com.example.productservice_proxy.DTOs.CategoryDto;
import com.example.productservice_proxy.DTOs.CategoryMapper;
import com.example.productservice_proxy.DTOs.ProductMapper;
import com.example.productservice_proxy.Models.Category;
import com.example.productservice_proxy.Services.ICategoryService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CategoryController.class)
class CategoryControllerTest {
    @Autowired
    MockMvc mockMvc;
    @MockBean
    ICategoryService categoryService;
    @MockBean
    CategoryMapper categoryMapper;
    @MockBean
    ProductMapper productMapper;
    @Autowired
    ObjectMapper objectMapper;
    /*@Test
    @DisplayName("When getAllCategories is called, then return all categories")
    void getAllCategories() throws Exception {
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setName("Electronics");
        categoryDto.setDescription("Electronics");
        when(categoryService.getAllCategories()).thenReturn(null);
        when(categoryMapper.toDtoList(any())).thenReturn(List.of(categoryDto));
        mockMvc.perform(get("/product/categories/"))
                .andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(categoryDto)));
    }*/

}