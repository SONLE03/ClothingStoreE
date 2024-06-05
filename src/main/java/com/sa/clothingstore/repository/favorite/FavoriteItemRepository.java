package com.sa.clothingstore.repository.favorite;

import com.sa.clothingstore.model.favorite.FavoriteItem;
import com.sa.clothingstore.model.favorite.FavoriteItemKey;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface FavoriteItemRepository extends JpaRepository<FavoriteItem, FavoriteItemKey> {

    @Query("SELECT p.id, p.product_Name, p.price, p.category.name, p.branch.name, p.description, GROUP_CONCAT(i.url) " +
            "FROM FavoriteItem f " +
            "JOIN Product p ON f.product.id = p.id " +
            "JOIN Image i ON p.id = i.product.id " +
            "WHERE p.productStatus != 'DELETED' AND f.customer.id = :customerId " +
            "GROUP BY p.id " +
            "ORDER BY p.createdAt DESC")
    List<Object[]> getAllFavoriteProducts(@Param("customerId") UUID customerId);
    @Modifying
    @Transactional
    @Query("DELETE FROM FavoriteItem f WHERE f.customer.id = :customerId AND f.product.id = :productId")
    void deleteFavoriteProduct(@Param("customerId") UUID customerId, @Param("productId") UUID productId);

}
