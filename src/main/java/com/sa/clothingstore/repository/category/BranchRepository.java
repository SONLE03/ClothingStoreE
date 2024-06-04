package com.sa.clothingstore.repository.category;

import com.sa.clothingstore.model.category.Branch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface BranchRepository extends JpaRepository<Branch, UUID> {
    Optional<Branch> findByName(String name);
    @Query("Select b from Branch b where b.id = ?1")
    Branch getBranchById(UUID branchId);

    @Query("SELECT b FROM Branch b WHERE LOWER(b.name) LIKE LOWER(CONCAT('%', ?1, '%'))")
    List<Branch> searchBranch(String keyword);
}
