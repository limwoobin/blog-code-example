//package com.example.mapstructexample;
//
//import com.example.mapstructexample.application.controller.AddressDTO;
//import com.example.mapstructexample.application.controller.UserDTO;
//import com.example.mapstructexample.application.domain.User;
//import com.example.mapstructexample.application.mapper.UserMapper;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.mapstruct.factory.Mappers;
//
//import static com.example.mapstructexample.MapStructTestDomain.테스트_DTO;
//import static com.example.mapstructexample.MapStructTestDomain.테스트_유저;
//import static org.assertj.core.api.Assertions.assertThat;
//
//public class MapStructTest {
//    private final UserMapper userMapper = Mappers.getMapper(UserMapper.class);
//
//    @DisplayName("DTO 를 전달하면 Entity 로 정상적으로 매핑되어야 한다")
//    @Test
//    void mapper_test_1() {
//        User user = userMapper.toUser(테스트_DTO);
//
//        assertThat(user.getId()).isNull();
//        assertThat(user.getName()).isEqualTo(테스트_DTO.getName());
//        assertThat(user.getAge()).isEqualTo(테스트_DTO.getAge());
//    }
//
//    @DisplayName("Entity 를 전달하면 DTO 로 정상적으로 매핑되어야 한다")
//    @Test
//    void mapper_test_2() {
//        UserDTO userDTO = userMapper.toUserDTO(테스트_유저);
//
//        assertThat(userDTO.getId()).isEqualTo(테스트_유저.getId());
//        assertThat(userDTO.getName()).isEqualTo(테스트_유저.getName());
//        assertThat(userDTO.getAge()).isEqualTo(테스트_유저.getAge());
//        assertThat(userDTO.getAddress()).isNull();
//    }
//
//    @DisplayName("DTO 로 매핑시 name 은 무시되어야 한다")
//    @Test
//    void mapper_test_3() {
//        UserDTO userDTO = userMapper.toUserDTO_v2(테스트_유저);
//
//        assertThat(userDTO.getId()).isEqualTo(테스트_유저.getId());
//        assertThat(userDTO.getAge()).isEqualTo(테스트_유저.getAge());
//        assertThat(userDTO.getName()).isNull();
//    }
//
//    @DisplayName("DTO 매핑시 Entity의 name 이 DTO의 address 로 매핑되어야 한다")
//    @Test
//    void mapper_test_4() {
//        UserDTO userDTO = userMapper.toUserDTO_v3(테스트_유저);
//
//        assertThat(userDTO.getId()).isEqualTo(테스트_유저.getId());
//        assertThat(userDTO.getAddress()).isEqualTo(테스트_유저.getName());
//    }
//
//    @DisplayName("Entity , String 을 전달하면 DTO에 병합되어야 한다")
//    @Test
//    void mapper_test_5() {
//        String 테스트_주소 = "test_address";
//        UserDTO userDTO = userMapper.toUserDTO_v4(테스트_유저 , 테스트_주소);
//
//        assertThat(userDTO.getId()).isEqualTo(테스트_유저.getId());
//        assertThat(userDTO.getName()).isEqualTo(테스트_유저.getName());
//        assertThat(userDTO.getAge()).isEqualTo(테스트_유저.getAge());
//        assertThat(userDTO.getAddress()).isEqualTo(테스트_주소);
//    }
//}
//
//final class MapStructTestDomain {
//    static final User 테스트_유저 = new User(1L , "테스트" , 15);
//    static final User 테스트_유저2 = new User(2L , "테스트2" , 22);
//
//    static final UserDTO 테스트_DTO = UserDTO.builder()
//            .name("테스트_DTO")
//            .age(15)
//            .build();
//
//    static final AddressDTO 테스트_주소_DTO = AddressDTO.builder()
//            .myAddress("test_address")
//            .build();
//}