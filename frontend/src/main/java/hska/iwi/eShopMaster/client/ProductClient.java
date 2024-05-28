package hska.iwi.eShopMaster.client;

import feign.Headers;
import feign.Param;
import feign.RequestLine;

import java.util.List;

import hska.iwi.eShopMaster.model.database.dataobjects.Product;

public interface ProductClient {
        @RequestLine("GET /products")
        List<Product> getProducts();

        @RequestLine("GET /product/{id}")
        Product getProductById(@Param("id") int id);

        @RequestLine("GET /product?name={name}")
        Product getProductByName(@Param("name") String name);

        @RequestLine("POST /product?name={name}&price={price}&categoryId={categoryId}&details={details}")
        @Headers("Content-Type: application/json")
        int addProduct(@Param("name") String name, @Param("price") double price, @Param("categoryId") int categoryId,
                        @Param("details") String details);

        @RequestLine("GET /products/search?searchValue={searchValue}&searchMinPrice={searchMinPrice}&searchMaxPrice={searchMaxPrice}")
        List<Product> getProductsForSearchValues(@Param("searchValue") String searchValue,
                        @Param("searchMinPrice") Double searchMinPrice, @Param("searchMaxPrice") Double searchMaxPrice);

        @RequestLine("DELETE /products?categoryId={categoryId}")
        boolean deleteProductsByCategoryId(@Param("categoryId") int categoryId);

        @RequestLine("DELETE /product/{id}")
        void deleteProductById(@Param("id") int id);
}