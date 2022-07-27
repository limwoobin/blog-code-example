package com.example.lockexample;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.example.lockexample.application.StockService;
import com.example.lockexample.domain.Stock;
import com.example.lockexample.domain.StockRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
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

        Future<?> future = executorService.submit(
            () -> stockService.decrease(savedStock.getId(), 1L));
        Future<?> future2 = executorService.submit(
            () -> stockService.decrease(savedStock.getId(), 1L));

        Exception result = new Exception();

        try {
            future.get();
            future2.get();
        } catch (ExecutionException e) {
            result = (Exception) e.getCause();
        }

        assertTrue(result instanceof OptimisticLockingFailureException);
    }

    @Test
    void 낙관적락_재고_선점_테스트2() throws InterruptedException {
        Stock savedStock = 재고_1개_생성();
        int numberOfThreads = 3;

        ExecutorService executorService = Executors.newFixedThreadPool(numberOfThreads);

        List<Future<?>> tasks = new ArrayList<>();
        for (int i = 0; i < numberOfThreads; i++) {
            Future<?> future = executorService.submit(
                () -> stockService.decrease(savedStock.getId(), 1L));
            tasks.add(future);
        }

        List<Exception> exceptions = new ArrayList<>();
        for (Future<?> task : tasks) {
            try {
                task.get();
            } catch (ExecutionException e) {
                exceptions.add((Exception) e.getCause());
            }
        }

        assertThat(exceptions).hasSize(numberOfThreads - 1);
        for (Exception e : exceptions) {
            assertTrue(e instanceof OptimisticLockingFailureException);
        }
    }

    Stock 재고_1개_생성() {
        Stock stock = Stock.createStock("불닭볶음면", 1L);
        stockRepository.save(stock);
        return stock;
    }
}
