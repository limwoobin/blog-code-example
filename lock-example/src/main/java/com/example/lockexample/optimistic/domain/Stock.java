package com.example.lockexample.optimistic.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Version;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Stock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Long availableStock;

    @Version
    private Long version;

    public Stock(String name, Long availableStock) {
        this.name = name;
        this.availableStock = availableStock;
    }

    public static Stock createStock(String name, Long availableStock) {
        return new Stock(name, availableStock);
    }


    public void decrease(Long pickingCount) {
        validateStockCount(pickingCount);
        availableStock -= pickingCount;
    }

    private void validateStockCount(Long pickingCount) {
        if (pickingCount > availableStock) {
            throw new IllegalArgumentException();
        }
    }
}
