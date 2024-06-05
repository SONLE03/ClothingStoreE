package com.sa.clothingstore.controller.favorite;

import com.sa.clothingstore.constant.APIConstant;
import com.sa.clothingstore.dto.request.event.CouponRequest;
import com.sa.clothingstore.dto.request.favorite.FavoriteRequest;
import com.sa.clothingstore.dto.response.product.ProductResponse;
import com.sa.clothingstore.model.event.Coupon;
import com.sa.clothingstore.service.favorite.FavoriteService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequestMapping(APIConstant.FAVORITES)
@RestController
@AllArgsConstructor
public class FavoriteController {
    private final FavoriteService favoriteService;

    @GetMapping(APIConstant.CUSTOMER_ID)
    public List<ProductResponse> getAllFavoriteProducts(@PathVariable UUID customerId){
        return favoriteService.getAllFavoriteProducts(customerId);
    }
    @PostMapping(APIConstant.CUSTOMER_ID)
    @ResponseStatus(HttpStatus.CREATED)
    public String createFavoriteProduct(@PathVariable UUID customerId, @RequestBody @Valid FavoriteRequest favoriteRequest){
        favoriteService.addProductToFavoriteList(customerId, favoriteRequest);
        return "Favorite product was added successfully";
    }
    @DeleteMapping(APIConstant.CUSTOMER_ID)
    @ResponseStatus(HttpStatus.OK)
    public String deleteFavoriteProduct(@PathVariable UUID customerId, @RequestBody @Valid FavoriteRequest favoriteRequest){
        favoriteService.deleteProductInFavoriteList(customerId, favoriteRequest);
        return "Favorite product was deleted successfully";
    }
}
