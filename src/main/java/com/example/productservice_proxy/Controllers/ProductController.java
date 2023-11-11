package com.example.productservice_proxy.Controllers;

import com.example.productservice_proxy.DTOs.ProductDTO;
import com.example.productservice_proxy.DTOs.ProductWithCategoryIdDto;
import com.example.productservice_proxy.DTOs.ProductWithCategoryIdMapper;
import com.example.productservice_proxy.Models.Category;
import com.example.productservice_proxy.Models.Product;
import com.example.productservice_proxy.Services.ICategoryService;
import com.example.productservice_proxy.Services.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/products")
public class ProductController {
    private IProductService productService;
    private ICategoryService categoryService;

    private ProductWithCategoryIdMapper productWithCategoryIdMapper;
    ProductController(IProductService productService, ICategoryService categoryService, ProductWithCategoryIdMapper productWithCategoryIdMapper){
        this.productService =productService;
        this.categoryService = categoryService;
        this.productWithCategoryIdMapper = productWithCategoryIdMapper;
    }
    @GetMapping("/")
    public ResponseEntity<List<ProductWithCategoryIdDto>> getAllProducts(){
        List<Product> products = productService.getAllProducts();
        //write code to convert List<Product> to  List<ProductWithCategoryIdDto> using streams
        return new ResponseEntity<List<ProductWithCategoryIdDto>>
                ((List<ProductWithCategoryIdDto>) products.stream().
                        map(product -> productWithCategoryIdMapper.toDto(product))
                .collect(Collectors.toCollection(ArrayList::new)), HttpStatus.OK);

    }
    @GetMapping("/{id}")
    public ResponseEntity<ProductWithCategoryIdDto> getSingleProduct(@PathVariable("id") long id) {
        try {
            MultiValueMap<String, String> headers = new org.springframework.http.HttpHeaders();
            headers.add("Content-Type", "application/json");
            headers.add("Accept", "application/json");

            Product product = productService.getSingleProduct(id);
            //write code to convert Product to ProductWithCategoryIdDto
            ProductWithCategoryIdDto productWithCategoryIdDto = productWithCategoryIdMapper.toDto(product);

            return new ResponseEntity<ProductWithCategoryIdDto>(productWithCategoryIdDto, headers, HttpStatus.OK);
        } catch (Exception e) {
            //return new ResponseEntity<ProductDTO>(new ProductDTO(), HttpStatus.INTERNAL_SERVER_ERROR);
            throw e;
        }
    }
    //Exception handling
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception e) {
        return new ResponseEntity<>("something went wrong", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private ProductDTO getProductDTO(Product product) {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(product.getId());
        productDTO.setTitle(product.getTitle());
        productDTO.setDescription(product.getDescription());
        productDTO.setImage(product.getImage_url());
        productDTO.setPrice(product.getPrice());
        productDTO.setCategory(product.getCategory().getName());
        return productDTO;
    }

    @PostMapping( "/")
    public ResponseEntity<ProductWithCategoryIdDto> createProduct(@RequestBody ProductWithCategoryIdDto productWithCategoryIdDto){
        Product product = productWithCategoryIdMapper.toEntity(productWithCategoryIdDto);
        Product productResponse = productService.createProduct(product);
        ProductWithCategoryIdDto productWithCategoryIdDtoResponse = productWithCategoryIdMapper.toDto(productResponse);
        return new ResponseEntity<>(productWithCategoryIdDtoResponse, HttpStatus.CREATED);
    }

    private Product getProductFromProductDTO(ProductDTO productDTO) {
        Product product = new Product();
        product.setTitle(productDTO.getTitle());
        product.setDescription(productDTO.getDescription());
        product.setImage_url(productDTO.getImage());
        product.setPrice(productDTO.getPrice());
        product.setId(productDTO.getId());
        Category category = categoryService.getCategoryByName(productDTO.getCategory());

        product.setCategory(category);
        return product;
    }

    @PutMapping ("/{id}")
    public ResponseEntity<ProductWithCategoryIdDto> updateProduct(@RequestBody ProductWithCategoryIdDto productWithCategoryIdDto, @PathVariable long id ){
        Product existingProduct = productService.getSingleProduct(id);
        Product product = productWithCategoryIdMapper.partialUpdate(productWithCategoryIdDto, existingProduct);
        Product productResponse = productService.createProduct(product);
        ProductWithCategoryIdDto productWithCategoryIdDtoResponse = productWithCategoryIdMapper.toDto(productResponse);
        return new ResponseEntity<>(productWithCategoryIdDtoResponse, HttpStatus.CREATED);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Product> deleteProduct(@PathVariable long id){
        return new ResponseEntity<>(productService.deleteProduct(id), HttpStatus.OK);
    }

}
