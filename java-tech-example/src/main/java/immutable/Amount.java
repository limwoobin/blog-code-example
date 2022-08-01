package immutable;

public class Amount {
    private int value;

    public Amount(int value) {
        this.value = value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "amount=" + value;
    }
}
