package application;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class User {
    private Long id;
    private String name;
    private Gender gender;
    private Integer age;
    private City city;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", gender=" + gender +
                ", age=" + age +
                ", city=" + city +
                '}';
    }
}
