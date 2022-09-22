package com.example.lockexample.redisson.application;

import com.example.lockexample.redisson.dto.CouponRequest;
import com.example.lockexample.redisson.dto.CouponResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CouponService {
    private final CouponDecreaseService couponDecreaseService;
    private final CouponRegisterService couponRegisterService;

    public CouponResponse registerCoupon(final String key, CouponRequest couponRequest) {
        System.out.println("register begin ### ");
        CouponResponse response = couponRegisterService.register(key, couponRequest);
        System.out.println("register begin ### ");

        return response;
    }

    public void decrease(final String key, Long couponId) {
        System.out.println("tx begin ### ");
        couponDecreaseService.couponDecrease(key, couponId);
        System.out.println("tx end ### ");
    }
}
