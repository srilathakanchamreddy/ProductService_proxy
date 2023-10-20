package com.example.productservice_proxy.DTOs;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class RatingDTO {
    private double rate;
    private int count;
}
