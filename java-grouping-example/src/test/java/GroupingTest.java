import application.City;
import application.Gender;
import application.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.groupingBy;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class GroupingTest {

    @DisplayName("유저목록을 성별로 그룹핑하면 MALE 5명, FEMALE 4명이 되어야한다")
    @Test
    void grouping_test_1() {
        // given
        List<User> 유저_목록 = UserData.users;

        // when
        Map<Gender, List<User>> result = 유저_목록.stream()
                .collect(groupingBy(User::getGender));

        // then
        List<User> 남자_회원_목록 = result.get(Gender.MALE);
        List<User> 여자_회원_목록 = result.get(Gender.FEMALE);

        assertEquals(남자_회원_목록.size(), 5);
        assertEquals(여자_회원_목록.size(), 4);
    }

    @DisplayName("유저목록을 성별, 도시별로 그룹핑하라")
    @Test
    void grouping_test_2() {
        // given
        List<User> 유저_목록 = UserData.users;

        // when
        Map<Gender, Map<City, List<User>>> result = 유저_목록.stream()
                .collect(groupingBy(User::getGender, groupingBy(User::getCity)));

        // then
        Map<City, List<User>> 남자_그룹 = result.get(Gender.MALE);
        Map<City, List<User>> 여자_그룹 = result.get(Gender.FEMALE);

        List<User> 남자_SEOUL_그룹 = 남자_그룹.get(City.SEOUL);
        List<User> 남자_PARIS_그룹 = 남자_그룹.get(City.PARIS);
        List<User> 남자_LA_그룹 = 남자_그룹.get(City.LA);

        List<User> 여자_SEOUL_그룹 = 여자_그룹.get(City.SEOUL);
        List<User> 여자_NEW_YORK_그룹 = 여자_그룹.get(City.NEW_YORK);
        List<User> 여자_TOKYO_그룹 = 여자_그룹.get(City.TOKYO);

        assertEquals(남자_SEOUL_그룹.size(), 1);
        assertEquals(남자_PARIS_그룹.size(), 2);
        assertEquals(남자_LA_그룹.size(), 2);

        assertEquals(여자_SEOUL_그룹.size(), 1);
        assertEquals(여자_NEW_YORK_그룹.size(), 1);
        assertEquals(여자_TOKYO_그룹.size(), 2);
    }
}


final class UserData {
    static final User 유저_1 = User.builder()
            .id(1L)
            .name("user1")
            .gender(Gender.MALE)
            .age(22)
            .city(City.SEOUL)
            .build();

    static final User 유저_2 = User.builder()
            .id(2L)
            .name("user2")
            .gender(Gender.FEMALE)
            .age(25)
            .city(City.SEOUL)
            .build();

    static final User 유저_3 = User.builder()
            .id(3L)
            .name("user3")
            .gender(Gender.FEMALE)
            .age(18)
            .city(City.NEW_YORK)
            .build();

    static final User 유저_4 = User.builder()
            .id(4L)
            .name("user4")
            .gender(Gender.MALE)
            .age(33)
            .city(City.PARIS)
            .build();

    static final User 유저_5 = User.builder()
            .id(5L)
            .name("user5")
            .gender(Gender.FEMALE)
            .age(30)
            .city(City.TOKYO)
            .build();

    static final User 유저_6 = User.builder()
            .id(6L)
            .name("user6")
            .gender(Gender.MALE)
            .age(25)
            .city(City.PARIS)
            .build();

    static final User 유저_7 = User.builder()
            .id(7L)
            .name("user7")
            .gender(Gender.MALE)
            .age(28)
            .city(City.LA)
            .build();

    static final User 유저_8 = User.builder()
            .id(8L)
            .name("user8")
            .gender(Gender.MALE)
            .age(29)
            .city(City.LA)
            .build();

    static final User 유저_9 = User.builder()
            .id(9L)
            .name("user9")
            .gender(Gender.FEMALE)
            .age(31)
            .city(City.TOKYO)
            .build();

    static List<User> users = List.of(유저_1,유저_2,유저_3,유저_4,유저_5,유저_6,유저_7,유저_8,유저_9);
}