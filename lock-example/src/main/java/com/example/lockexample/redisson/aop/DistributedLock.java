package com.example.lockexample.redisson.aop;

import com.example.lockexample.redisson.exception.ExceptionIgnore;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.concurrent.TimeUnit;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface DistributedLock {

    /**
     * 락의 이름
     */
    String key();

    /**
     * 락의 시간 단위
     */
    TimeUnit timeUnit() default TimeUnit.SECONDS;

    /**
     * 락 획득을 기다리는 시간
     */
    long waitTime() default 5L;

    /**
     * 락을 점유하는 시간 (이 시간이 지나면 해제됨)
     */
    long leaseTime() default 3L;

    /**
     * 예외 클래스
     * (default 는 예외를 발생시키지 않음)
     */
    Class<? extends Exception> exceptionClass() default ExceptionIgnore.class;

    /**
     * 예외 메시지
     */
    String exceptionMessage() default "";
}
