package com.hka.iwi.productmanagement;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;

@Component
public class ProductServiceRequests {

    @Value("${services.category_endpoint}")
    private String categoryEndpoint;

    private String categoryAPI = "/category/";

    public Boolean CategoryExists(Integer categoryId) {
        try {
            RestTemplate r = new RestTemplate();
            r.getForObject(categoryEndpoint + categoryAPI + categoryId, String.class);

            return true;
        } catch (HttpClientErrorException | HttpServerErrorException httpClientOrServerExc) {
            return false;
        }
    }
}
