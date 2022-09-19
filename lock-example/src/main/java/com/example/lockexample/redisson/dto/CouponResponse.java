package com.example.lockexample.redisson.dto;

import com.example.lockexample.redisson.domain.Coupon;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class CouponResponse {
    private Long id;
    private String name;
    private Long availableStock;

    public static CouponResponse toResponse(Coupon coupon) {
        return new CouponResponse(
            coupon.getId(),
            coupon.getName(),
            coupon.getAvailableStock()
        );
    }
}
