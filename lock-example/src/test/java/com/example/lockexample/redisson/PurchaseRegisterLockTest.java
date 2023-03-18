package com.example.lockexample.redisson;

import com.example.lockexample.redisson.application.PurchaseRegisterService;
import com.example.lockexample.redisson.domain.Purchase;
import com.example.lockexample.redisson.domain.PurchaseRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Redisson Lock 발주 등록 테스트")
@SpringBootTest
class PurchaseRegisterLockTest {
    @Autowired
    private PurchaseRegisterService purchaseRegisterService;

    @Autowired
    private PurchaseRepository purchaseRepository;

    /**
     * Feature: 발주 등록 동시성 테스트
     * <p>
     * Scenario: KURLY_001 라는 이름의 발주 10개가 동시에 등록 요청됨
     * <p>
     * Then 중복된 발주 10개가 동시에 들어오더라도 한 건만 정상 등록 되어야 함
     */
    @Test
    void 발주등록_분산락_적용_테스트() throws InterruptedException {
        String 발주_코드 = "KURLY_001";

        int numberOfThreads = 10;
        ExecutorService executorService = Executors.newFixedThreadPool(numberOfThreads);
        CountDownLatch latch = new CountDownLatch(numberOfThreads);

        for (int i = 0; i < numberOfThreads; i++) {
            executorService.submit(() -> {
                try {
                    // 분산락 적용 메소드 호출
                    purchaseRegisterService.register(발주_코드, 발주_코드);
                } finally {
                    latch.countDown();
                }
            });
        }

        latch.await();

        Long totalCount = purchaseRepository.countByCode(발주_코드);

        System.out.println("등록된 발주 = " + totalCount);
        assertThat(totalCount).isOne();
    }

    @Test
    void 발주등록_분산락_미적용_테스트() throws InterruptedException {
        String 발주_코드 = "KURLY_001";

        int numberOfThreads = 10;
        ExecutorService executorService = Executors.newFixedThreadPool(numberOfThreads);
        CountDownLatch latch = new CountDownLatch(numberOfThreads);

        for (int i = 0; i < numberOfThreads; i++) {
            executorService.submit(() -> {
                try {
                    // 분산락 미적용 메소드 호출
                    purchaseRegisterService.register(발주_코드);
                } finally {
                    latch.countDown();
                }
            });
        }

        latch.await();

        Long totalCount = purchaseRepository.countByCode(발주_코드);

        System.out.println("등록된 발주 = " + totalCount);
        assertThat(totalCount).isOne();
    }
}
