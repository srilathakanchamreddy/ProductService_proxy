package com.example.productservice_proxy.Services;

import com.example.productservice_proxy.DTOs.CategoryDTO;
import com.example.productservice_proxy.Models.Category;
import com.example.productservice_proxy.Repositaries.CategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SelfCategoryService implements com.example.productservice_proxy.Services.ICategoryService{
    private final CategoryRepo categoryRepo;

    public SelfCategoryService(CategoryRepo categoryRepo) {
        this.categoryRepo = categoryRepo;
    }

    @Override
    public Category getCategoryByName(String category) {
        Category fetchedCategory =  categoryRepo.findByName(category);
        if(fetchedCategory == null){
            fetchedCategory = new Category();
            fetchedCategory.setName(category);
            categoryRepo.save(fetchedCategory);
        }
        return fetchedCategory;
    }

    @Override
    public Category getSingleCategory(long id) {
        return categoryRepo.findById(id);
    }
    public Category createCategory(Category category) {
        return categoryRepo.save(category);

    }

    @Override
    public CategoryDTO deleteCategory(long id) {
        return categoryRepo.deleteById(id);
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryRepo.findAll();
    }
}
