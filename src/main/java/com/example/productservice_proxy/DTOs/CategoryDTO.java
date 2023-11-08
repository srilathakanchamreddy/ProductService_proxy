package com.example.productservice_proxy.DTOs;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter

public class CategoryDTO {
    private long id;
    private String name;
    private String description;
    private List<String> productList;
}
