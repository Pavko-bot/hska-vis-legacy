package com.hka.iwi.productmanagement;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import com.hka.iwi.productmanagement.Product;

import java.util.List;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

// TODO: check if the repository suffices
@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

        @Query("SELECT p FROM Product p WHERE " +
                        "(:searchDescription IS NULL OR p.description LIKE %:searchDescription%) " +
                        "AND (:searchMinPrice IS NULL OR p.price >= :searchMinPrice) " +
                        "AND (:searchMaxPrice IS NULL OR p.price <= :searchMaxPrice)")
        List<Product> getProductsForSearchValues(
                        @RequestParam("searchDescription") String searchDescription,
                        @RequestParam("searchMinPrice") Double searchMinPrice,
                        @RequestParam("searchMaxPrice") Double searchMaxPrice);

        @Query("SELECT p FROM Product p WHERE name = :name LIMIT 1")
        Product findByName(@RequestParam("name") String name);

}