package com.sa.clothingstore.service.favorite;

import com.sa.clothingstore.dto.request.favorite.FavoriteRequest;
import com.sa.clothingstore.dto.response.favorite.FavoriteResponse;
import com.sa.clothingstore.dto.response.product.ProductResponse;

import java.util.List;
import java.util.UUID;

public interface FavoriteService {
    List<ProductResponse> getAllFavoriteProducts(UUID customerId);

    void addProductToFavoriteList(UUID customerId, FavoriteRequest favoriteRequest);

    void deleteProductInFavoriteList(UUID customerId, FavoriteRequest favoriteRequest);
}
