package com.example.lockexample.ui;

import com.example.lockexample.domain.Stock;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class StockResponse {

    private Long id;
    private String name;
    private String location;
    private Long availableCount;

    public static StockResponse toResponse(Stock stock) {
        return new StockResponse(
            stock.getId(),
            stock.getName(),
            stock.getLocation(),
            stock.getAvailableStock()
        );
    }
}
