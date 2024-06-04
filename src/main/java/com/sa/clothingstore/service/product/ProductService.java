package com.sa.clothingstore.service.product;

import com.sa.clothingstore.dto.request.product.ProductItemRequest;
import com.sa.clothingstore.dto.request.product.ProductRequest;
import com.sa.clothingstore.dto.response.product.ProductItemResponse;
import com.sa.clothingstore.dto.response.product.ProductResponse;
import com.sa.clothingstore.model.product.Product;
import com.sa.clothingstore.model.product.ProductItem;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

public interface ProductService {
    List<ProductItemResponse> getDetailProduct(UUID productId);
    List<ProductResponse> getAllProduct();
    void createProduct(List<MultipartFile> multipartFiles, ProductRequest productRequest) throws IOException;
    void updateProduct(UUID productId, List<MultipartFile> multipartFiles, ProductRequest productRequest) throws IOException;
    void addProductExisted(UUID productId, List<ProductItemRequest> productItemRequests);
    void deleteProduct(UUID productId);
}
