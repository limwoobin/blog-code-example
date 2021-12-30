package SilverPointStrategy;

import SilverPointStrategy.impl.BronzeGradePointStrategy;
import SilverPointStrategy.impl.GoldGradePointStrategy;

public class PayController {
    public static void main(String[] args) {
        PointStrategy bronzePointStrategy = new BronzeGradePointStrategy();
        PaymentService paymentService = new PaymentService(bronzePointStrategy);

        int point = paymentService.payForAmount(50000);
        System.out.println("point: " + point);

        PointStrategy goldPointStrategy = new GoldGradePointStrategy();
        PaymentService paymentService1 = new PaymentService(goldPointStrategy);
        int goldConsumerPoint = paymentService1.payForAmount(50000);
        System.out.println("goldConsumerPoint: " + goldConsumerPoint);
    }
}
