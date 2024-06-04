package com.sa.clothingstore.model.order;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sa.clothingstore.model.CommonModel;
import com.sa.clothingstore.model.customer.Address;
import com.sa.clothingstore.model.event.Coupon;
import com.sa.clothingstore.model.customer.Customer;
import com.sa.clothingstore.model.product.ProductStatus;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EntityListeners(AuditingEntityListener.class)
@Entity
@Table(name = "orders")
public class Order extends CommonModel {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;
    @Column(name = "payment_method")
    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;
    @ManyToOne
    @JoinColumn(name = "coupon")
    private Coupon coupon;
    @ManyToOne
    @JoinColumn(name = "shipping_address")
    private Address address;
    @Column(name = "total")
    private BigDecimal total;
    @Column(name = "note")
    private String note;
    @Column(name = "completed_at")
    private Date completedAt;
    @Column(name = "payment_at")
    private Date paymentAt;
    @Column(name = "canceled_at")
    private Date canceledAt;
    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;
    @JsonIgnore
    @OneToMany(mappedBy = "order")
    Set<OrderItem> orderItems;
}

