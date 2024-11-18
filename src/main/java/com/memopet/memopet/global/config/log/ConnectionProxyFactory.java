//package com.memopet.memopet.global.config.log;
//
//import org.springframework.aop.framework.ProxyFactory;
//
//import java.sql.Connection;
//
//public class ConnectionProxyFactory {
//
//    public static Connection wrapConnection(Connection originalConnection) {
//        ProxyFactory proxyFactory = new ProxyFactory();
//        proxyFactory.setTarget(originalConnection);
//        proxyFactory.addAdvice(new ConnectionInterceptor());
//
//        return (Connection) proxyFactory.getProxy();
//    }
//}
