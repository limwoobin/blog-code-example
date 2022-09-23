package com.example.lockexample.redisson.application;

import com.example.lockexample.redisson.dto.CouponRequest;
import com.example.lockexample.redisson.dto.CouponResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CouponService {
    private final CouponDecreaseService couponDecreaseService;
    private final CouponRegisterService couponRegisterService;

    public CouponResponse registerCoupon(CouponRequest couponRequest) {
        System.out.println("register begin ### ");
        String key = "COUPON_" + couponRequest.getName();
        CouponResponse response = couponRegisterService.register(key, couponRequest);
        System.out.println("register begin ### ");

        return response;
    }

    public void decrease(Long couponId) {
        System.out.println("tx begin ### ");

        String key = "COUPON_" + couponId;
        couponDecreaseService.couponDecrease(key, couponId);

        System.out.println("tx end ### ");
    }
}
