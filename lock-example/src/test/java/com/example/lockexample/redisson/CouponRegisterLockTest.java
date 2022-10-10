package com.example.lockexample.redisson;

import static org.assertj.core.api.Assertions.assertThat;

import com.example.lockexample.redisson.application.CouponService;
import com.example.lockexample.redisson.domain.CouponRepository;
import com.example.lockexample.redisson.dto.CouponRequest;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@DisplayName("Redisson Lock 쿠폰 등록 테스트")
@SpringBootTest
public class CouponRegisterLockTest {

    @Autowired
    private CouponService couponService;

    @Autowired
    private CouponRepository couponRepository;

    @Test
    void 같은이름의_쿠폰이_여러개_등록될수_없음() throws InterruptedException {
        CouponRequest couponRequest = new CouponRequest("NEW001", 10L);

        int numberOfThreads = 100;
        ExecutorService executorService = Executors.newFixedThreadPool(32);
        CountDownLatch latch = new CountDownLatch(numberOfThreads);

        for (int i=0; i<numberOfThreads; i++) {
            executorService.submit(() -> {
                try {
                    couponService.registerCoupon(couponRequest);
                } finally {
                    latch.countDown();
                }
            });
        }

        latch.await();

        Long totalCount = couponRepository.countByName("NEW001");
        assertThat(totalCount).isOne();
    }
}
