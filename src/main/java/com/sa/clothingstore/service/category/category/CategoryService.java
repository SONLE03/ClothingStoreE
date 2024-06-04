package com.sa.clothingstore.service.category.category;

import com.sa.clothingstore.dto.request.category.CategoryRequest;
import com.sa.clothingstore.dto.response.category.CategoryResponse;
import com.sa.clothingstore.dto.response.product.ProductResponse;
import com.sa.clothingstore.model.category.Category;

import java.util.List;
import java.util.UUID;

public interface CategoryService {
    List<Category> getAllCategory();
    CategoryResponse createCategory(CategoryRequest categoryRequest);
    Category modifyCategory(UUID id, CategoryRequest categoryRequest);
    void deleteCategory(UUID id);
    List<ProductResponse> getAllProductByCategoryId(UUID categoryId);
}
