package com.memopet.memopet.global.common.repository.bulk;


import com.memopet.memopet.global.common.entity.AccessLog;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.util.List;

public class AccessLogBulkRepository {

    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void saveAll(List<AccessLog> accessLogs) {
        String sql = """
                INSERT INTO access_log (
                    member_id,email,thread_id,host,authorization,method,uri,service,os,device_class,
                    agent_name,agent_class,client_ip,elapsed,request,response,status,device_name,os_name,os_version,
                    user_agent,referer,error_id,request_at,response_at,request_id
                    ) 
                values(
                    ?,?,?,?,?,?,?,?,?,?,
                    ?,?,?,?,?,?,?,?,?,?,
                    ?,?,?,?,?,?
                )
                """;


        jdbcTemplate.batchUpdate(sql, accessLogs,accessLogs.size(),
                (PreparedStatement ps, AccessLog log) -> {
                    ps.setLong(1, log.getMemberId());
                    ps.setString(2, log.getEmail());
                    ps.setString(3, log.getThreadId());
                    ps.setString(4, log.getHost());
                    ps.setString(5, log.getAuthorization());
                    ps.setString(6, log.getMethod());
                    ps.setString(7, log.getUri());
                    ps.setString(8, log.getService());
                    ps.setString(9, log.getService());
                    ps.setString(10, log.getDeviceClass());

                    ps.setString(11, log.getAgentName());
                    ps.setString(12, log.getAgentClass());
                    ps.setString(13, log.getClientIp());
                    ps.setLong(14, log.getElapsed());
                    ps.setString(15, log.getRequest());
                    ps.setString(16, log.getResponse());
                    ps.setString(17, log.getStatus());
                    ps.setString(18, log.getDeviceName());
                    ps.setString(19, log.getOsName());
                    ps.setString(20, log.getOsVersion());

                    ps.setString(21, log.getUserAgent());
                    ps.setString(22, log.getErrorId());
                    ps.setString(23, log.getReferer());
                    ps.setTimestamp(24, Timestamp.valueOf(log.getRequestAt()));
                    ps.setTimestamp(25, Timestamp.valueOf(log.getResponseAt()));
                    ps.setString(26, log.getRequestId());

                });
    }

}
