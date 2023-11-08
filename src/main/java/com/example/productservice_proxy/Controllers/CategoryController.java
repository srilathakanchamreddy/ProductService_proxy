package com.example.productservice_proxy.Controllers;

import com.example.productservice_proxy.DTOs.CategoryDTO;
import com.example.productservice_proxy.DTOs.ProductDTO;
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
    CategoryController(ICategoryService categoryService){
        this.categoryService = categoryService;
    }
    @GetMapping("/")
    public ResponseEntity<List<CategoryDTO>> getAllCategories() {
        List<Category> categories = categoryService.getAllCategories();
        List<CategoryDTO> categoryDTOS = new ArrayList<>();
        for (Category category: categories) {
            CategoryDTO categoryDTO = getCategoryDTO(category);
            categoryDTOS.add(categoryDTO);
        }
        return new ResponseEntity<>(categoryDTOS, HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<CategoryDTO> getSingleCategory(@PathVariable("id") long id) {

        Category category =  categoryService.getSingleCategory(id);
        return new ResponseEntity<>(getCategoryDTO(category), HttpStatus.OK);
    }

    private CategoryDTO getCategoryDTO(Category category) {
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setId(category.getId());
        categoryDTO.setName(category.getName());
        categoryDTO.setDescription(category.getDescription());
        if(category.getProductList() == null) return categoryDTO;
        List<String> productList = new ArrayList<>();
        for (int i = 0; i < category.getProductList().size(); i++) {
            productList.add(category.getProductList().get(i).getTitle());
        }
        categoryDTO.setProductList(productList);
        return categoryDTO;
    }

    @GetMapping("/{id}/products")
    public ResponseEntity<List<ProductDTO>> getProductsByCategory(@PathVariable("id") long id) {
           List<Product> products = categoryService.getSingleCategory(id).getProductList();
              List<ProductDTO> productDTOS = new ArrayList<>();
                for (Product product: products) {
                    ProductDTO productDTO = new ProductDTO();
                    productDTO.setId(product.getId());
                    productDTO.setTitle(product.getTitle());
                    productDTO.setDescription(product.getDescription());
                    productDTOS.add(productDTO);
                }
                return new ResponseEntity<>(productDTOS, HttpStatus.OK);

    }
    @Transactional
    @PostMapping("/")
    public ResponseEntity<CategoryDTO> createCategory(@RequestBody CategoryDTO categoryDTO) {

        Category category = categoryService.createCategory(getcategoryfromDTO(categoryDTO));
        return new ResponseEntity<>(getCategoryDTO(category), HttpStatus.CREATED);
    }

    private Category getcategoryfromDTO(CategoryDTO categoryDTO) {
        Category category = new Category();
        category.setName(categoryDTO.getName());
        category.setDescription(categoryDTO.getDescription());
        return category;
    }

    @PatchMapping("/{id}")
    public ResponseEntity<CategoryDTO> updateCategory(@RequestBody CategoryDTO categoryDTO,@PathVariable long id) {
        return null;
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<CategoryDTO> deleteCategory(@PathVariable long id) {
        return null;
    }
}
