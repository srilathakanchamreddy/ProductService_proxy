package com.example.productservice_proxy.DTOs;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * DTO for {@link com.example.productservice_proxy.Models.Category}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class CategoryWithProductListDto implements Serializable {
    private long id;
    private Date created_at;
    private Date last_updated_at;
    private boolean is_deleted;
    private String name;
    private String description;
    private List<ProductDto> productList;


    /**
     * DTO for {@link com.example.productservice_proxy.Models.Product}
     */
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class ProductDto implements Serializable {
        private long id;
        private String title;
        private String description;
        private double price;
    }
}