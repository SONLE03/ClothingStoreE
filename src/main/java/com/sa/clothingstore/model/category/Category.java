package com.sa.clothingstore.model.category;

import com.sa.clothingstore.model.CommonModel;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.UUID;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Table(name = "category")
public class Category extends CommonModel {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(name = "category_name")
    private String name;
    @ManyToOne
    @JoinColumn(name = "product_gender")
    private ProductGender productGender;
}
