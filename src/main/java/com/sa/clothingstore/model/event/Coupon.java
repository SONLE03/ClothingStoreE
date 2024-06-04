package com.sa.clothingstore.model.event;

import com.sa.clothingstore.model.CommonModel;
import com.sa.clothingstore.model.order.OrderStatus;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;


@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Table(name = "coupon")
public class Coupon extends CommonModel {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false)
    private UUID id;
    @Column(name = "coupon_name")
    private String name;
    @Column(name = "start_date")
    private Date startDate;
    @Column(name = "end_date")
    private Date endDate;
    @Column(name = "discount_value")
    private BigDecimal discountValue;
    @Column(name = "minimum_bill")
    private BigDecimal minimumBill;
    @Column(name = "quantity")
    private Integer quantity;
    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private EventStatus eventStatus;
}
