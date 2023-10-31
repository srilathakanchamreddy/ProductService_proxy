package com.example.productservice_proxy.Clients.FakeStore.DTO;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class FakeStoreProductDTO implements com.example.productservice_proxy.Clients.IClientDTO{
    private long id;
    private String title;
    private String description;
    private float price;
    private String category;
    private String image;


}
