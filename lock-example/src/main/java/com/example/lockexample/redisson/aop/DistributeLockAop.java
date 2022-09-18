package com.example.lockexample.redisson.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class DistributeLockAop {
    private static String REDISSON_KEY_PREFIX = "RLOCK_";

    private RedissonClient redissonClient;

    public DistributeLockAop(RedissonClient redissonClient) {
        this.redissonClient = redissonClient;
    }

    @Around("@annotation(com.example.lockexample.redisson.aop.DistributeLock)")
    public Object lock(final ProceedingJoinPoint joinPoint) throws Throwable {
        return joinPoint.proceed();
    }
}
