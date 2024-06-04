package com.sa.clothingstore.repository.attribute;

import com.sa.clothingstore.model.attribute.Color;
import com.sa.clothingstore.model.attribute.Size;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SizeRepository extends JpaRepository<Size, Integer> {
    Optional<Size> findByName(String name);
}
