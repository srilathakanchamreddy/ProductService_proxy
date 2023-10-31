package com.example.productservice_proxy.Controllers;

import com.example.productservice_proxy.DTOs.ProductDTO;
import com.example.productservice_proxy.Models.Category;
import com.example.productservice_proxy.Models.Product;
import com.example.productservice_proxy.Services.IProductService;
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
        this.productService =productService;
    }
    @GetMapping("/")
    public ResponseEntity<List<Product>> getAllProducts(){
        return new ResponseEntity<List<Product>>(productService.getAllProducts(), HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> getSingleProduct(@PathVariable("id") long id) {
        try {
            MultiValueMap<String, String> headers = new org.springframework.http.HttpHeaders();
            headers.add("Content-Type", "application/json");
            headers.add("Accept", "application/json");
            if (id <= 0) {
                return new ResponseEntity<ProductDTO>(new ProductDTO(), headers, HttpStatus.OK);
            }
            Product product = productService.getSingleProduct(id);
            ProductDTO productDTO = getProductDTO(product);

            return new ResponseEntity<ProductDTO>(productDTO, headers, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<ProductDTO>(new ProductDTO(), HttpStatus.INTERNAL_SERVER_ERROR);
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
    public ResponseEntity<ProductDTO> createProduct(@RequestBody ProductDTO productDTO){
        Product product = getProductFromProductDTO(productDTO);
        Product productResponse = productService.createProduct(product);
        ProductDTO productDTOResponse = getProductDTO(productResponse);
        return new ResponseEntity<ProductDTO>(productDTOResponse, HttpStatus.CREATED);
    }

    private Product getProductFromProductDTO(ProductDTO productDTO) {
        Product product = new Product();
        product.setTitle(productDTO.getTitle());
        product.setDescription(productDTO.getDescription());
        product.setImage_url(productDTO.getImage());
        product.setPrice(productDTO.getPrice());
        product.setId(productDTO.getId());
        Category category = new Category();
        category.setName(productDTO.getCategory());
        product.setCategory(category);
        return product;
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ProductDTO> updateProduct(@RequestBody ProductDTO productDTO, @PathVariable long id ){
        Product product = getProductFromProductDTO(productDTO);
        Product productResponse = productService.updateProduct(product, id);
        ProductDTO productDTOResponse = getProductDTO(productResponse);
        return new ResponseEntity<ProductDTO>(productDTOResponse, HttpStatus.CREATED);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Product> deleteProduct(@PathVariable long id){
        return new ResponseEntity<>(productService.deleteProduct(id), HttpStatus.OK);
    }

}
