package application;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.Objects;

@Getter
//@EqualsAndHashCode
@AllArgsConstructor
public class Tuple {
    private Gender gender;
    private City city;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tuple tuple = (Tuple) o;
        return getCity() == tuple.getCity() && getGender() == tuple.getGender();
    }

    @Override
    public int hashCode() {
//        return Objects.hash(getGender(), getCity());
        return 0;
    }
}
