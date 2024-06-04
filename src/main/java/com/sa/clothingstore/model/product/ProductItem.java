package com.sa.clothingstore.model.product;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sa.clothingstore.model.attribute.Color;
import com.sa.clothingstore.model.attribute.Size;
import com.sa.clothingstore.model.importInvoice.ImportItem;
import com.sa.clothingstore.model.order.OrderItem;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Set;
import java.util.UUID;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Table(name = "product_item")
public class ProductItem {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false)
    private UUID id;
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
    @ManyToOne
    @JoinColumn(name = "size_id")
    private Size size;
    @ManyToOne
    @JoinColumn(name = "color_id")
    private Color color;
    @Column(name = "quantity")
    private Integer quantity;
    @JsonIgnore
    @OneToMany(mappedBy = "productItem")
    Set<OrderItem> orderItems;
    @JsonIgnore
    @OneToMany(mappedBy = "productItem")
    Set<ImportItem> importItems;

}
