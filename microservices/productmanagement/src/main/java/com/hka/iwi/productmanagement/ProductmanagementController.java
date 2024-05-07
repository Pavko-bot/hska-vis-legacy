package com.hka.iwi.productmanagement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity getProducts() {
        try {
            List<Product> products = productmanagementService.getProducts();
            return ResponseEntity.ok(products);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        }
    }

    @GetMapping(value = "/product/{id}")
    public ResponseEntity getProductById(@PathVariable int id) {
        try {
            Product product = productmanagementService.getProductById(id);
            return ResponseEntity.ok(product);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        }

    }

    @GetMapping(value = "/product", params = { "name" })
    public ResponseEntity getProductByName(
            @RequestParam(required = true, name = "name") String name) {
        try {
            Product product = productmanagementService.getProductByName(name);
            return ResponseEntity.ok(product);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        }
    }

    @PostMapping(value = "/product", params = { "name", "price", "categoryId" })
    public ResponseEntity addProduct(
            @RequestParam(required = true, name = "name") String name,
            @RequestParam(required = true, name = "price") double price,
            @RequestParam(required = true, name = "categoryId") int categoryId,
            @RequestParam(required = false, name = "details") String details) {
        try {
            Integer productId = productmanagementService.addProduct(name, price, categoryId, details);
            return ResponseEntity.ok(productId);
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }

    @GetMapping(value = "/products/search")
    public ResponseEntity getProductsForSearchValues(
            @RequestParam(required = false, name = "searchValue") String searchValue,
            @RequestParam(required = false, name = "searchMinPrice") Double searchMinPrice,
            @RequestParam(required = false, name = "searchMaxPrice") Double searchMaxPrice) {
        try {
            List<Product> products = productmanagementService.getProductsForSearchValues(searchValue, searchMinPrice,
                    searchMaxPrice);
            return ResponseEntity.ok(products);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        }
    }

    @DeleteMapping(value = "/products", params = { "categoryId" })
    public ResponseEntity deleteProductsByCategoryId(
            @RequestParam(required = true, name = "categoryId") int categoryId) {
        try {
            Boolean bool = productmanagementService.deleteProductsByCategoryId(categoryId);
            return ResponseEntity.ok(bool);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        }
    }

    @DeleteMapping(value = "/product/{id}")
    public ResponseEntity deleteProductById(@PathVariable int id) {
        try {
            productmanagementService.deleteProductById(id);
            return ResponseEntity.ok("Product deleted successfully");
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        }
    }

}
