package com.example.productservice_proxy.Controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping ("/product/categories")
public class CategoryController {
    @GetMapping()
    public String getAllCategories() {
        return "getAllCategories";
    }
    @GetMapping("/{id}")
    public String getSingleCategory(@PathVariable("id") long id) {
        return "getSingleCategory"+ id;
    }
}
