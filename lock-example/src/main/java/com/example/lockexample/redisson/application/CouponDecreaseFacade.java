package com.example.lockexample.redisson.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CouponDecreaseFacade {
    private final CouponDecreaseService couponDecreaseService;

    private static final String COUPON_KEY_PREFIX = "COUPON_";

    public void decrease(Long couponId) {
        String key = COUPON_KEY_PREFIX + couponId;
        couponDecreaseService.couponDecrease(key, couponId);
    }

    public void decrease2(Long couponId) {
        String key = COUPON_KEY_PREFIX + couponId;
        couponDecreaseService.couponDecrease2(key, couponId);
    }
}
