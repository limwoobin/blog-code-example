package com.example.springtestexample.cache;

import com.example.springtestexample.ApplicationContextInitListener;
import com.example.springtestexample.IntegrationTest;
import com.example.springtestexample.config.MyService;
import org.junit.jupiter.api.Test;
import org.mockito.internal.util.MockUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;

@IntegrationTest
public class IntegrationCacheTest {

    @Autowired
    private MyService myService;

    @Autowired
    private MyService mockMyService;

    @Autowired
    private MyService spyMyService;

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private ApplicationContextInitListener initListener;

    @Test
    void test() {
        System.out.println("myService: " + myService);
        System.out.println("mockMyService: " + mockMyService);
        System.out.println("spyMyService: " + spyMyService);

        System.out.println("applicationContext.hashCode(): " + applicationContext.hashCode());
        System.out.println("context init Count: " + initListener.getContextInitCount());

        assertThat(MockUtil.isMock(myService)).isFalse();
        assertThat(MockUtil.isSpy(myService)).isFalse();
        assertThat(MockUtil.isMock(mockMyService)).isTrue();
        assertThat(MockUtil.isSpy(spyMyService)).isTrue();
    }

}
