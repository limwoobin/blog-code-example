package com.example.lockexample.ui;

import com.example.lockexample.application.StockService;
import lombok.RequiredArgsConstructor;
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
@RequestMapping(value = "/stock")
@RequiredArgsConstructor
public class StockController {

    private final StockService stockService;

    @PostMapping
    public ResponseEntity create(@RequestBody StockRequest stockRequest) {
        stockService.createStock(stockRequest);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @PutMapping(value = "/{stockId}/picking")
    public ResponseEntity picking(@PathVariable Long stockId, @RequestParam Long pickingCount) {
        stockService.stockPicking(stockId, pickingCount);
        return new ResponseEntity(HttpStatus.OK);
    }
}
