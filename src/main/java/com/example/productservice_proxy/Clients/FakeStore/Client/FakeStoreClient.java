package com.example.productservice_proxy.Clients.FakeStore.Client;

import com.example.productservice_proxy.Clients.FakeStore.DTO.FakeStoreProductDTO;
import com.example.productservice_proxy.Clients.IClientDTO;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class FakeStoreClient {
    private RestTemplateBuilder restTemplateBuilder;
    public FakeStoreClient(RestTemplateBuilder restTemplateBuilder){
        this.restTemplateBuilder = restTemplateBuilder;
    }
    public IClientDTO getSingleProduct(long id) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        return restTemplate.getForEntity("https://fakestoreapi.com/products/{id}", FakeStoreProductDTO.class,id).getBody();
    }

    public IClientDTO[] getAllProducts() {
        RestTemplate restTemplate = restTemplateBuilder.build();
        return restTemplate.getForEntity("https://fakestoreapi.com/products", FakeStoreProductDTO[].class).getBody();



    }

    public FakeStoreProductDTO createProduct(FakeStoreProductDTO product) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        return restTemplate.postForEntity("https://fakestoreapi.com/products", product, FakeStoreProductDTO.class).getBody();
    }

    public FakeStoreProductDTO updateProduct(FakeStoreProductDTO fakeStoreProductDTO, long id) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        //patchForObject is not supported by fakestoreapi.
        //write a custom method(like requestForEntity()) to update the product.
        String url = "https://fakestoreapi.com/products/" + id; // Replace with the actual URL of your API

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        // Create a HttpEntity with the updated product data and headers
        HttpEntity<FakeStoreProductDTO> requestEntity = new HttpEntity<>(fakeStoreProductDTO, headers);


            // Send a PATCH request to update the product
            ResponseEntity<FakeStoreProductDTO> responseEntity = restTemplate.exchange(
                    url,
                    HttpMethod.PATCH,
                    requestEntity,
                    FakeStoreProductDTO.class
            );
            return responseEntity.getBody();


    }
}
