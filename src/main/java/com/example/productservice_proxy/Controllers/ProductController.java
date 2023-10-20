package com.example.productservice_proxy.Controllers;

import com.example.productservice_proxy.DTOs.ProductDTO;
import jakarta.websocket.server.PathParam;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
public class ProductController {
    @GetMapping("/")
    public String getAllProducts(){
        return "All Products";
    }
    @GetMapping("/{id}")
    public String getSingleProduct(@PathVariable("id") long id){
        return "getting product with id: "+id;
    }
    @PostMapping()
    public String createProduct(@RequestBody ProductDTO productDTO){
        return " creating product"+productDTO.toString();
    }
    @PutMapping("/{id}")
    public String updateProduct(@RequestBody ProductDTO productDTO, @PathVariable long id ){
        return "updating product"+ id +productDTO.toString();
    }
    @DeleteMapping("/{id}")
    public String deleteProduct(@PathVariable long id){
        return "deleting product with id: "+id;
    }

}
