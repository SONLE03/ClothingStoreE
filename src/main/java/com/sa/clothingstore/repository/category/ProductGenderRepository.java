package com.sa.clothingstore.repository.category;

import com.sa.clothingstore.model.category.Branch;
import com.sa.clothingstore.model.category.ProductGender;
import com.sa.clothingstore.model.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProductGenderRepository extends JpaRepository<ProductGender, UUID> {
    Optional<ProductGender> findByName(String name);

    @Query("SELECT p FROM ProductGender p WHERE LOWER(p.name) LIKE LOWER(CONCAT('%', ?1, '%'))")
    List<ProductGender> searchProductGender(String keyword);
}
