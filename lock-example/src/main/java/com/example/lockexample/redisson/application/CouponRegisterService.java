package com.example.lockexample.redisson.application;

import com.example.lockexample.redisson.domain.Coupon;
import com.example.lockexample.redisson.domain.CouponRepository;
import com.example.lockexample.redisson.dto.CouponRequest;
import com.example.lockexample.redisson.dto.CouponResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class CouponRegisterService {
    private final CouponRepository couponRepository;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public CouponResponse register(CouponRequest request) {
        validateAlreadyExist(request);

        Coupon coupon = request.toCoupon();
        couponRepository.save(coupon);
        return CouponResponse.toResponse(coupon);
    }

    private void validateAlreadyExist(CouponRequest request) {
        couponRepository.findByName(request.getName())
                .orElseThrow(IllegalArgumentException::new);
    }
}
