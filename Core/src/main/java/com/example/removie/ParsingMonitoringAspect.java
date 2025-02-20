package com.example.removie;

import com.example.removie.document.DocConnection;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@Aspect
@Component
public class ParsingMonitoringAspect {
    private final static Logger logger = LoggerFactory.getLogger(ParsingMonitoringAspect.class);

    private final ConcurrentMap<String, ConcurrentHashMap<String, Integer>> trackingMap = new ConcurrentHashMap<>();

    @Around("execution(* com.example.removie.document.kobis.KOBISDocConnect.responseDoc(..))")
    public Object logParsingCalls(ProceedingJoinPoint joinPoint) throws Throwable {
        Object[] args = joinPoint.getArgs();
        if (args.length > 0 && args[0] instanceof DocConnection docConnection) {
            String url = docConnection.getUrl();

            String className = joinPoint.getTarget().getClass().getSimpleName();
            trackingMap.computeIfAbsent(className, k -> new ConcurrentHashMap<>())
                    .merge(url, 1, Integer::sum);
        }

        return joinPoint.proceed();
    }

    @Around("execution(* com.example.removie.update.UpdateService.runUpdate*(..))")
    public Object logParsingCallsAfterFacadeExecution(ProceedingJoinPoint joinPoint) throws Throwable {
        String className = joinPoint.getTarget().getClass().getSimpleName();
        Object result = joinPoint.proceed();
        ConcurrentHashMap<String, Integer> calls = trackingMap.remove(className);

        if (calls != null && !calls.isEmpty()) {
            logger.info("클래스 : `{}` 부터 호출된 요청 :", className);
            calls.forEach((url, count) -> logger.info("  - 요청 URL: {} ({}회 호출됨)", url, count));
        }
        return result;
    }
}
