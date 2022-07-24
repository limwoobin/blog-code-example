package com.example.lockexample.application;

import com.example.lockexample.domain.Stock;
import com.example.lockexample.domain.StockRepository;
import com.example.lockexample.ui.StockRequest;
import javax.persistence.OptimisticLockException;
import lombok.RequiredArgsConstructor;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class StockService {
    private final StockRepository stockRepository;

    @Transactional
    public void createStock(StockRequest stockRequest) {
        Stock stock = stockRequest.toStock();
        stockRepository.save(stock);
    }

    @Transactional
    public void stockPicking(Long stockId, Long pickingCount) {

        try {
            Stock stock = stockRepository.findById(stockId)
                .orElseThrow(IllegalStateException::new);

            System.out.println("####### before: " + stock.getAvailableStock());

            stock.decrease(pickingCount);

            System.out.println("####### after: " + stock.getAvailableStock());
        } catch (Exception e) {
            System.out.println("[ERROR]: " + e.getClass());
        }

//        Stock stock = stockRepository.findById(stockId)
//            .orElseThrow(IllegalStateException::new);
//
//        System.out.println("####### before: " + stock.getAvailableStock());
//
//        stock.decrease(pickingCount);
//
//        System.out.println("####### after: " + stock.getAvailableStock());
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void decrease(Stock stock, Long pickingCount) {
        stock.decrease(pickingCount);
    }
}
