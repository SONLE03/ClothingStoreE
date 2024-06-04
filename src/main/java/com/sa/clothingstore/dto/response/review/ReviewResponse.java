package com.sa.clothingstore.dto.response.review;

import java.util.Date;
import java.util.UUID;

public class ReviewResponse {
    private UUID reviewId;
    private Date reviewDate;
    private String customer;
    private String urlImage;
    private String content;
}
