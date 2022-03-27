import application.Gender;
import application.MobileCarrier;
import application.UserDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.groupingBy;

public class GroupingTest {

    @DisplayName("유저목록을 성별로 그룹핑하라")
    @Test
    void grouping_test_1() {
        Map<Gender, List<UserDTO>> result = UserDTOData.유저_목록.stream()
                .collect(groupingBy(UserDTO::getGender));

        System.out.println(result.size());
    }
    
    @DisplayName("유저목록을 성별, 통신사별로 그룹핑하라")
    @Test
    void grouping_test_2() {
        Map<Gender, Map<MobileCarrier, List<UserDTO>>> result = UserDTOData.유저_목록.stream()
                .collect(groupingBy(UserDTO::getGender, groupingBy(UserDTO::getMobileCarrier)));

        System.out.println(result.size());
    }
}


final class UserDTOData {
    static final UserDTO 유저_1 = UserDTO.builder()
            .id(1L)
            .name("user1")
            .gender(Gender.FEMALE)
            .age(25)
            .mobileCarrier(MobileCarrier.KT)
            .build();

    static final UserDTO 유저_2 = UserDTO.builder()
            .id(2L)
            .name("user2")
            .gender(Gender.MALE)
            .age(20)
            .mobileCarrier(MobileCarrier.SKT)
            .build();

    static final UserDTO 유저_3 = UserDTO.builder()
            .id(3L)
            .name("user3")
            .gender(Gender.FEMALE)
            .age(23)
            .mobileCarrier(MobileCarrier.KT)
            .build();

    static final UserDTO 유저_4 = UserDTO.builder()
            .id(4L)
            .name("user4")
            .gender(Gender.MALE)
            .age(35)
            .mobileCarrier(MobileCarrier.LG)
            .build();

    static final UserDTO 유저_5 = UserDTO.builder()
            .id(5L)
            .name("user5")
            .gender(Gender.FEMALE)
            .age(28)
            .mobileCarrier(MobileCarrier.KT)
            .build();

    static List<UserDTO> 유저_목록 = List.of(유저_1,유저_2,유저_3,유저_4,유저_5);
}