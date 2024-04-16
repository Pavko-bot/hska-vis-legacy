package com.hka.iwi.productmanagement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;

import com.hka.iwi.productmanagement.ProductmanagementService;

import java.util.List;

@RestController
public class ProductmanagementController {

    @Autowired
    private ProductmanagementService productmanagementService;

    @GetMapping(value = "/products")
    public List<Product> getProducts() {
        return productmanagementService.getProducts();
    }

    @GetMapping(value = "/product/{id}")
    public Product getProductById(@PathVariable int id) {
        return productmanagementService.getProductById(id);
    }

    @GetMapping(value = "/product", params = { "name" })
    public Product getProductByName(
            @RequestParam(required = true, name = "name") String name) {
        return productmanagementService.getProductByName(name);
    }

    @PostMapping(value = "/product", params = { "name", "price", "categoryId" })
    public int addProduct(
            @RequestParam(required = true, name = "name") String name,
            @RequestParam(required = true, name = "price") double price,
            @RequestParam(required = true, name = "categoryId") int categoryId,
            @RequestParam(required = false, name = "details") String details) {
        return productmanagementService.addProduct(name, price, categoryId, details);
    }

    @GetMapping(value = "/products/search")
    public List<Product> getProductsForSearchValues(
            @RequestParam(required = false, name = "searchValue") String searchValue,
            @RequestParam(required = false, name = "searchMinPrice") Double searchMinPrice,
            @RequestParam(required = false, name = "searchMaxPrice") Double searchMaxPrice) {
        return productmanagementService.getProductsForSearchValues(searchValue, searchMinPrice, searchMaxPrice);
    }

    @DeleteMapping(value = "/products", params = { "categoryId" })
    public boolean deleteProductsByCategoryId(
            @RequestParam(required = true, name = "categoryId") int categoryId) {
        return productmanagementService.deleteProductsByCategoryId(categoryId);
    }

    @DeleteMapping(value = "/product/{id}")
    public void deleteProductById(@PathVariable int id) {
        productmanagementService.deleteProductById(id);
    }

}
