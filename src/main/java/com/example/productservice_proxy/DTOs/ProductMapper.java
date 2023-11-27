package com.example.productservice_proxy.DTOs;

import com.example.productservice_proxy.Models.Category;
import com.example.productservice_proxy.Models.Product;
import lombok.Builder;
import org.mapstruct.*;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface ProductMapper {
    @Mapping(target = "category", expression = "java(mapCategory(productDto))")
    Product toEntity(ProductDto productDto);

    default com.example.productservice_proxy.Models.Category mapCategory(ProductDto productDto) {
        Category category = null;
        if (productDto.getCategory() != null) {
            category = new Category();
            category.setName(productDto.getCategory());
        }
        return category;
    }

    //default method to map category for partial update
    default com.example.productservice_proxy.Models.Category mapCategoryForPartialUpdate(ProductDto productDto, Product product) {
       if(productDto.getCategory() != null){
           Category category = new Category();
           category.setName(productDto.getCategory());
           return category;
       }
       else return product.getCategory();
    }


    @Mapping(target = "category", expression = "java(product.getCategory().getName())")
    ProductDto toDto(Product product);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "category", expression = "java(mapCategoryForPartialUpdate(productDto, product))")
    @Mapping(target = "title", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "description", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "image_url", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "price", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Product partialUpdate(ProductDto productDto, @MappingTarget Product product);
    default List<ProductDto> toDtoList(List<Product> products){
        return products.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }
    default List<Product> toEntityList(List<ProductDto> productDtos){
        return productDtos.stream()
                .map(this::toEntity)
                .collect(Collectors.toList());
    }

}