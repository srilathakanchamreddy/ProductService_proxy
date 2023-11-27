package com.example.productservice_proxy.Services;

import com.example.productservice_proxy.Models.Category;

import java.util.List;

public class FakeStoreCategoryService implements ICategoryService{

    @Override
    public Category getCategoryByName(String category) {
        return null;
    }

    @Override
    public Category getSingleCategory(long id) {
        return null;
    }

    @Override
    public List<Category> getAllCategories() {
        return null;
    }

    @Override
    public Category createCategory(Category category) {
        return null;
    }

    @Override
    public Category deleteCategory(long id) {
        return null;
    }
}
