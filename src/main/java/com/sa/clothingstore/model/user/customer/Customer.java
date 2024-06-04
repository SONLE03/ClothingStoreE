package com.sa.clothingstore.model.user.customer;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sa.clothingstore.model.CommonModel;

import com.sa.clothingstore.model.cart.CartItem;
import com.sa.clothingstore.model.user.User;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Set;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Table(name = "customer")
public class Customer extends User {
    @JsonIgnore
    @OneToMany(mappedBy = "customer")
    Set<CartItem> cartItems;

    public Customer(){

    }
    public Customer(User user){
        super(user);
    }
}
