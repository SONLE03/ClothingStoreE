package com.sa.clothingstore.controller.event;

import com.sa.clothingstore.constant.APIConstant;
import com.sa.clothingstore.dto.request.event.CouponRequest;
import com.sa.clothingstore.dto.response.event.CouponResponse;
import com.sa.clothingstore.model.event.Coupon;
import com.sa.clothingstore.service.event.coupon.CouponService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequestMapping(APIConstant.COUPONS)
@RestController
@AllArgsConstructor
public class CouponController {
    private final CouponService couponService;

    @GetMapping()
    public List<Coupon> getAllCoupon(){
        return couponService.getAllCoupon();
    }
    @GetMapping(APIConstant.SEARCH)
    public List<CouponResponse> searchCoupon(@RequestParam("keyword") String keyword){
        return couponService.searchCoupon(keyword);
    }
    @GetMapping(APIConstant.COUPON_STATUS)
    public List<CouponResponse> getCouponByStatus(@PathVariable Integer status){
        return couponService.getCouponByStatus(status);
    }
    @GetMapping(APIConstant.COUPON_ID)
    public CouponResponse getCouponById(@PathVariable UUID couponId){
        return couponService.getCouponById(couponId);
    }
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public String createCoupon(@RequestBody @Valid CouponRequest couponRequest){
        couponService.createCoupon(couponRequest);
        return "Coupon created successfully";
    }
    @PutMapping(APIConstant.COUPON_ID)
    @ResponseStatus(HttpStatus.OK)
    public String updateCoupon(@PathVariable UUID couponId, @RequestBody @Valid CouponRequest couponRequest){
        couponService.updateCoupon(couponId, couponRequest);
        return "Coupon modified successfully";
    }
    @DeleteMapping(APIConstant.COUPON_ID)
    @ResponseStatus(HttpStatus.OK)
    public String deleteCoupon(@PathVariable UUID couponId){
        couponService.deleteCoupon(couponId);
        return "Coupon deleted successfully";
    }
}
