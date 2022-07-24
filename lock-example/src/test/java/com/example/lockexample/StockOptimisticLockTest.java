package com.example.lockexample;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;

import com.example.lockexample.application.StockService;
import com.example.lockexample.domain.Stock;
import com.example.lockexample.domain.StockRepository;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.persistence.OptimisticLockException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.OptimisticLockingFailureException;

@DisplayName("낙관적락 재고 선점 테스트")
@SpringBootTest
class StockOptimisticLockTest {

    @Autowired
    StockService stockService;

    @Autowired
    StockRepository stockRepository;

    @Test
    void 낙관적락_재고_선점_테스트() throws InterruptedException {
        Stock savedStock = 재고_1개_생성();
        int numberOfThreads = 2;

        ExecutorService executorService = Executors.newFixedThreadPool(numberOfThreads);
        CountDownLatch latch = new CountDownLatch(numberOfThreads);

        for (int i=0; i<numberOfThreads; i++) {
            executorService.execute(() -> {
                stockService.stockPicking(savedStock.getId(), 1L);
                latch.countDown();
            });
        }

        latch.await();
    }

    @Test
    void 낙관적락_재고_선점_테스트_v2() throws InterruptedException {
        재고_1개_생성();
        int numberOfThreads = 3;

        CountDownLatch countDownLatch = new CountDownLatch(numberOfThreads);
        List<Thread> workers = Stream
            .generate(() -> new Thread(new StockWorker(countDownLatch, stockService)))
            .limit(numberOfThreads)
            .collect(Collectors.toList());

        System.out.printf("Start Multi Thread: %d", Thread.currentThread().getId());
        System.out.println();
        workers.forEach(Thread::start);

        System.out.printf("Waiting for finished: %d", Thread.currentThread().getId());
        System.out.println();
        countDownLatch.await();

        System.out.printf("Finished: %d", Thread.currentThread().getId());

        Stock stock = stockRepository.findById(1L)
                .orElseThrow(IllegalArgumentException::new);
        System.out.println("zz");
    }

    Stock 재고_1개_생성() {
        Stock stock = Stock.createStock("불닭볶음면", "T-1011", 1L);
        stockRepository.save(stock);
        return stock;
    }

    static class StockWorker implements Runnable {
        private CountDownLatch countDownLatch;
        private StockService stockService;

        public StockWorker(CountDownLatch countDownLatch, StockService stockService) {
            this.countDownLatch = countDownLatch;
            this.stockService = stockService;
        }

        @Override
        public void run() {
            stockService.stockPicking(1L,1L);
            countDownLatch.countDown();

//            try {
//                stockService.stockPicking(1L,1L);
//            } catch (OptimisticLockingFailureException e) {
//                System.out.println("[ERROR]:" + e.getMessage());
//            } finally {
//                countDownLatch.countDown();
//            }
        }
    }
}
