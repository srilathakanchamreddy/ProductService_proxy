package com.example.productservice_proxy.DTOs;

import com.example.productservice_proxy.Models.Product;
import com.example.productservice_proxy.Repositaries.CategoryRepo;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;


@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public abstract class ProductWithCategoryIdMapper {
    @Autowired
    CategoryRepo categoryRepo;

    //write annotation to map category id to category object
    @Mapping(target = "category", expression = "java(categoryRepo.findById(productWithCategoryIdDto.getCategory_id()))")
    public abstract Product toEntity(ProductWithCategoryIdDto productWithCategoryIdDto);
    //write annotation to map category object to category id
   @Mapping(target = "category_id", expression = "java(product.getCategory().getId())")
    public abstract ProductWithCategoryIdDto toDto(Product product);
//write annotation to map category id to category object
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "category", expression = "java(categoryRepo.findById(productWithCategoryIdDto.getCategory_id()))")
    public abstract Product partialUpdate(ProductWithCategoryIdDto productWithCategoryIdDto, @MappingTarget Product product);
}