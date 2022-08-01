package immutable;

public final class ImmutableReference {
    private final int num;
    private final Amount amount;

    public ImmutableReference(int num, Amount amount) {
        this.num = num;
        this.amount = new Amount(amount.getValue());
    }

    public int getNum() {
        return num;
    }

    public Amount getAmount() {
        return new Amount(amount.getValue());
    }
}
