//package com.memopet.memopet.global.config.log;
//
//
//import org.aspectj.lang.JoinPoint;
//import org.aspectj.lang.annotation.AfterReturning;
//import org.aspectj.lang.annotation.Aspect;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.stereotype.Component;
//
//@Aspect
//@Component
//public class SqlLoggingAspect {
//
//    private static final Logger logger = LoggerFactory.getLogger("SQLLogger");
//
//    @AfterReturning(pointcut = "execution(* com.memopet.memopet.*.*.service.*(..))")
//    public void logSqlAfterMethodExecution(JoinPoint joinPoint) {
//        // 메서드 이름과 인자를 기반으로 SQL 로그 남기기
//        String methodName = joinPoint.getSignature().getName();
//        Object[] args = joinPoint.getArgs();
//
//        for (Object arg : args) {
//            if (arg instanceof String) {
//                String sql = ((String) arg).trim().toUpperCase();
//                if (sql.startsWith("INSERT") || sql.startsWith("UPDATE")) {
//                    logger.debug("Method: " + methodName + " | SQL: " + arg);
//                }
//            }
//        }
//    }
//}