package com.example.spelexample.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Aspect
@Component
@Slf4j
public class CustomAnnotationAop {

    @Around("@annotation(com.example.spelexample.aop.CustomAnnotation)")
    public Object aopCall(final ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        CustomAnnotation customAnnotation = method.getAnnotation(CustomAnnotation.class);

        String value = (String) CustomSpELParser.getDynamicValue(signature.getParameterNames(), joinPoint.getArgs(), customAnnotation.value());
        log.info("aop value ##### {}", value);

        return joinPoint.proceed();
    }
}
