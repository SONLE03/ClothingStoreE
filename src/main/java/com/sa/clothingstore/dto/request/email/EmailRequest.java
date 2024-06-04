package com.sa.clothingstore.dto.request.email;

import lombok.Builder;

@Builder
public record EmailRequest(String to, String subject, String text) {
}
