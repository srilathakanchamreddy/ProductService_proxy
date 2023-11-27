package com.example.productservice_proxy.DTOs;

import lombok.*;

import java.io.Serializable;
import java.util.Date;

/**
 * DTO for {@link com.example.productservice_proxy.Models.Product}
 */

@Getter
@Setter
@AllArgsConstructor
public class ProductDto implements Serializable {
    long id;

    String title;
    String description;
    String image_url;
    double price;
   String category;

    public ProductDto() {

    }
}