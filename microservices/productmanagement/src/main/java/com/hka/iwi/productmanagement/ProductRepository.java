package com.hka.iwi.productmanagement;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hka.iwi.productmanagement.Product;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

        @Query("SELECT p FROM Product p WHERE " +
                        "(:searchDescription IS NULL OR p.details LIKE %:searchDescription%) " +
                        "AND (:searchMinPrice IS NULL OR p.price >= :searchMinPrice) " +
                        "AND (:searchMaxPrice IS NULL OR p.price <= :searchMaxPrice)")
        List<Product> getProductsForSearchValues(String searchDescription, Double searchMinPrice,
                        Double searchMaxPrice);

        @Query("SELECT p FROM Product p WHERE name = :name")
        Product findByName(String name);

}