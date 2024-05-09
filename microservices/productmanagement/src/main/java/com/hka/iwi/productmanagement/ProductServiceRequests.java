package com.hka.iwi.productmanagement;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;

@Component
public class ProductServiceRequests {

    private String categoryAPI = "http://category:8080/category/";

    public Boolean CategoryExists(Integer categoryId) {
        try {
            RestTemplate r = new RestTemplate();
            r.getForObject(categoryAPI + categoryId, String.class);

            return true;
        } catch (HttpClientErrorException | HttpServerErrorException httpClientOrServerExc) {
            return false;
        }
    }
}
