package com.example.lockexample.optimistic;

import com.example.lockexample.optimistic.application.StockService;
import com.example.lockexample.dto.StockRequest;
import com.example.lockexample.dto.StockResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/stocks")
@RequiredArgsConstructor
public class StockController {

    private final StockService stockService;

    @PostMapping
    public ResponseEntity<StockResponse> create(@RequestBody StockRequest stockRequest) {
        return new ResponseEntity<>(stockService.createStock(stockRequest), HttpStatus.CREATED);
    }

    @PutMapping(value = "/{stockId}/decrease")
    public ResponseEntity decrease(@PathVariable Long stockId, @RequestParam Long pickingCount) {
        try {
            stockService.decrease(stockId, pickingCount);
        } catch (OptimisticLockingFailureException e) {
            return new ResponseEntity<>("이미 재고가 차감되었습니다.", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
