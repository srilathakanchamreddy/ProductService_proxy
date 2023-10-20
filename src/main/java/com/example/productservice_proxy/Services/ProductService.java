package com.example.productservice_proxy.Services;

import com.example.productservice_proxy.DTOs.ProductDTO;
import com.example.productservice_proxy.Models.Product;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService implements IProductService {
    private RestTemplateBuilder restTemplateBuilder;
    ProductService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplateBuilder = restTemplateBuilder;
    }

    @Override
    public List<Product> getAllProducts() {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<ProductDTO[]> products = restTemplate.getForEntity("https://fakestoreapi.com/products", ProductDTO[].class);
        List<Product> productList= new ArrayList<>();
        for(ProductDTO productDTO: products.getBody()){
            Product product = new Product();
            product.setTitle(productDTO.getTitle());
            product.setDescription(productDTO.getDescription());
            product.setImage_url(productDTO.getImage());
            product.setPrice(productDTO.getPrice());
            productList.add(product);
        }
        return productList;
    }

    @Override
    public Product getSingleProduct(long id) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ProductDTO productDTO = restTemplate.getForEntity("https://fakestoreapi.com/products/{id}", ProductDTO.class,id).getBody();
        Product product = new Product();
        product.setTitle(productDTO.getTitle());
        product.setDescription(productDTO.getDescription());
        product.setImage_url(productDTO.getImage());
        product.setPrice(productDTO.getPrice());
        return product;
    }

    @Override
    public Product createProduct(ProductDTO productDTO) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        Product product = new Product();
        product.setTitle(productDTO.getTitle());
        product.setDescription(productDTO.getDescription());
        product.setImage_url(productDTO.getImage());
        product.setPrice(productDTO.getPrice());
        return restTemplate.postForEntity("https://fakestoreapi.com/products", product, Product.class).getBody();
    }

    @Override
    public Product updateProduct(ProductDTO productDTO, long id) {
        Product productOld = this.getSingleProduct(id);
        ProductDTO productDto = new ProductDTO();
        productDto.setTitle(productOld.getTitle());
        productDto.setDescription(productOld.getDescription());
        productDto.setImage(productOld.getImage_url());
        productDto.setPrice(productOld.getPrice());

        return this.createProduct(productDto);


    }

    @Override
    public Product deleteProduct(long id) {
       return null;
    }
}
