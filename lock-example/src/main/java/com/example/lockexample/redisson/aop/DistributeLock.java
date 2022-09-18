package com.example.lockexample.redisson.aop;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.concurrent.TimeUnit;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface DistributeLock {
    String key();

    TimeUnit timeUnit() default TimeUnit.SECONDS;

    long waitTime() default 4L;

    long releaseTime() default 3L;
}
