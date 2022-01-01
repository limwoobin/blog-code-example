package strategy;

public class VipConsumer implements Consumer {
    private VipPointStrategy vipPointStrategy;

    public VipConsumer(VipPointStrategy vipPointStrategy) {
        this.vipPointStrategy = vipPointStrategy;
    }

    @Override
    public int payment(int price) {
        return vipPointStrategy.earn(price);
    }
}
