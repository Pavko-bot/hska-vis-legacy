package com.hka.iwi.categorymanagement;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import com.fasterxml.jackson.databind.JsonNode;

@Component
public class CategoryServiceRequests {

    private String productsAPI = "http://product:8080/products";
    private String productAPI = "http://product:8080/product/";

    public void deleteProductsByCategoryId(Integer categoryId) {
        RestTemplate r = new RestTemplate();
        JsonNode response = r.getForObject(productsAPI, JsonNode.class);

        if (response != null && response.isArray()) {
            for (JsonNode product : response) {
                Integer productCategoryId = product.path("categoryId").asInt();
                if (productCategoryId.equals(categoryId)) {
                    Integer productId = product.path("id").asInt();
                    r.delete(productAPI + productId);
                }
            }
        }
    }
}
