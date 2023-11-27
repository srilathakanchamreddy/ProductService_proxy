package com.example.productservice_proxy.Controllers;

import com.example.productservice_proxy.DTOs.*;
import com.example.productservice_proxy.Models.Category;
import com.example.productservice_proxy.Models.Product;
import com.example.productservice_proxy.Services.ICategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
@RestController
@RequestMapping ("/product/categories")
public class CategoryController {

    private ICategoryService categoryService;
    private CategoryMapper categoryMapper;
    private ProductMapper productMapper;
    CategoryController(ICategoryService categoryService, CategoryMapper categoryMapper, ProductMapper productMapper){
        this.categoryService = categoryService;
        this.categoryMapper = categoryMapper;
        this.productMapper = productMapper;
    }
    @GetMapping("/")
    public ResponseEntity<List<CategoryDto>> getAllCategories() {
        List<Category> categories = categoryService.getAllCategories();
        List<CategoryDto> categoryDtos = categoryMapper.toDtoList(categories);
        return new ResponseEntity<>(categoryDtos, HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<CategoryDto> getSingleCategory(@PathVariable("id") long id) {

        Category category =  categoryService.getSingleCategory(id);
        CategoryDto categoryDto = categoryMapper.toDto(category);
        return new ResponseEntity<>(categoryDto, HttpStatus.OK);
    }



    @GetMapping("/{id}/products")
    public ResponseEntity<List<ProductDto>> getProductsByCategory(@PathVariable("id") long id) {
           List<Product> products = categoryService.getSingleCategory(id).getProductList();
           List<ProductDto> productDtoList = productMapper.toDtoList(products);
        return new ResponseEntity<>(productDtoList, HttpStatus.OK);


    }
    @Transactional
    @PostMapping ("/")
    public ResponseEntity<CategoryDto> createCategory(@RequestBody CategoryDto categoryDto) {
        Category category = categoryMapper.toEntity(categoryDto);
        Category categoryCreated  = categoryService.createCategory(category);
        categoryDto = categoryMapper.toDto(categoryCreated);
        return new ResponseEntity<>(categoryDto, HttpStatus.CREATED);
    }



    @PutMapping("/{id}")
    public ResponseEntity<CategoryDto> updateCategory(@RequestBody CategoryDto categoryDto, @PathVariable long id) {
        Category category = categoryMapper.toEntity(categoryDto);
        Category categoryUpdated = categoryService.createCategory(category);
        categoryDto = categoryMapper.toDto(categoryUpdated);
        return new ResponseEntity<>(categoryDto, HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<CategoryDto> partialUpdateCategory(@RequestBody CategoryDto categoryDto, @PathVariable long id) {
        Category existingCategory = categoryService.getSingleCategory(id);
        Category category = categoryMapper.partialUpdate(categoryDto, existingCategory);
        Category categoryUpdated = categoryService.createCategory(category);
        categoryDto = categoryMapper.toDto(categoryUpdated);
        return new ResponseEntity<>(categoryDto, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CategoryDto> deleteCategory(@PathVariable long id) {
        return null;
    }
}
