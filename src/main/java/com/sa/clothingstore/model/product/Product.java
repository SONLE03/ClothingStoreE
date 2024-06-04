package com.sa.clothingstore.model.product;

import com.sa.clothingstore.model.CommonModel;
import com.sa.clothingstore.model.category.Branch;
import com.sa.clothingstore.model.category.Category;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Table(name = "product")

public class Product extends CommonModel {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false)
    private UUID id;
    @Column(name = "product_name", nullable = false)
    private String product_Name;
    @Column(name = "description")
    private String description;
    @Column(name = "price", nullable = false)
    private BigDecimal price;
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
    @ManyToOne
    @JoinColumn(name = "branch_id")
    private Branch branch;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private ProductStatus productStatus;
}
