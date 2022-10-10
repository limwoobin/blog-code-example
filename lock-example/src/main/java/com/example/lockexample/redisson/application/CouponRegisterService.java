package com.example.lockexample.redisson.application;

import com.example.lockexample.redisson.aop.DistributeLock;
import com.example.lockexample.redisson.domain.Coupon;
import com.example.lockexample.redisson.domain.CouponRepository;
import com.example.lockexample.redisson.dto.CouponRequest;
import com.example.lockexample.redisson.dto.CouponResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CouponRegisterService {

    private final CouponRepository couponRepository;

    @DistributeLock(key = "#key")
    public CouponResponse register(final String key, CouponRequest request) {
        validateAlreadyExist(request);

        Coupon coupon = request.toCoupon();
        couponRepository.save(coupon);
        return CouponResponse.toResponse(coupon);
    }

    private void validateAlreadyExist(CouponRequest request) {
        couponRepository.findByName(request.getName()).ifPresent(x -> {
            throw new IllegalArgumentException();
        });
    }
}
