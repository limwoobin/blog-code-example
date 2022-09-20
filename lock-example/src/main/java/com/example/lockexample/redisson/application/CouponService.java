package com.example.lockexample.redisson.application;

import com.example.lockexample.redisson.domain.Coupon;
import com.example.lockexample.redisson.domain.CouponRepository;
import com.example.lockexample.redisson.dto.CouponRequest;
import com.example.lockexample.redisson.dto.CouponResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CouponService {
    private final CouponRepository couponRepository;
    private final CouponDecreaseService couponDecreaseService;

    @Transactional
    public CouponResponse createCoupon(CouponRequest couponRequest) {
        Coupon coupon = couponRequest.toCoupon();
        couponRepository.save(coupon);
        return CouponResponse.toResponse(coupon);
    }

    @Transactional
    public void decrease(Long couponId) {
        String key = "COUPON_" + couponId;
        couponDecreaseService.couponDecrease(key, couponId);
    }
}
