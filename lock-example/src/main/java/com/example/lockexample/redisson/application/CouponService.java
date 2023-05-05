package com.example.lockexample.redisson.application;

import com.example.lockexample.redisson.dto.CouponRequest;
import com.example.lockexample.redisson.dto.CouponResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CouponService {
    private final CouponDecreaseService couponDecreaseService;
    private final CouponRegisterService couponRegisterService;

    @Value("#{19 + 2}")
    private int add;

    private static final String COUPON_KEY_PREFIX = "COUPON_";

    public CouponResponse registerCoupon(CouponRequest couponRequest) {
        String key = COUPON_KEY_PREFIX + couponRequest.getName();
        return couponRegisterService.register(key, couponRequest);
    }

    public void decrease(Long couponId) {
        System.out.println("add ### " + add);

        String key = COUPON_KEY_PREFIX + couponId;
        couponDecreaseService.couponDecrease(key, couponId);
    }

    public void decrease2(Long couponId) {
        String key = COUPON_KEY_PREFIX + couponId;
        couponDecreaseService.couponDecrease2(key, couponId);
    }

    public void decrease3(Long couponId) {
        String key = COUPON_KEY_PREFIX + couponId;
        couponDecreaseService.couponDecrease3(key, couponId);
    }
}
