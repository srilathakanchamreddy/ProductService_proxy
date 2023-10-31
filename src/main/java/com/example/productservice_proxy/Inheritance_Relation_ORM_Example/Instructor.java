package com.example.productservice_proxy.Inheritance_Relation_ORM_Example;

import jakarta.persistence.Entity;

@Entity
public class Instructor extends User{
    private String salary;
    private String subject;
}
