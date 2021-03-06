package com.example.validexample;

import com.example.validexample.user.controller.UserController;
import com.example.validexample.user.domain.UserRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.servlet.HttpEncodingAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
@Import(HttpEncodingAutoConfiguration.class)
public class ValidTest {
    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Nested
    @DisplayName("기본 Valid 테스트")
    class DefaultValidTest {

        @Test
        @DisplayName("Valid 조건에 맞지 않는 파라미터를 Get으로 넘기면 실패해야 한다")
        void validTest_get() throws Exception {
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
                    .andExpect(status().isBadRequest());
        }

        @Test
        @DisplayName("Valid 조건에 맞는 파라미터를 Get으로 넘기면 성공해야 한다")
        void validTest2_get() throws Exception {
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

        @Test
        @DisplayName("Valid 조건에 맞지 않는 파라미터를 Post로 넘기면 실패해야 한다")
        void validTest_post() throws Exception {
            // given
            UserRequest userRequest = UserRequest.builder()
                    .email("drogba02")
                    .name("woobeen")
                    .age(29)
                    .build();

            String jsonData = objectMapper.writeValueAsString(userRequest);

            // then
            mockMvc.perform(post("/user")
                    .content(jsonData)
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isBadRequest());
        }

        @Test
        @DisplayName("Valid 조건에 맞는 파라미터를 Post로 넘기면 성공해야 한다")
        void validTest2_post() throws Exception {
            // given
            UserRequest userRequest = UserRequest.builder()
                    .email("drogba02@naver.com")
                    .name("woobeen")
                    .age(29)
                    .build();

            String jsonData = objectMapper.writeValueAsString(userRequest);

            // then
            mockMvc.perform(post("/user")
                    .content(jsonData)
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isCreated());
        }
    }

    @Nested
    @DisplayName("BindResult Valid 테스트")
    class BindResultValidTest {

        static final String EMAIL_EXCEPTION_MESSAGE = "이메일 형식이 맞지 않습니다.";

        @Test
        @DisplayName("Valid 조건에 맞지 않는 파라미터를 Get으로 넘기면 status 400, 에러메시지를 응답받아야 한다")
        void validTest_get_fail() throws Exception {
            // given
            UserRequest userRequest = UserRequest.builder()
                    .email("drogba02")
                    .name("woobeen")
                    .age(29)
                    .build();

            // then
            mockMvc.perform(get("/user/v2")
                    .param("email" , userRequest.getEmail())
                    .param("name" , userRequest.getName())
                    .param("age" , Integer.toString(userRequest.getAge())))
                    .andExpect(status().isBadRequest())
                    .andExpect(content().string(EMAIL_EXCEPTION_MESSAGE));
        }

        @Test
        @DisplayName("Valid 조건에 맞는 파라미터를 Get으로 넘기면 성공해야 한다")
        void validTest_get_ok() throws Exception {
            // given
            UserRequest userRequest = UserRequest.builder()
                    .email("drogba02@naver.com")
                    .name("woobeen")
                    .age(29)
                    .build();

            // then
            mockMvc.perform(get("/user/v2")
                    .param("email" , userRequest.getEmail())
                    .param("name" , userRequest.getName())
                    .param("age" , Integer.toString(userRequest.getAge())))
                    .andExpect(status().isCreated())
                    .andExpect(content().string(""));
        }

        @Test
        @DisplayName("Valid 조건에 맞지 않는 파라미터를 Post로 넘기면 status 400, 에러메시지를 응답받아야 한다")
        void validTest_post_fail() throws Exception {
            // given
            UserRequest userRequest = UserRequest.builder()
                    .email("drogba02")
                    .name("woobeen")
                    .age(29)
                    .build();

            String jsonData = objectMapper.writeValueAsString(userRequest);

            // then
            mockMvc.perform(post("/user/v2")
                    .content(jsonData)
                    .contentType(MediaType.APPLICATION_JSON_VALUE)
                    .accept(MediaType.APPLICATION_JSON_VALUE))
                    .andExpect(status().isBadRequest())
                    .andExpect(content().string(EMAIL_EXCEPTION_MESSAGE));
        }

        @Test
        @DisplayName("Valid 조건에 맞는 파라미터를 Post로 넘기면 성공해야 한다")
        void validTest_post_ok() throws Exception {
            // given
            UserRequest userRequest = UserRequest.builder()
                    .email("drogba02@naver.com")
                    .name("woobeen")
                    .age(29)
                    .build();

            String jsonData = objectMapper.writeValueAsString(userRequest);

            // then
            mockMvc.perform(post("/user/v2")
                    .content(jsonData)
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isCreated())
                    .andExpect(content().string(""));
        }
    }

    @Nested
    @DisplayName("Valid Advice 테스트")
    class ValidAdviceTest {

        static final String EMAIL_EXCEPTION_MESSAGE = "이메일 형식이 맞지 않습니다.";
        static final String AGE_EXCEPTION_MESSAGE = "나이는 20~100세 사이의 사용자만 가입이 가능합니다.";

        @Test
        @DisplayName("PostMapping시 Valid 예외가 Advice 에서 정상적으로 처리되어야 한다")
        void advice_post_test() throws Exception {
            // given
            UserRequest userRequest = UserRequest.builder()
                    .email("drogba02")
                    .name("woobeen")
                    .age(18)
                    .build();

            String jsonData = objectMapper.writeValueAsString(userRequest);

            // then
            mockMvc.perform(post("/user")
                    .content(jsonData)
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isBadRequest())
                    .andExpect(content().string(containsString(EMAIL_EXCEPTION_MESSAGE)))
                    .andExpect(content().string(containsString(AGE_EXCEPTION_MESSAGE)))
                    .andDo(print());
        }

        @Test
        @DisplayName("GetMapping시 Valid 예외가 Advice 에서 정상적으로 처리되어야 한다")
        void advice_post_get() throws Exception {
            // given
            UserRequest userRequest = UserRequest.builder()
                    .email("drogba02")
                    .name("woobeen")
                    .age(18)
                    .build();

            // then
            mockMvc.perform(get("/user")
                    .param("email" , userRequest.getEmail())
                    .param("name" , userRequest.getName())
                    .param("age" , Integer.toString(userRequest.getAge())))
                    .andExpect(status().isBadRequest())
                    .andExpect(content().string(containsString(EMAIL_EXCEPTION_MESSAGE)))
                    .andExpect(content().string(containsString(AGE_EXCEPTION_MESSAGE)))
                    .andDo(print());
        }
    }
}