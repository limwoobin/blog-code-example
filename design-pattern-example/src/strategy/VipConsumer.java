package strategy;

public class VipConsumer implements Consumer {
    private static final double POINT_RATE = 0.1;
    private PointStrategy pointStrategy;

    public VipConsumer(PointStrategy pointStrategy) {
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
