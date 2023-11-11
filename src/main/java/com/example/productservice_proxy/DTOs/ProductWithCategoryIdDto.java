package com.example.productservice_proxy.DTOs;

import lombok.Getter;
import lombok.Setter;
import lombok.Value;

import java.io.Serializable;
import java.util.Date;

/**
 * DTO for {@link com.example.productservice_proxy.Models.Product}
 */

@Getter
@Setter
public class ProductWithCategoryIdDto implements Serializable {
    long id;
    Date created_at;
    Date last_updated_at;
    boolean is_deleted;
    String title;
    String description;
    String image_url;
    double price;
    long category_id;
}