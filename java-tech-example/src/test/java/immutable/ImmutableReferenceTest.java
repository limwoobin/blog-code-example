package immutable;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("불변 객체 테스트")
class ImmutableReferenceTest {

    @Test
    void immutable_test() {
        Amount amount = new Amount(10);

        ImmutableReference immutableReference = new ImmutableReference(10, amount);
        System.out.println(immutableReference.getAmount()); // (1)

        Amount newAmount = immutableReference.getAmount();
        newAmount.setValue(50);

        System.out.println(immutableReference.getAmount()); // (2)
    }
}