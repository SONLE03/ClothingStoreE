package com.sa.clothingstore.controller.cart;
import com.sa.clothingstore.constant.APIConstant;
import com.sa.clothingstore.dto.request.cart.CartRequest;
import com.sa.clothingstore.dto.response.cart.CartResponse;
import com.sa.clothingstore.service.cart.CartService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(APIConstant.CARTS)
@AllArgsConstructor
public class CartController {
    private final CartService cartService;

    @GetMapping(APIConstant.CART_ID)
    @ResponseStatus(HttpStatus.OK)
    public List<CartResponse> getProductInCart(@PathVariable UUID customerId){
        return cartService.getProductInCart(customerId);
    }
    @PostMapping(APIConstant.CART_ID)
    @ResponseStatus(HttpStatus.CREATED)
    public String importProductToCart(@PathVariable UUID customerId, @RequestBody @Valid CartRequest cartRequest){
        cartService.importProductToCart(customerId, cartRequest);
        return "The product has been successfully added to the cart";
    }
    @PutMapping(APIConstant.CART_ID)
    @ResponseStatus(HttpStatus.OK)
    public String updateProductInCart(@PathVariable UUID customerId, @RequestBody List<@Valid CartRequest> cartRequestList){
        cartService.updateProductInCart(customerId, cartRequestList);
        return "Product information in the shopping cart was successfully changed";
    }
    @DeleteMapping(APIConstant.CART_ID)
    @ResponseStatus(HttpStatus.OK)
    public String deleteProductInCart(@PathVariable UUID customerId, @RequestBody List<@Valid CartRequest> cartRequestList){
        cartService.deleteProductInCart(customerId, cartRequestList);
        return "Product information in the shopping cart was successfully deleted";
    }
}
