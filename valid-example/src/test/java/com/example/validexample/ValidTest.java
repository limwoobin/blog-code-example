package com.example.validexample;

import com.example.validexample.user.controller.UserController;
import com.example.validexample.user.domain.UserRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
public class ValidTest {
    @Autowired
    MockMvc mockMvc;

    @Test
    @DisplayName("Valid 조건에 맞지 않는 파라미터를 넘기면 실패해야 한다")
    void validTest() throws Exception {
        // given
        UserRequest userRequest = UserRequest.builder()
                .email("drogba02")
                .name("woobeen")
                .age(29)
                .build();

        // then
        mockMvc.perform(get("/user")
                .param("email" , userRequest.getEmail())
                .param("name" , userRequest.getName())
                .param("age" , Integer.toString(userRequest.getAge())))
                .andExpect(status().isCreated());
    }

    @Test
    @DisplayName("Valid 조건에 맞는 파라미터를 넘기면 성공해야 한다")
    void validTest2() throws Exception {
        // given
        UserRequest userRequest = UserRequest.builder()
                .email("drogba02@naver.com")
                .name("woobeen")
                .age(29)
                .build();

        // then
        mockMvc.perform(get("/user")
                        .param("email" , userRequest.getEmail())
                        .param("name" , userRequest.getName())
                        .param("age" , Integer.toString(userRequest.getAge())))
                .andExpect(status().isCreated());
    }
}

//    Member member = Member.builder().name("Tom").build();
//    Gson gson = new Gson();
//    String content = gson.toJson(member);
//
//        mvc.perform(post("/api/member")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(content))
//                .andExpect(status().isCreated());
//
//                verify(memberService).insert(member);