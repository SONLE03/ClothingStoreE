package com.sa.clothingstore.dto.response.category;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BranchResponse {
    @JsonProperty("id")
    private UUID id;
    @JsonProperty("branch_name")
    private String name;
}
