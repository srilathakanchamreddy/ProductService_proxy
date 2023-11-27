package com.example.productservice_proxy.Services;

import com.example.productservice_proxy.Clients.FakeStore.Client.FakeStoreClient;
import com.example.productservice_proxy.Clients.FakeStore.DTO.FakeStoreProductDTO;
import com.example.productservice_proxy.Clients.IClientDTO;
import com.example.productservice_proxy.Models.Category;
import com.example.productservice_proxy.Models.Product;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


public class FakeStoreProductService implements IProductService {
    private RestTemplateBuilder restTemplateBuilder;
    private FakeStoreClient fakeStoreClient;
    FakeStoreProductService(RestTemplateBuilder restTemplateBuilder, FakeStoreClient fakeStoreClient){
        this.restTemplateBuilder = restTemplateBuilder;
        this.fakeStoreClient = fakeStoreClient;
    }

    @Override
    public List<Product> getAllProducts() {

        IClientDTO[] products = fakeStoreClient.getAllProducts();
        return getProductList(products);
    }

    private List<Product> getProductList(IClientDTO[] iClientDTOs) {
        List<Product> productList= new ArrayList<>();
        for(IClientDTO iClientDTO: iClientDTOs){
            Product product = getProduct(iClientDTO);
            productList.add(product);
        }
        return productList;
    }

    @Override
    public Product getSingleProduct(long id) {

        return getProduct(fakeStoreClient.getSingleProduct(id));
    }

    private Product getProduct(IClientDTO iClientDTO) {
        Product product = new Product();
        FakeStoreProductDTO productDTO = (FakeStoreProductDTO) iClientDTO;
        product.setId(productDTO.getId());
        product.setTitle(productDTO.getTitle());
        product.setDescription(productDTO.getDescription());
        product.setImage_url(productDTO.getImage());
        product.setPrice(productDTO.getPrice());
        Category category = new Category();
        category.setName(productDTO.getCategory());
        product.setCategory(category);
        return product;
    }

    @Override
    public Product createProduct(Product product) {
        FakeStoreProductDTO fakeStoreProductDTO = getFakeStoreProductDTO(product);
        FakeStoreProductDTO fakeStoreProductDTO1 = fakeStoreClient.createProduct(fakeStoreProductDTO);
        return getProduct(fakeStoreProductDTO1);
    }

    private FakeStoreProductDTO getFakeStoreProductDTO(Product product) {
        FakeStoreProductDTO fakeStoreProductDTO = new FakeStoreProductDTO();
        fakeStoreProductDTO.setId(product.getId());
        fakeStoreProductDTO.setTitle(product.getTitle());
        fakeStoreProductDTO.setDescription(product.getDescription());
        fakeStoreProductDTO.setImage(product.getImage_url());
        fakeStoreProductDTO.setPrice((float) product.getPrice());
        return fakeStoreProductDTO;
    }

    @Override
    public Product updateProduct(Product product, long id) {
        FakeStoreProductDTO fakeStoreProductDTO = getFakeStoreProductDTO(product);
        FakeStoreProductDTO fakeStoreProductDTO1 = fakeStoreClient.updateProduct(fakeStoreProductDTO, id);
        return getProduct(fakeStoreProductDTO1);


    }

    @Override
    public Product deleteProduct(long id) {
       return null;
    }
}
