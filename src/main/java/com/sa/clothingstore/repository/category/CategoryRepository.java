package com.sa.clothingstore.repository.category;

import com.sa.clothingstore.dto.response.category.CategoryResponse;
import com.sa.clothingstore.model.category.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;
@Repository
public interface CategoryRepository extends JpaRepository<Category, UUID> {

    @Query("Select c from Category c where c.id = ?1")
    Category getCategoryById(UUID categoryId);

    @Query("SELECT new com.sa.clothingstore.dto.response.category.CategoryResponse(c.id, c.name, c.productGender.name) " +
            "FROM Category c WHERE LOWER(c.name ) LIKE LOWER(CONCAT('%', ?1, '%'))")
    List<CategoryResponse> searchCategory(String keyword);
}
