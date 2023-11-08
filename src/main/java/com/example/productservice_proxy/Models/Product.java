package com.example.productservice_proxy.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Product extends BaseModel{
    private String title;
    private String description;
    private String image_url;
    private double price;
    @ManyToOne(fetch = jakarta.persistence.FetchType.LAZY, cascade = jakarta.persistence.CascadeType.ALL)

    private Category category;
}
