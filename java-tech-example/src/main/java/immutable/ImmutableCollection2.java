package immutable;

import java.util.ArrayList;
import java.util.List;

public final class ImmutableCollection2 {
    private final List<Amount> amounts;

    public ImmutableCollection2(List<Amount> amounts) {
        this.amounts = new ArrayList<>(amounts);
    }

    public List<Amount> getAmounts() {
        return new ArrayList<>(amounts);
    }
}
