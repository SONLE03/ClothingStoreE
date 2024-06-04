package com.sa.clothingstore.service.cart;

import com.sa.clothingstore.dto.request.cart.CartRequest;
import com.sa.clothingstore.dto.response.cart.CartResponse;

import java.util.List;
import java.util.UUID;

public interface CartService {
    List<CartResponse> getProductInCart(UUID customerId);
    void importProductToCart(UUID customerId, CartRequest cartRequest);
    void updateProductInCart(UUID customerId, List<CartRequest> cartRequestList);
    void deleteProductInCart(UUID customerId, List<CartRequest> cartRequestList);
}