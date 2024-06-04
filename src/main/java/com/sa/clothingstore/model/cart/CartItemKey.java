package com.sa.clothingstore.model.cart;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.UUID;

@Embeddable
@Getter
@Setter
public class CartItemKey implements Serializable{
    @Column(name = "customer_id")
    private UUID customerId;
    @Column(name = "productItem_id")
    private UUID productItemId;
}

