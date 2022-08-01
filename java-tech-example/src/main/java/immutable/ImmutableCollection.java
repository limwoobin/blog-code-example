package immutable;

import java.util.ArrayList;
import java.util.List;

public final class ImmutableCollection {
    private final int num;
    private final List<Integer> list;


    public ImmutableCollection(int num, List<Integer> list) {
        this.num = num;
        this.list = new ArrayList<>(list);
    }

    public int getNum() {
        return num;
    }

    public List<Integer> getList() {
        return new ArrayList<>(list);
    }
}
