package strategy;

public class VipOverPriceStrategy implements VipPointStrategy {
    @Override
    public int earn(int price) {
        int point = 0;
        if (price >= 30000) {
            point += 2000;
        }

        point += price * POINT_RATE;
        return point;
    }
}
