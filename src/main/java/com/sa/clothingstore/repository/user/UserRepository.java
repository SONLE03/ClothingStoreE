package com.sa.clothingstore.repository.user;

import com.sa.clothingstore.dto.response.user.UserResponse;
import com.sa.clothingstore.model.user.Role;
import com.sa.clothingstore.model.user.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
    Optional<User> findByEmail(String email);
    Optional<User> findByPhone(String phone);
    Optional<List<User>> findByRole(Role role);

    @Transactional
    @Modifying
    @Query("update User u set u.password = ?2 where u.email = ?1")
    void updatePassword(String email, String password);

    @Query("select u.role from User u where u.id = ?1")
    Integer getRoleById(UUID userId);

    @Query("SELECT u FROM User u WHERE u.id = ?1")
    User getUserDetail(UUID userId);
}
