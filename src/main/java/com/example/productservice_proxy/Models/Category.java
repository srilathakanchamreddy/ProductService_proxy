package com.example.productservice_proxy.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
@Entity
public class Category extends BaseModel{
    private String name;
    private String description;
    @OneToMany(mappedBy = "category" , cascade = {jakarta.persistence.CascadeType.ALL})
    List<Product> productList;
}
