package com.example.lockexample.redisson.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

@Aspect
@Component
@Slf4j
public class DistributeLockAop {
    private static String REDISSON_KEY_PREFIX = "RLOCK_";

    private RedissonClient redissonClient;

    public DistributeLockAop(RedissonClient redissonClient) {
        this.redissonClient = redissonClient;
    }

    @Around("@annotation(com.example.lockexample.redisson.aop.DistributeLock)")
    public Object lock(final ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        DistributeLock distributeLock = method.getAnnotation(DistributeLock.class);

        String key = REDISSON_KEY_PREFIX + CustomSpringELParser.getDynamicValue(signature.getParameterNames(), joinPoint.getArgs(), distributeLock.key());
        log.info("lockKey {}" , key);

        RLock rLock = redissonClient.getLock(key);
        TimeUnit timeUnit = (TimeUnit) CustomSpringELParser.getDynamicValue(signature.getParameterNames(), joinPoint.getArgs(), "timeUnit");

        try {
            boolean available = rLock.tryLock(4L, 3L, timeUnit);
            if (!available) {
                return false;
            }

            return joinPoint.proceed();
        } catch (InterruptedException e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
        } finally {
            rLock.unlock();
        }

        return joinPoint.proceed();
    }
}
