package SilverPointStrategy.impl;

import SilverPointStrategy.PointStrategy;

public class GoldGradePointStrategy implements PointStrategy {
    private static final double ACCUMULATION_RATE = 0.05;

    @Override
    public int payment(int price) {
        return (int) (price * ACCUMULATION_RATE);
    }
}
