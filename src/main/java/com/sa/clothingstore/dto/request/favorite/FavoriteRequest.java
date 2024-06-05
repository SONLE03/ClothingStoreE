package com.sa.clothingstore.dto.request.favorite;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class FavoriteRequest {
    @NotEmpty(message = "Missing product")
    List<UUID> productIds;
}
