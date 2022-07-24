package com.example.lockexample.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Version;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Getter
public class Stock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String location;

    private Long availableStock;

    @Version
    private Long version;

    public Stock(String name, String location, Long availableStock) {
        this.name = name;
        this.location = location;
        this.availableStock = availableStock;
    }

    public static Stock createStock(String name, String location, Long availableStock) {
        return new Stock(name, location, availableStock);
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
