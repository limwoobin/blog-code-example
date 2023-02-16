package com.example.lockexample.redisson;

import com.example.lockexample.redisson.application.CouponService;
import com.example.lockexample.redisson.domain.Coupon;
import com.example.lockexample.redisson.domain.CouponRepository;
import com.example.lockexample.redisson.exception.InternalServerException;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.*;

import static org.assertj.core.api.Assertions.assertThat;


@DisplayName("Redisson Lock 획득 실패시 예외처리 테스트")
@SpringBootTest
class CouponDecreaseLockExceptionTest {

    @Autowired
    private CouponService couponService;

    @Autowired
    private CouponRepository couponRepository;

    private Coupon coupon;

    @BeforeEach
    void setUp() {
        coupon = new Coupon("C0001", 100L);
        couponRepository.save(coupon);
    }

    @AfterEach
    void teardown() {
        couponRepository.deleteAll();
    }

    @Test
    @DisplayName("zz")
    void 쿠폰차감_락_획득_예외테스트() {
        int numberOfThreads = 3;
        ExecutorService executor = Executors.newFixedThreadPool(numberOfThreads);

        Future<?> future = executor.submit(new CouponDecreaseWorker(couponService, coupon));
        Future<?> future2 = executor.submit(new CouponDecreaseWorker(couponService, coupon));
        Future<?> future3 = executor.submit(new CouponDecreaseWorker(couponService, coupon));
        executor.shutdown();

        Object result = new Object();

        try {
            future.get();
            future2.get();
            future3.get();
        } catch (Exception e) {
            result = e.getCause();
        }

        Exception exception = (Exception) result;

        Assertions.assertTrue(exception instanceof InternalServerException);
        assertThat(exception.getMessage()).isEqualTo("서버에서 요청을 처리할 수 없습니다.");
    }

    static class CouponDecreaseWorker implements Runnable {
        private final CouponService couponService;
        private final Coupon coupon;

        public CouponDecreaseWorker(CouponService couponService, Coupon coupon) {
            this.couponService = couponService;
            this.coupon = coupon;
        }

        @Override
        public void run() {
            couponService.decrease2(coupon.getId());
        }
    }
}
