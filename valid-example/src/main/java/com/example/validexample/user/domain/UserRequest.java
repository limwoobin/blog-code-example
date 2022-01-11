package com.example.validexample.user.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserRequest {
    @Email(message = "이메일 형식이 맞지 않습니다.")
    @NotBlank(message = "이메일을 입력해주세요.")
    private String email;

    @NotBlank(message = "이름을 입력해주세요.")
    @Size(min = 2 , max = 5 , message = "이름은 2자 이상 , 5자 이하여야 합니다.")
    private String name;

    @Min(value = 20 , message = "나이는 20~100세 사이의 사용자만 가입이 가능합니다.")
    private Integer age;

    @Override
    public String toString() {
        return "UserRequest{" +
                "email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
