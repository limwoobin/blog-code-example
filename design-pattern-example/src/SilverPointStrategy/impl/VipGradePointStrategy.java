package SilverPointStrategy.impl;

import SilverPointStrategy.PointStrategy;

public class VipGradePointStrategy implements PointStrategy {
    private static final double ACCUMULATION_RATE = 0.1;

    @Override
    public int payment(int price) {
        return (int) (price * ACCUMULATION_RATE);
    }
}
