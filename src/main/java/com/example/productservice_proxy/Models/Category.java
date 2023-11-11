package com.example.productservice_proxy.Models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.List;
@Getter
@Setter
@Entity
public class Category extends BaseModel{
    private String name;
    private String description;
    @OneToMany(mappedBy = "category" , cascade = {jakarta.persistence.CascadeType.ALL}, fetch = jakarta.persistence.FetchType.LAZY)
    List<Product> productList;



    @Column(name = "image_url")
    private String imageUrl;

}
