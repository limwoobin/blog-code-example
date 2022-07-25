package com.example.lockexample.ui;

import com.example.lockexample.domain.Stock;
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
