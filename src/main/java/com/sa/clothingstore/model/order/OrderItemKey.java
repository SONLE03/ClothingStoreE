package com.sa.clothingstore.model.order;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.UUID;
@Getter
@Setter
@Embeddable
public class OrderItemKey implements Serializable {
    @Column(name = "order_id")
    private UUID orderId;
    @Column(name = "productItem_id")
    private UUID productItemId;
}
