package com.sa.clothingstore.repository.user.staff;

import com.sa.clothingstore.model.user.staff.Staff;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface StaffRepository extends JpaRepository<Staff, UUID> {
}
