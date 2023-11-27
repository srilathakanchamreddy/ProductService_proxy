package com.example.productservice_proxy.Services;

import com.example.productservice_proxy.Models.Category;

import java.util.List;

public interface ICategoryService {

    Category getCategoryByName(String category);

    Category getSingleCategory(long id);

    List<Category> getAllCategories();

    Category createCategory(Category category);

    Category deleteCategory(long id);
}
