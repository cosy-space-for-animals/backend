//package com.memopet.memopet.global.config.log;
//
//import org.aopalliance.intercept.MethodInterceptor;
//import org.aopalliance.intercept.MethodInvocation;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//public class ConnectionInterceptor implements MethodInterceptor {
//    private static final Logger logger = LoggerFactory.getLogger("SQLLogger");
//
//    @Override
//    public Object invoke(MethodInvocation invocation) throws Throwable {
//        String methodName = invocation.getMethod().getName();
//        if ("prepareStatement".equals(methodName)) {
//            String sql = (String) invocation.getArguments()[0];
//            logSqlIfNeeded(sql);
//        }
//        return invocation.proceed();
//    }
//
//    private void logSqlIfNeeded(String sql) {
//        if (sql.trim().toUpperCase().startsWith("UPDATE") || sql.trim().toUpperCase().startsWith("INSERT")) {
//            logger.debug(sql);
//        }
//    }
//}
//
