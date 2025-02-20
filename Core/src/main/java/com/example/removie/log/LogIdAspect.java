package com.example.removie.log;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.jboss.logging.MDC;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Aspect
@Component
public class LogIdAspect {

    @Before("@annotation(com.example.removie.log.LogGroup)")
    public void setLogId(){
        String id = UUID.randomUUID().toString();
        MDC.put("LogId", id);
    }

    @After("@annotation(com.example.removie.log.LogGroup)")
    public void clearLogId() {
        MDC.clear();
    }
}
