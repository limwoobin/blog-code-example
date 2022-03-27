import application.City;
import application.Gender;
import application.MobileCarrier;
import application.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.groupingBy;

public class GroupingTest {

    @DisplayName("유저목록을 성별로 그룹핑하라")
    @Test
    void grouping_test_1() {
        Map<Gender, List<User>> result = UserDTOData.유저_목록.stream()
                .collect(groupingBy(User::getGender));

        System.out.println(result.size());
    }
    
    @DisplayName("유저목록을 성별, 도시별로 그룹핑하라")
    @Test
    void grouping_test_2() {
        Map<Gender, Map<MobileCarrier, List<User>>> result = UserDTOData.유저_목록.stream()
                .collect(groupingBy(User::getGender, groupingBy(User::getCity)));

        System.out.println(result.size());
    }
}


final class UserDTOData {
    static final User 유저_1 = User.builder()
            .id(1L)
            .name("user1")
            .gender(Gender.FEMALE)
            .age(25)
            .city(City.NEW_YORK)
            .build();

    static final User 유저_2 = User.builder()
            .id(2L)
            .name("user2")
            .gender(Gender.MALE)
            .age(20)
            .city(City.TOKYO)
            .build();

    static final User 유저_3 = User.builder()
            .id(3L)
            .name("user3")
            .gender(Gender.FEMALE)
            .age(23)
            .city(City.SEOUL)
            .build();

    static final User 유저_4 = User.builder()
            .id(4L)
            .name("user4")
            .gender(Gender.MALE)
            .age(35)
            .city(City.LA)
            .build();

    static final User 유저_5 = User.builder()
            .id(5L)
            .name("user5")
            .gender(Gender.FEMALE)
            .age(28)
            .city(City.LA)
            .build();

    static List<User> 유저_목록 = List.of(유저_1,유저_2,유저_3,유저_4,유저_5);
}