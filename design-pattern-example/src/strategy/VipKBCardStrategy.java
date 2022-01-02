package strategy;

public class VipKBCardStrategy implements PointStrategy {
    @Override
    public int earn(int price) {
        return 2000;
    }
}
