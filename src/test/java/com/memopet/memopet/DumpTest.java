package com.memopet.memopet;


import com.memopet.memopet.global.common.entity.AccessLog;
import com.memopet.memopet.global.common.repository.bulk.AccessLogBulkRepository;
import com.zaxxer.hikari.HikariDataSource;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.JdbcTemplate;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

public class DumpTest {

    @Test
    public void test1() {

        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/memopetdblocal?serverTimezone=UTC&characterEncoding=UTF-8");
        dataSource.setUsername("root");
        dataSource.setPassword("Ljh8812@");
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

        AccessLogBulkRepository accessLogBulkRepository = new AccessLogBulkRepository();
        accessLogBulkRepository.setJdbcTemplate(jdbcTemplate);

        List<AccessLog> accessLogList = new ArrayList<>();
        Random random = new Random();
        AccessLog accesslog = AccessLog.builder()
                .memberId(random.nextLong())
                .email("test" + random.nextInt(1000) + "@example.com")
                .threadId(UUID.randomUUID().toString())
                .host("host" + random.nextInt(1000) + ".example.com")
                .authorization("Bearer " + UUID.randomUUID().toString())
                .method("POST")
                .uri("/api/v1/resource/" + random.nextInt(100))
                .service("Service" + random.nextInt(10))
                .os("OS" + random.nextInt(10))
                .deviceClass("DeviceClass" + random.nextInt(10))
                .agentName("AgentName" + random.nextInt(10))
                .agentClass("AgentClass" + random.nextInt(10))
                .clientIp("192.168." + random.nextInt(256) + "." + random.nextInt(256))
                .elapsed(random.nextLong(1000))
                .request("Sample request payload")
                .response("Sample response payload")
                .status("200 OK")
                .deviceName("Device" + random.nextInt(10))
                .osName("OSName" + random.nextInt(10))
                .osVersion("v" + random.nextInt(10) + "." + random.nextInt(10))
                .userAgent("UserAgent" + random.nextInt(10))
                .referer("http://referer" + random.nextInt(1000) + ".com")
                .errorId(UUID.randomUUID().toString())
                .requestAt(LocalDateTime.now().minusSeconds(random.nextInt(1000)))
                .responseAt(LocalDateTime.now())
                .requestId(UUID.randomUUID().toString())
                .build();


//                for(int i = 0; i< 100000; i++) {
//                    accessLogList.add(accesslog);
//                }
//        accessLogBulkRepository.saveAll(accessLogList);
        for(int i = 0; i< 100000; i++) {


            accessLogList.add(accesslog);

            // 50,000건씩 배치로 나누어 저장
            if (accessLogList.size() == 50000) {
                accessLogBulkRepository.saveAll(accessLogList);
                accessLogList.clear(); // 리스트 초기화
            }
        }

        // 남아있는 데이터 저장
        if (!accessLogList.isEmpty()) {
            accessLogBulkRepository.saveAll(accessLogList);
        }
        //System.out.println("test");

    }
}
