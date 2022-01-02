package strategy;

public class GoldConsumer implements Consumer {
    private static final double POINT_RATE = 0.05;
    private PointStrategy pointStrategy;

    public GoldConsumer(PointStrategy pointStrategy) {
        this.pointStrategy = pointStrategy;
    }

    @Override
    public int payment(int price) {
        int point = 0;
        point += pointStrategy.earn(price);
        point += price * POINT_RATE;
        return point;
    }
}
