package com.example.productservice_proxy.Models;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
public class Categories extends BaseModel{
    private String name;
    private String description;
    List<Product> productList;
}
