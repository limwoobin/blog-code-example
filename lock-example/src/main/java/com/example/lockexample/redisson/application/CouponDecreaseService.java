package com.example.lockexample.redisson.application;

import com.example.lockexample.redisson.aop.DistributedLock;
import com.example.lockexample.redisson.domain.Coupon;
import com.example.lockexample.redisson.domain.CouponRepository;
import com.example.lockexample.redisson.exception.InternalServerException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class CouponDecreaseService {
    private final CouponRepository couponRepository;

    @DistributedLock(key = "#key")
    public void couponDecrease(String key, Long couponId) {
        Coupon coupon = couponRepository.findById(couponId)
                .orElseThrow(IllegalArgumentException::new);

        coupon.decrease();
    }

    @Transactional
    public void couponDecrease(Long couponId) {
        Coupon coupon = couponRepository.findById(couponId)
                .orElseThrow(IllegalArgumentException::new);

        coupon.decrease();
    }

    @DistributedLock(
            key = "#key",
            exceptionClass = InternalServerException.class,
            exceptionMessage = "서버에서 요청을 처리할 수 없습니다."
    )
    public void couponDecrease2(String key, Long couponId) {
        try {
            Thread.sleep(6000);
        } catch (Exception ignored) {}

        Coupon coupon = couponRepository.findById(couponId)
                .orElseThrow(IllegalArgumentException::new);

        coupon.decrease();
    }

    @DistributedLock(
            key = "#key",
            exceptionClass = RuntimeException.class,
            exceptionMessage = "RuntimeException 입니다."
    )
    public void couponDecrease3(String key, Long couponId) {
        try {
            Thread.sleep(6000);
        } catch (Exception ignored) {}

        Coupon coupon = couponRepository.findById(couponId)
                .orElseThrow(IllegalArgumentException::new);

        coupon.decrease();
    }
}
