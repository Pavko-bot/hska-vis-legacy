package com.hka.iwi.productmanagement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;

@RestController // This means that this class is a RestController
public class ProductmanagementController {

    @Autowired // This means to get the bean called userRepository which is auto-generated
    private ProductService productService;

    @GetMapping(value = "/products")
    public List<Product> getProducts() {
        // TODO: call service
    }

    @GetMapping(value = "/product", params = { "id" })
    public Product getProductById(
            @RequestParam(required = true, name = "id") int id) {
        // TODO: call service
    }

    @GetMapping(value = "/product", params = { "name" })
    public Product getProductByName(
            @RequestParam(required = true, name = "name") String name) {
        // TODO: call service
    }

    @PostMapping(value = "/product/add", params = { "name", "price", "categoryId", "details" })
    public int addProduct(
            @RequestParam(required = true, name = "name") String name,
            @RequestParam(required = true, name = "price") double price,
            @RequestParam(required = true, name = "categoryId") int categoryId,
            @RequestParam(required = false, name = "details") String details) {
        // TODO: call service
    }

    @GetMapping(value = "/products/search", params = { "searchValue", "searchMinPrice", "searchMaxPrice" })
    public List<Product> getProductsForSearchValues(
            @RequestParam(required = false, name = "searchValue") String searchValue,
            @RequestParam(required = false, name = "searchMinPrice") Double searchMinPrice,
            @RequestParam(required = false, name = "searchMaxPrice") Double searchMaxPrice) {
        // TODO: call service
    }

    @DeleteMapping(value = "/products/delete", params = { "categoryId" })
    public boolean deleteProductsByCategoryId(
            @RequestParam(required = true, name = "categoryId") int categoryId) {
        // TODO: call service
    }

    @DeleteMapping(value = "/product/delete", params = { "id" })
    public void deleteProductById(
            @RequestParam(required = true, name = "id") int id) {
        // TODO: call service
    }

}
