package com.example.lockexample.redisson.application;

import com.example.lockexample.redisson.aop.DistributeLock;
import com.example.lockexample.redisson.domain.Coupon;
import com.example.lockexample.redisson.domain.CouponRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class CouponDecreaseService {
    private final CouponRepository couponRepository;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @DistributeLock(key = "#key")
    public void couponDecrease(String key, Long couponId) {
        Coupon coupon = couponRepository.findById(couponId)
                .orElseThrow(IllegalArgumentException::new);

        coupon.decrease();
    }
}
