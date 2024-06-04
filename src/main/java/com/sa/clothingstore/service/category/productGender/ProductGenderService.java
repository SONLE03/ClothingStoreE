package com.sa.clothingstore.service.category.productGender;

import com.sa.clothingstore.dto.request.category.ProductGenderRequest;
import com.sa.clothingstore.dto.response.category.ProductGenderResponse;
import com.sa.clothingstore.model.category.ProductGender;

import java.util.List;
import java.util.UUID;

public interface ProductGenderService {
    List<com.sa.clothingstore.model.category.ProductGender> getAllProductGender();
    ProductGenderResponse createProductGender(ProductGenderRequest productGenderRequest);
    ProductGender modifyProductGender(UUID id, ProductGenderRequest productGenderRequest);
    void deleteProductGender(UUID id);
    List<ProductGender> searchProductGender(String keyword);
}
