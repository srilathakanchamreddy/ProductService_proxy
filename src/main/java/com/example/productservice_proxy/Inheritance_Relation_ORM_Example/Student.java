package com.example.productservice_proxy.Inheritance_Relation_ORM_Example;

import jakarta.persistence.Entity;

@Entity
public class Student extends User{
    private String roll_no;
    private String class_name;
}
