package com.example.lockexample.redisson.dto;

import com.example.lockexample.redisson.domain.Coupon;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class CouponRequest {
    private String name;
    private Long availableCount;

    public Coupon toCoupon() {
        return Coupon.of(name, availableCount);
    }
}
