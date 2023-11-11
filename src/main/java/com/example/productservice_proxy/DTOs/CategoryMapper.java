package com.example.productservice_proxy.DTOs;

import com.example.productservice_proxy.Models.Category;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface CategoryMapper {
    Category toEntity(CategoryWithProductListDto categoryWithProductListDto);

    CategoryWithProductListDto toDto(Category category);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Category partialUpdate(CategoryWithProductListDto categoryWithProductListDto, @MappingTarget Category category);
}