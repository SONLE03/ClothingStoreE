package com.sa.clothingstore.service.event.coupon;

import com.sa.clothingstore.dto.request.event.CouponRequest;
import com.sa.clothingstore.dto.response.event.CouponResponse;
import com.sa.clothingstore.model.event.Coupon;

import java.util.List;
import java.util.UUID;

public interface CouponService {
    List<Coupon> getAllCoupon();
    CouponResponse getCouponById(UUID couponId);
    List<CouponResponse> getCouponByStatus(Integer status);
    void createCoupon(CouponRequest couponRequest);
    void updateCoupon(UUID couponId, CouponRequest couponRequest);
    void deleteCoupon(UUID couponId);
    List<CouponResponse> searchCoupon(String keyword);
}
