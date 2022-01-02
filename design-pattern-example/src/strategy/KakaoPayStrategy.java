package strategy;

public class KakaoPayStrategy implements PointStrategy {
    @Override
    public int earn(int price) {
        return 1000;
    }
}
