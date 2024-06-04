package com.sa.clothingstore.service.event.coupon;

import com.sa.clothingstore.constant.APIStatus;
import com.sa.clothingstore.dto.request.event.CouponRequest;
import com.sa.clothingstore.dto.response.event.CouponResponse;
import com.sa.clothingstore.exception.BusinessException;
import com.sa.clothingstore.exception.ObjectNotFoundException;
import com.sa.clothingstore.model.event.Coupon;
import com.sa.clothingstore.model.event.EventStatus;
import com.sa.clothingstore.repository.event.CouponRepository;
import com.sa.clothingstore.service.user.service.UserDetailService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CouponServiceImp implements CouponService{
    private final CouponRepository couponRepository;
    private final ModelMapper modelMapper;
    private final UserDetailService userDetailService;
    @Transactional
    public List<Coupon> getAllCoupon() {
        LocalDate currentDate = LocalDate.now();
        couponRepository.updateExpiredStatus(java.sql.Date.valueOf(currentDate));
        return couponRepository.findAllSortedByEndDateAndStatus();
    }
    @Override
    public List<CouponResponse> getCouponByStatus(Integer status){
        List<Coupon> coupons = couponRepository.findByEventStatus(EventStatus.convertIntegerToStatus(status));
        return coupons.stream()
                .map(coupon -> modelMapper.map(coupon, CouponResponse.class))
                .collect(Collectors.toList());
    }

    @Override
    public CouponResponse getCouponById(UUID couponId) {
        if(!couponRepository.existsById(couponId)){
            throw new BusinessException(APIStatus.COUPON_NOT_FOUND);
        }
        return modelMapper.map(couponRepository.getById(couponId), CouponResponse.class);
    }

    @Override
    @Transactional
    public void createCoupon(CouponRequest couponRequest) {
        Coupon coupon = Coupon.builder()
                .name(couponRequest.getName())
                .startDate(couponRequest.getStartDate())
                .endDate(couponRequest.getEndDate())
                .discountValue(couponRequest.getDiscountValue())
                .minimumBill(couponRequest.getMinimumBill())
                .eventStatus(EventStatus.ACTIVE)
                .quantity(couponRequest.getQuantity())
                .build();
        coupon.setCommonCreate(userDetailService.getIdLogin());
        couponRepository.save(coupon);
    }

    @Override
    @Transactional
    public void updateCoupon(UUID couponId, CouponRequest couponRequest) {
        if(!couponRepository.existsById(couponId)){
            new BusinessException(APIStatus.COUPON_NOT_FOUND);
        }
        Coupon coupon = couponRepository.getById(couponId);
        coupon.setName(couponRequest.getName());
        coupon.setStartDate(couponRequest.getStartDate());
        coupon.setEndDate(couponRequest.getEndDate());
        coupon.setDiscountValue(couponRequest.getDiscountValue());
        coupon.setMinimumBill(couponRequest.getMinimumBill());
        coupon.setQuantity(couponRequest.getQuantity());
        coupon.setEventStatus(EventStatus.convertIntegerToStatus(couponRequest.getStatus()));
        coupon.setCommonUpdate(userDetailService.getIdLogin());
        couponRepository.save(coupon);
    }

    @Override
    public void deleteCoupon(UUID couponId) {
        if(!couponRepository.existsById(couponId)){
            new BusinessException(APIStatus.COUPON_NOT_FOUND);
        }
        couponRepository.deleteById(couponId);
    }

    @Override
    public List<CouponResponse> searchCoupon(String keyword) {
        List<Coupon> coupons = couponRepository.searchCoupon(keyword);
        return coupons.stream()
                .map(coupon -> modelMapper.map(coupon, CouponResponse.class))
                .collect(Collectors.toList());
    }
}
