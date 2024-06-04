package com.sa.clothingstore.constant;

public class APIConstant {
    // Common Routes
    public static final String VERSION = "/api/v1";
    public static final String SEARCH = "/search";
    public static final String ID = "/{id}";

    // Auth Routes
    public static final String AUTH = VERSION + "/auth";
    public static final String LOGIN = "/login";
    public static final String LOGOUT = "/logout";
    public static final String SIGNUP = "/signup";
    public static final String REFRESH_TOKEN = "/refreshtoken";
    public static final String AUTH_ME = "/me";

    // Users Routes
    public static final String USERS = VERSION + "/users";
    public static final String USER_ID = "/{userId}";
    public static final String GET_ALL = "/all/{role}";
    public static final String VERIFY_OTP = "verifyOtp/{otp}/{email}";
    public static final String CHANGE_PASSWORD = "/changePassword/{email}";
    // Customer
    public static final String CUSTOMERS = VERSION + "/customers";
    public static final String CUSTOMER_ID = "/{customerId}";
    public static final String CREATE_ADDRESS = "address/{userId}";
    public static final String UPDATE_ADDRESS = "address/{addressId}";
    // Email Routes
    public static final String EMAIL = VERSION + "/email";
    public static final String SEND_OTP = "/verifyEmail/{email}";

    // Branch Routes
    public static final String BRANCHES = VERSION + "/branch";
    public static final String BRANCH_ID = "/{branchId}";

    // Product Gender Routes
    public static final String PRODUCT_GENDERS = VERSION + "/productGender";
    public static final String PRODUCT_GENDER_ID = "/{productGenderId}";

    // Category Routes
    public static final String CATEGORIES = VERSION + "/category";
    public static final String CATEGORY_ID = "/{categoryId}";

    // Image Routes
    public static final String IMAGES = VERSION + "/image";
    public static final String IMAGE_ID = "/{imageId}";

    // Color Routes
    public static final String COLORS = VERSION + "/color";
    public static final String COLOR_ID = "/{colorId}";

    // Size Routes
    public static final String SIZES = VERSION + "/size";
    public static final String SIZE_ID = "/{sizeId}";

    // Product Routes
    public static final String PRODUCTS = VERSION + "/products";
    public static final String PRODUCT_ID = "/{productId}";

    // Review
    public static final String REVIEWS = VERSION + "/reviews";
    public static final String REVIEW_ID = "/{reviewId}";

    // Import routes
    public static final String IMPORTS = VERSION + "/imports";
    public static final String IMPORT_ID = "/{importId}";

    // Coupon
    public static final String COUPONS = VERSION + "/coupons";
    public static final String COUPON_ID = "couponId/{couponId}";
    public static final String COUPON_STATUS = "status/{status}";

    //Cart
    public static final String CARTS = VERSION + "/carts";
    public static final String CART_ID = "/{customerId}";

    //Order
    public static final String ORDERS = VERSION + "/orders";
    public static final String ORDER_ID = "/{orderId}";
    public static final String ORDER_STATUS = "/status/{orderStatus}";
    public static final String ORDER_CUSTOMER = "/customer/{customerId}";
    // VNPay
    public static final String VNPAY = VERSION + "/vnpay";
    public static final String SUBMIT_ORDER = "/submitOrder";
    public static final String VNPAY_RETURN = "/vnpay-payment-return";
    //Payment method
    public static final String PAYMENTS = VERSION + "/payments";
    public static final String PAYMENT_ID = "/{paymentId}";
    // Report
    public static final String REPORTS = VERSION + "/reports";
    public static final String USER_DAILY_REVENUE = "/daily-revenue/{userId}";
    public static final String DAILY_REVENUE = "/daily-revenue";
    public static final String MONTHLY_REVENUE = "/monthly-revenue";
    public static final String YEARLY_REVENUE = "/yearly-revenue";
    public static final String USER_DAILY_EXPENSE = "/daily-expense/{userId}";
    public static final String DAILY_EXPENSE = "/daily-expense";
    public static final String MONTHLY_EXPENSE = "/monthly-expense";
    public static final String YEARLY_EXPENSE = "/yearly-expense";
}
