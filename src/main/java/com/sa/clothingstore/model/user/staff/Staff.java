package com.sa.clothingstore.model.user.staff;

import com.sa.clothingstore.model.user.User;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.UUID;

@Entity
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
@AllArgsConstructor
@Table(name = "staff")
public class Staff extends User{
    public Staff(User user){
        super(user);
    }
}
