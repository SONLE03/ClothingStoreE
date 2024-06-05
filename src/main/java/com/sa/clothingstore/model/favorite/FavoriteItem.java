package com.sa.clothingstore.model.favorite;

import com.sa.clothingstore.model.product.Product;
import com.sa.clothingstore.model.user.customer.Customer;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EntityListeners(AuditingEntityListener.class)
@Entity
@Table(name = "favorite_item")
public class FavoriteItem {
    @EmbeddedId
    FavoriteItemKey id;
    @ManyToOne
    @MapsId("customerId")
    @JoinColumn(name = "customer_id")
    private Customer customer;
    @ManyToOne
    @MapsId("productId")
    @JoinColumn(name = "product_id")
    private Product product;
}
