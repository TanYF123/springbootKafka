package com.talone.springbootkafka.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class KafkaAop {


    @Pointcut("execution(* com.talone.springbootkafka.controller.*.*(..))")
    public void pointCut(){

    }

    @Before("pointCut()")
    public void before(JoinPoint joinPoint){
        Object[] args = joinPoint.getArgs();
        for (Object arg : args) {
            System.out.println("aop: "+arg);
            //TODO 可以在此将入参发送topic，当然这个时候就需要有一个定义一个入参的超类
        }
    }
}
