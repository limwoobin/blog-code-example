package strategy;

public class PayController {
    public static void main(String[] args) {
        Consumer goldConsumer = new GoldConsumer(new KakaoPayStrategy());

        PaymentService paymentService = new PaymentService(goldConsumer);
        int goldPoint = paymentService.payForAmount(30000);
        System.out.println("goldPoint: " + goldPoint);

        Consumer vipConsumer = new VipConsumer(new VipKBCardStrategy());
        paymentService = new PaymentService(vipConsumer);
        int vipPoint = paymentService.payForAmount(30000);
        System.out.println("vipPoint: " + vipPoint);
    }
}
