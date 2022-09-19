package com.example.lockexample.optimistic.dto;

import com.example.lockexample.optimistic.domain.Stock;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class StockRequest {
    private String name;
    private Long availableCount;

    public Stock toStock() {
        return Stock.createStock(name, availableCount);
    }
}
