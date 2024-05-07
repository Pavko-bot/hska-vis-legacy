package com.hka.iwi.categorymanagement;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import com.fasterxml.jackson.databind.JsonNode;

@Component
public class CategoryServiceRequests {

    @Value("${services.product_endpoint}")
    private String productEndpoint;

    private String productsAPI = "/products";
    private String productAPI = "/product/";

    public void deleteProductsByCategoryId(Integer categoryId) {
        RestTemplate r = new RestTemplate();
        JsonNode response = r.getForObject(productEndpoint + productsAPI, JsonNode.class);

        if (response != null && response.isArray()) {
            for (JsonNode product : response) {
                Integer productCategoryId = product.path("categoryId").asInt();
                if (productCategoryId.equals(categoryId)) {
                    Integer productId = product.path("id").asInt();
                    r.delete(productEndpoint + "/product/" + productId);
                }
            }
        }
    }
}
