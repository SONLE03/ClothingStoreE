package com.sa.clothingstore.repository.user.admin;

import com.sa.clothingstore.model.user.admin.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AdminRepository extends JpaRepository<Admin, UUID> {
}
