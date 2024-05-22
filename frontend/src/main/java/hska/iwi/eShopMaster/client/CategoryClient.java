package hska.iwi.eShopMaster.client;

import feign.Headers;
import feign.Param;
import feign.RequestLine;

import java.util.List;
import java.util.Map;

import hska.iwi.eShopMaster.model.database.dataobjects.Category;

public interface CategoryClient {
    @RequestLine("GET /categories")
    List<Category> getCategories();

    @RequestLine("GET /category/{id}")
    Category getCategoryById(@Param("id") int id);

    @RequestLine("GET /category?name={name}")
    Category getCategoryByName(@Param("name") String name);

    @RequestLine("POST /category")
    @Headers("Content-Type: application/json")
    void addCategory(Map<String, Object> requestBody);

    @RequestLine("DELETE /category")
    @Headers("Content-Type: application/json")
    void deleteCategory(Category category);

    @RequestLine("DELETE /category/{id}")
    void deleteCategoryById(@Param("id") int id);
}