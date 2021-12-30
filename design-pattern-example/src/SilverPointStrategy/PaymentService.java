package SilverPointStrategy;

public class PaymentService {
    private final PointStrategy pointStrategy;

    public PaymentService(PointStrategy pointStrategy) {
        this.pointStrategy = pointStrategy;
    }

    public int payForAmount(int price) {
        return pointStrategy.payment(price);
    }
}
