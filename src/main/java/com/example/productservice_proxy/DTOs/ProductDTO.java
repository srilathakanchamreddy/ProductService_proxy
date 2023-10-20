package com.example.productservice_proxy.DTOs;

import com.example.productservice_proxy.Models.Categories;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ProductDTO {
    private String title;
    private String description;
    private String image;
    private double price;
    private String category;
    private RatingDTO rating;


}
