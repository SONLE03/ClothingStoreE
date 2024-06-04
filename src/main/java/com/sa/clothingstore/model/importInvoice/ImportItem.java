package com.sa.clothingstore.model.importInvoice;

import com.sa.clothingstore.model.product.ProductItem;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.math.BigDecimal;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EntityListeners(AuditingEntityListener.class)
@Entity
@Table(name = "import_item")
public class ImportItem {
    @EmbeddedId
    ImportItemKey id;
    @ManyToOne
    @MapsId("importId")
    @JoinColumn(name = "import_id")
    private ImportInvoice importInvoice;
    @ManyToOne
    @MapsId("productItemId")
    @JoinColumn(name = "product_item")
    private ProductItem productItem;
    @Column(name = "quantity")
    private Integer quantity;
    @Column(name = "price")
    private BigDecimal price;
    @Column(name = "total")
    private BigDecimal total;

}
