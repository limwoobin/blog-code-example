package strategy;

public class GoldConsumer implements Consumer {
    private static final double POINT_RATE = 0.05;

    @Override
    public int payment(int price) {
        return (int) (price * POINT_RATE);
    }
}
