package immutable;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("불변 객체 Collection 테스트2")
class ImmutableCollection2Test {

    @Test
    void immutable_test() {
        Amount amount = new Amount(1);
        Amount amount2 = new Amount(2);
        List<Amount> amounts = List.of(amount, amount2);

        ImmutableCollection2 immutableCollection2 = new ImmutableCollection2(amounts);
        System.out.println(immutableCollection2.getAmounts());

        amount2.setValue(100);
        System.out.println(immutableCollection2.getAmounts());
    }
}