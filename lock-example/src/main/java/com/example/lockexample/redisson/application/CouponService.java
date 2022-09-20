package com.example.lockexample.redisson.application;

import com.example.lockexample.redisson.aop.DistributeLock;
import com.example.lockexample.redisson.dto.CouponRequest;
import com.example.lockexample.redisson.dto.CouponResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CouponService {
    private final CouponDecreaseService couponDecreaseService;
    private final CouponRegisterService couponRegisterService;

    @DistributeLock(key = "#key")
    public CouponResponse registerCoupon(final String key, CouponRequest couponRequest) {
        return couponRegisterService.register(couponRequest);
    }

    @DistributeLock(key = "#key")
    public void decrease(final String key, Long couponId) {
        couponDecreaseService.couponDecrease(couponId);
    }
}
