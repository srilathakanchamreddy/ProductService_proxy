package com.example.productservice_proxy.Controllers;

import com.example.productservice_proxy.DTOs.CategoryDTO;
import com.example.productservice_proxy.DTOs.CategoryMapper;
import com.example.productservice_proxy.DTOs.CategoryWithProductListDto;
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
    private CategoryMapper categoryMapper;
    CategoryController(ICategoryService categoryService, CategoryMapper categoryMapper){
        this.categoryService = categoryService;
        this.categoryMapper = categoryMapper;
    }
    @GetMapping("/")
    public ResponseEntity<List<CategoryWithProductListDto>> getAllCategories() {
        List<Category> categories = categoryService.getAllCategories();
        List<CategoryWithProductListDto> categoryWithProductListDtos = new ArrayList<>();
        for (Category category: categories) {
            CategoryWithProductListDto categoryWithProductListDto = categoryMapper.toDto(category);
            categoryWithProductListDtos.add(categoryWithProductListDto);
        }
        return new ResponseEntity<>(categoryWithProductListDtos, HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<CategoryWithProductListDto> getSingleCategory(@PathVariable("id") long id) {

        Category category =  categoryService.getSingleCategory(id);
        CategoryWithProductListDto categoryWithProductListDto = categoryMapper.toDto(category);
        return new ResponseEntity<>(categoryWithProductListDto, HttpStatus.OK);
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
    @PostMapping ("/")
    public ResponseEntity<CategoryWithProductListDto> createCategory(@RequestBody CategoryWithProductListDto categoryWithProductListDto) {
        Category category = categoryMapper.toEntity(categoryWithProductListDto);
        Category categoryCreated  = categoryService.createCategory(category);
        categoryWithProductListDto = categoryMapper.toDto(categoryCreated);
        return new ResponseEntity<>(categoryWithProductListDto, HttpStatus.CREATED);
    }

    private Category getcategoryfromDTO(CategoryDTO categoryDTO) {
        Category category = new Category();
        category.setName(categoryDTO.getName());
        category.setDescription(categoryDTO.getDescription());
        return category;
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryWithProductListDto> updateCategory(@RequestBody CategoryWithProductListDto categoryWithProductListDto,@PathVariable long id) {
        Category existingCategory = categoryService.getSingleCategory(id);
        Category category = categoryMapper.partialUpdate(categoryWithProductListDto, existingCategory);
        Category categoryResponse = categoryService.createCategory(category);
        CategoryWithProductListDto categoryWithProductListDtoResponse = categoryMapper.toDto(categoryResponse);
        return new ResponseEntity<>(categoryWithProductListDtoResponse, HttpStatus.CREATED);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<CategoryDTO> deleteCategory(@PathVariable long id) {
        return new ResponseEntity<>(categoryService.deleteCategory(id), HttpStatus.OK);
    }
}
