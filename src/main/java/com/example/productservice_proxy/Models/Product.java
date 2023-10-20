package com.example.productservice_proxy.Models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Product extends BaseModel{
    private String title;
    private String description;
    private String image_url;
    private double price;
    private Categories category;
}
