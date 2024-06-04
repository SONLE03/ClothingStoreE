package com.sa.clothingstore.repository.cart;

import com.sa.clothingstore.dto.response.cart.CartResponse;
import com.sa.clothingstore.model.cart.CartItem;
import com.sa.clothingstore.model.product.ProductItem;
import com.sa.clothingstore.model.user.customer.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;
@Repository
public interface CartItemRepository extends JpaRepository<CartItem, UUID> {
    List<CartItem> findByCustomer(Customer customer);
    CartItem findByCustomerAndProductItem(Customer customer, ProductItem productItem);
    void deleteByCustomerAndProductItem(Customer customer, ProductItem productItem);

    @Query("SELECT new com.sa.clothingstore.dto.response.cart.CartResponse(ci.productItem.id, ci.productItem.size.name, ci.productItem.color.name, ci.quantity, ci.productItem.product.price, ci.productItem.product.product_Name) FROM CartItem ci WHERE ci.customer = :customer")
    List<CartResponse> findCartResponsesByCustomer(Customer customer);
}
