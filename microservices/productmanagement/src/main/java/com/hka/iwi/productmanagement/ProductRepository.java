package com.hka.iwi.productmanagement;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

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
                        @Param("searchDescription") String searchDescription,
                        @Param("searchMinPrice") Double searchMinPrice,
                        @Param("searchMaxPrice") Double searchMaxPrice);

}