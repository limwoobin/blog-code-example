package strategy;

public class PaymentService {
    private final Consumer consumer;

    public PaymentService(Consumer consumer) {
        this.consumer = consumer;
    }

    public int payForAmount(int price) {
        return consumer.payment(price);
    }
}
