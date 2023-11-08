package com.example.productservice_proxy.Inheritance_Relation_ORM_Example;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class User {
    @Id
    private long id;
    private String name;
    private String email;
}
