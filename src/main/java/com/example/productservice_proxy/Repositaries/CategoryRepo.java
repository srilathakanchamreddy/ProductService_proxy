package com.example.productservice_proxy.Repositaries;

import com.example.productservice_proxy.Models.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface CategoryRepo extends JpaRepository<Category, Long> {
    Category save(Category category);

    @Override
    List<Category> findAll();
    Category findByName(String name);
    Category findById(long id);

    Category deleteById(long id);
}
