package com.example.lockexample.redisson.exception;

public class ExceptionIgnore extends Exception {

    /**
     * 분산락 커스텀 예외를 무시하기 위한 생성자
     * @param s
     */
    public ExceptionIgnore(String s) {
        super(s);
    }
}
