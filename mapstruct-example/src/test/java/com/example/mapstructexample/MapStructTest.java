package com.example.mapstructexample;

import com.example.mapstructexample.application.controller.UserDTO;
import com.example.mapstructexample.application.domain.User;
import com.example.mapstructexample.application.mapper.UserMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import static com.example.mapstructexample.MapStructTestDomain.테스트_DTO;
import static com.example.mapstructexample.MapStructTestDomain.테스트_유저;
import static org.assertj.core.api.Assertions.assertThat;

public class MapStructTest {
    private final UserMapper userMapper = Mappers.getMapper(UserMapper.class);

    @DisplayName("DTO 를 전달하면 Entity 로 정상적으로 매핑되어야 한다")
    @Test
    void mapper_test_1() {
        User user = userMapper.toUser(테스트_DTO);

        assertThat(user.getId()).isNull();
        assertThat(user.getName()).isEqualTo(테스트_DTO.getName());
        assertThat(user.getAge()).isEqualTo(테스트_DTO.getAge());
    }

    @DisplayName("Entity 를 전달하면 DTO 로 정상적으로 매핑되어야 한다")
    @Test
    void mapper_test_2() {
        UserDTO userDTO = userMapper.toUserDTO(테스트_유저);

        assertThat(userDTO.getId()).isEqualTo(테스트_유저.getId());
        assertThat(userDTO.getName()).isEqualTo(테스트_유저.getName());
        assertThat(userDTO.getAge()).isEqualTo(테스트_유저.getAge());
        assertThat(userDTO.getAddress()).isNull();
    }
}

final class MapStructTestDomain {
    static final User 테스트_유저 = new User(1L , "테스트" , 15);
    static final User 테스트_유저2 = new User(2L , "테스트2" , 22);

    static final UserDTO 테스트_DTO = UserDTO.builder()
            .name("테스트_DTO")
            .age(15)
            .build();
}