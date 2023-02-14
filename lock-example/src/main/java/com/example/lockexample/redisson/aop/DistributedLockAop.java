package com.example.lockexample.redisson.aop;

import com.example.lockexample.redisson.exception.ExceptionIgnore;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Component;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.time.LocalDateTime;

import static net.logstash.logback.argument.StructuredArguments.kv;

@Aspect
@Component
@RequiredArgsConstructor
@Slf4j
public class DistributedLockAop {
    private static final String REDISSON_KEY_PREFIX = "RLOCK_";

    private final RedissonClient redissonClient;
    private final AopForTransaction aopForTransaction;


    @Around("@annotation(com.example.lockexample.redisson.aop.DistributedLock)")
    public Object lock(final ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        DistributedLock distributedLock = method.getAnnotation(DistributedLock.class);

        String key = REDISSON_KEY_PREFIX + CustomSpringELParser.getDynamicValue(signature.getParameterNames(), joinPoint.getArgs(), distributedLock.key());

        RLock rLock = redissonClient.getLock(key);

        try {
            log.info("try Lock Time {}, ThreadId {}", LocalDateTime.now(), Thread.currentThread().getId());

            boolean available = rLock.tryLock(distributedLock.waitTime(), distributedLock.leaseTime(), distributedLock.timeUnit());
            if (!available) {
                throwException(distributedLock.exceptionClass(), distributedLock.exceptionMessage());
                return false;
            }

            log.info("get lock success {}" , key);
            return aopForTransaction.proceed(joinPoint);
        } catch (InterruptedException e) {
            throw new InterruptedException();
        } finally {
            try {
                rLock.unlock();
            } catch (IllegalMonitorStateException e) {
                log.info("Redisson Lock Already UnLock {} {}",
                        kv("serviceName", method.getName()),
                        kv("key", key)
                );
            }
        }
    }

    private void throwException(Class<? extends Exception> clazz, String message) throws Exception {
        Constructor<? extends Exception> constructor = clazz.getDeclaredConstructor(String.class);
        Exception exception = constructor.newInstance(message);

        if (exception instanceof ExceptionIgnore) {
            return;
        }

        throw exception;
    }
}
