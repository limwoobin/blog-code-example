package com.example.redisexample;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@DisplayName("기본 테스트")
@ExtendWith(MockitoExtension.class)
public class DefaultTest {

    @Test
    void calc() {
        int a = 3;
        int b = 2;

        Assertions.assertEquals(5, a + b);
    }
}
