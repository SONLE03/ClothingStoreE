package com.sa.clothingstore.repository.attribute;

import com.sa.clothingstore.model.attribute.Color;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.swing.text.html.Option;
import java.util.Optional;

public interface ColorRepository extends JpaRepository<Color, Integer> {
    Optional<Color> findByName(String name);
}
