package com.sa.clothingstore.repository.product;

import com.sa.clothingstore.dto.response.product.ProductItemResponse;
import com.sa.clothingstore.model.product.Product;
import com.sa.clothingstore.model.product.ProductItem;
import org.hibernate.query.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;
@Repository
public interface ProductItemRepository extends JpaRepository<ProductItem, UUID> {
    @Query("SELECT pi.id FROM ProductItem pi WHERE pi.product = ?1 AND pi.size.id = ?2 AND pi.color.id = ?3")
    UUID getProductItemByProductAndAttribute(Product product, int sizeId, int colorId);

    @Query("SELECT NEW com.sa.clothingstore.dto.response.product.ProductItemResponse(pi.id, sz.name, clr.name, pi.quantity, pi.product.price) FROM ProductItem pi JOIN pi.size sz JOIN pi.color clr WHERE pi.product.id = ?1")
    List<ProductItemResponse> getDetailProduct(UUID productId);
    @Query("SELECT pi FROM ProductItem pi WHERE pi.product.id = ?1")
    List<ProductItem> getProductItemByProduct(UUID productId);
    @Query("SELECT pi.id FROM ProductItem pi WHERE pi.product.id = ?1")
    List<UUID> getProductItemIdByProduct(UUID productId);
}
