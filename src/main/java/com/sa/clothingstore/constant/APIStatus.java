package com.sa.clothingstore.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum APIStatus {

    // User
    EMAIL_ALREADY_EXISTED(400, "Email already existed"),
    PHONE_ALREADY_EXISTED(400, "Phone already existed"),
    USER_NOT_FOUND(404, "User not found"),
    CUSTOMER_NOT_FOUND(404, "Customer not found"),
    ADDRESS_NOT_FOUND(404, "Address not found"),
    ROLE_NOT_FOUND(404, "Role not found"),
    CUSTOMER_ADDRESS_NOT_FOUND(404, "Customer and address not found"),
    PASSWORD_INCORRECT(400, "Incorrect password. Please re-enter password!"),
    // Email (Send OTP)
    OTP_EXPIRY(400, "OTP has expired!"),
    OTP_INVALID(400, "Invalid OTP!"),
    // Product
    PRODUCT_NOT_FOUND(404, "Product not found"),
    PRODUCT_ITEM_NOT_FOUND(404, "Product item not found"),
    // Cart
    INSUFFICIENT_PRODUCT_QUANTITY(400,"The quantity of products available must be greater than the quantity you want to buy"),
    // Order
    ORDER_NOT_FOUND(404, "Order not found"),
    ORDER_NOT_CANCEL(400, "Order cannot be canceled"),
    ORDER_NOT_SHIPPING(400, "Order cannot be delivered"),
    ORDER_NOT_COMPLETE(400, "Order cannot be completed"),
    ORDER_STATUS_NOT_FOUND(404, "Order status not found"),
    CUSTOMER_NEVER_PURCHASED_PRODUCT(404, "Customer has never purchased the product"),
    // Category
    CATEGORY_NOT_FOUND(404, "Category not found"),
    // Branch
    BRANCH_NOT_FOUND(404, "Branch not found"),
    //Product Gender
    PRODUCT_GENDER_NOT_FOUND(404, "Product gender not found"),
    PRODUCT_GENDER_ALREADY_EXISTED(400, "Product gender already existed"),
    // Color
    COLOR_NOT_FOUND(404, "Color not found"),
    COLOR_ALREADY_EXISTED(400, "Color already existed"),
    // Size
    SIZE_NOT_FOUND(404, "Size not found"),
    // Color or Size
    COLOR_SIZE_NOT_FOUND(404, "Color or Size not found"),
    // Image
    IMAGE_NOT_FOUND(404, "Image not found"),
    SIZE_ALREADY_EXISTED(400, "Size already existed"),
    //Coupon
    COUPON_NOT_FOUND(404, "Coupon not found"),
    // Import product
    IMPORT_NOT_FOUND(404, "Import not found"),
    IMPORT_PRODUCT_PRICE(400, "Selling price cannot be lower than purchase price."),
    // Payment
    PAYMENT_NOT_FOUND(404, "Payment not found"),
    // Review
    REVIEW_NOT_FOUND(404, "Review not found"),
    REVIEW_CUSTOMER_ID_MISMATCH(400, "Review customer ID does not match the requested customer ID");
    private final int status;
    private final String message;
}
