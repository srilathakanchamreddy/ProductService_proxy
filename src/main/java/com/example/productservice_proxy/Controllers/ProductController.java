package com.example.productservice_proxy.Controllers;

import com.example.productservice_proxy.DTOs.ProductDTO;
import com.example.productservice_proxy.Models.Product;
import com.example.productservice_proxy.Services.IProductService;
import jakarta.websocket.server.PathParam;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    private IProductService productService;
    ProductController(IProductService productService){
        this.productService=productService;
    }
    @GetMapping("/")
    public ResponseEntity<List<Product>> getAllProducts(){
        return new ResponseEntity<List<Product>>(productService.getAllProducts(), HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Product> getSingleProduct(@PathVariable("id") long id) {
        MultiValueMap<String, String> headers = new org.springframework.http.HttpHeaders();
        headers.add("Content-Type", "application/json");
        headers.add("Accept", "application/json");

        Product product = productService.getSingleProduct(id);
        return new ResponseEntity<Product>(product, headers, HttpStatus.OK);
    }
    @PostMapping( "/")
    public ResponseEntity<Product> createProduct(@RequestBody ProductDTO productDTO){
        Product product = productService.createProduct(productDTO);
        return new ResponseEntity<Product>(product, HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@RequestBody ProductDTO productDTO, @PathVariable long id ){
        Product product = productService.getSingleProduct(id);
        product.setTitle(productDTO.getTitle());
        product.setDescription(productDTO.getDescription());
        product.setImage_url(productDTO.getImage());
        product.setPrice(productDTO.getPrice());
        return this.createProduct(productDTO);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Product> deleteProduct(@PathVariable long id){
        return new ResponseEntity<>(productService.deleteProduct(id), HttpStatus.OK);
    }

}
