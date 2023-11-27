package com.example.productservice_proxy.Controllers;

import com.example.productservice_proxy.DTOs.ProductDto;
import com.example.productservice_proxy.DTOs.ProductMapper;
import com.example.productservice_proxy.Exceptions.CategoryNotPresentException;
import com.example.productservice_proxy.Exceptions.ProductNotFoundException;
import com.example.productservice_proxy.Models.Product;
import com.example.productservice_proxy.Services.IProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final IProductService productService;


    private final ProductMapper productMapper;
    ProductController(IProductService productService, ProductMapper productMapper){
        this.productService =productService;

        this.productMapper = productMapper;
    }
    @GetMapping("/")
    public ResponseEntity<List<ProductDto>> getAllProducts(){
        List<Product> products = productService.getAllProducts();
        //write code to convert List<Product> to  List<ProductDto> using streams
        return new ResponseEntity<>(productMapper.toDtoList(products), HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> getSingleProduct(@PathVariable("id") long id) {

            MultiValueMap<String, String> headers = new org.springframework.http.HttpHeaders();
            headers.add("Content-Type", "application/json");
            headers.add("Accept", "application/json");

            Product product = productService.getSingleProduct(id);
            if(product == null){
                throw new ProductNotFoundException("Product not found");
            }
            //write code to convert Product to ProductDto
            ProductDto productDto = productMapper.toDto(product);

            return new ResponseEntity<>(productDto, headers, HttpStatus.OK);

    }




    @PostMapping( "/")
    @Transactional
    public ResponseEntity<ProductDto> createProduct(@RequestBody ProductDto productDto){
        Product product = productMapper.toEntity(productDto);
        if(product.getCategory() == null){
            throw new CategoryNotPresentException("category is required");
        }
        Product productResponse = productService.createProduct(product);
        ProductDto productDtoResponse = productMapper.toDto(productResponse);
        return new ResponseEntity<>(productDtoResponse, HttpStatus.CREATED);
    }


    @Transactional
    @PutMapping ("/{id}")
    //here we will update the whole product
    public ResponseEntity<ProductDto> updateProduct(@RequestBody ProductDto productDto, @PathVariable long id ){
        //Product existingProduct = productService.getSingleProduct(id);
        Product product = productMapper.toEntity(productDto);
        if(product.getCategory() == null){
            throw new CategoryNotPresentException("category is required");
        }
        Product productResponse = productService.createProduct(product);
        ProductDto productDtoResponse = productMapper.toDto(productResponse);
        return new ResponseEntity<>(productDtoResponse, HttpStatus.CREATED);
    }
    @Transactional
    @PatchMapping ("/{id}")
    //here we will partially update the product
    public ResponseEntity<ProductDto> partialUpdateProduct(@RequestBody ProductDto productDto, @PathVariable long id ){
        Product existingProduct = productService.getSingleProduct(id);
        Product product = productMapper.partialUpdate(productDto, existingProduct);

        Product productResponse = productService.createProduct(product);
        ProductDto productDtoResponse = productMapper.toDto(productResponse);
        return new ResponseEntity<>(productDtoResponse, HttpStatus.CREATED);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Product> deleteProduct(@PathVariable long id){
        return new ResponseEntity<>(productService.deleteProduct(id), HttpStatus.OK);
    }

}
