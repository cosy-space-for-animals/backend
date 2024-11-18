//package com.memopet.memopet.global.config;
//
//
//import org.aspectj.lang.annotation.AfterReturning;
//import org.aspectj.lang.annotation.Aspect;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.stereotype.Component;
//
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.SQLException;
//
//@Aspect
//@Component
//public class SqlLoggingAspect {
//
//    private static final Logger logger = LoggerFactory.getLogger("SQLLogger");
//
//    @AfterReturning(pointcut = "execution(* javax.sql.DataSource.getConnection(..))", returning = "connection")
//    public void logSql(Connection connection) {
//        // 래핑된 Connection에서 SQL 로그를 캡처
//        try {
//            Connection wrappedConnection = new ConnectionWrapper(connection);
//            // SQL을 실행하는 코드에서 래핑된 Connection을 사용하게 해야 합니다.
//        } catch (SQLException e) {
//            logger.error("Error wrapping the connection", e);
//        }
//    }
//
//    private class ConnectionWrapper extends ConnectionDelegate {
//        public ConnectionWrapper(Connection connection) throws SQLException {
//            super(connection);
//        }
//
//        @Override
//        public PreparedStatement prepareStatement(String sql) throws SQLException {
//            logSqlIfNeeded(sql);
//            return super.prepareStatement(sql);
//        }
//
//    }
//
//    private void logSqlIfNeeded(String sql) {
//
//        if (sql.trim().toUpperCase().startsWith("UPDATE") || sql.trim().toUpperCase().startsWith("INSERT")) {
//            logger.debug(sql);
//        }
//    }
//}
