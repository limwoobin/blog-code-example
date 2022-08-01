package immutable;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("불변 객체 Collection 테스트")
class ImmutableCollectionTest {

    @Test
    void immutable_test() {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        ImmutableCollection immutableCollection = new ImmutableCollection(10, list);
        System.out.println(immutableCollection.getList());

        List<Integer> newList = immutableCollection.getList();
        newList.add(2);

        System.out.println(immutableCollection.getList());
    }
}