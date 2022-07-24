package com.example.lockexample.ui;

import com.example.lockexample.domain.Stock;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class StockRequest {
    private String name;
    private String location;
    private Long availableCount;

    public Stock toStock() {
        return Stock.createStock(name, location, availableCount);
    }
}
