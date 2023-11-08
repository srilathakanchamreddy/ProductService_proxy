package com.example.productservice_proxy.Repositaries;

import com.example.productservice_proxy.Models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepo extends JpaRepository<Product, Long> {
    Product save(Product product);
    Product getProductById(long id);
    List<Product> findAll() ;
    Product deleteProductById(long id);
    //Product update


}
