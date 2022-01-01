package strategy;

public interface VipPointStrategy {
    double POINT_RATE = 0.1;

    int earn(int price);
}
