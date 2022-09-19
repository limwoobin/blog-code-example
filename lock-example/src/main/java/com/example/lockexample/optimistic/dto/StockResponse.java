package com.example.lockexample.optimistic.dto;

import com.example.lockexample.optimistic.domain.Stock;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class StockResponse {

    private Long id;
    private String name;
    private Long availableCount;

    public static StockResponse toResponse(Stock stock) {
        return new StockResponse(
            stock.getId(),
            stock.getName(),
            stock.getAvailableStock()
        );
    }
}
