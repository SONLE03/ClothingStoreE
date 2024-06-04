package com.sa.clothingstore.repository.product;

import com.sa.clothingstore.dto.response.product.ProductResponse;
import com.sa.clothingstore.model.product.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface ProductRepository extends JpaRepository<Product, UUID> {
    @Query("SELECT p.id, p.product_Name, p.price, p.category.name, p.branch.name, p.description, p.productStatus, MIN(i.url) " +
            "FROM Product p " +
            "JOIN Image i ON p.id = i.product.id " +
            "WHERE p.productStatus != 'DELETED' " +
            "GROUP BY p.id " +
            "ORDER BY p.createdAt DESC")
    List<Object[]> getAllProduct();


    @Query("SELECT p FROM Product p where p.id = ?1")
    Product getProductById(UUID productId);

    @Query("SELECT p.id, p.product_Name, p.price, p.category.name, p.branch.name, p.description, p.productStatus  FROM Product p WHERE p.category.id = :categoryId AND p.productStatus != 'DELETED' ORDER BY p.createdAt DESC")
    List<Object[]> getAllProductByCategoryId(@Param("categoryId") UUID categoryId);

}
