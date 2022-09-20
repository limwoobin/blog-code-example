package com.example.lockexample.redisson.application;

import com.example.lockexample.redisson.aop.DistributeLock;
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

    @DistributeLock(key = "#key")
    public void decrease(final String key, Long couponId) {
        couponDecreaseService.couponDecrease(couponId);
    }
}
