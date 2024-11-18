package com.memopet.memopet.global.common.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.memopet.memopet.global.common.dto.AccessLogDto;
import com.memopet.memopet.global.common.entity.AccessLog;
import com.memopet.memopet.global.common.exception.BadRequestRuntimeException;
import com.memopet.memopet.global.common.repository.AccessLogRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.ConcurrentLinkedQueue;

@Service
@RequiredArgsConstructor
@Slf4j
public class AccessLogRabbitConsumer {

    //@Qualifier("secondaryTransactionManager")
    //private final PlatformTransactionManager secondaryTransactionManager;

    private final AccessLogRepository accessLogRepository;
    private final ConcurrentLinkedQueue<AccessLog> messages = new ConcurrentLinkedQueue<>();
    private final ModelMapper modelMapper;
    private final ObjectMapper objectMapper;

    @RabbitListener(queues = "#{directQueue.name}")
    @Transactional(readOnly = false)
    public void consumeSub(String accessLogDtoStr) {
        AccessLogDto accessLogDto;
        try {
            // fixme 매번 ObjectMapper를 생성하는 것은 비효율적이므로, ObjectMapper를 Bean으로 등록하여 사용하거나 ObjectMapper를 static으로 선언하여 사용하는 것이 좋다.
            accessLogDto = objectMapper.readValue(accessLogDtoStr, AccessLogDto.class);
        } catch (JsonProcessingException e) {
            throw new BadRequestRuntimeException(e.getMessage());
        }

        AccessLog accesslog = modelMapper.map(accessLogDto, AccessLog.class);
        messages.add(accesslog);

    }
    @Scheduled(fixedRate = 1000)
    public void processMessages() {
        int size = messages.size();
        if (size > 0) {
            while (!messages.isEmpty()) {
                AccessLog accessLog = messages.poll();
                if (accessLog != null) {
                    accessLogRepository.save(accessLog);
                }
            }
        } else {
            log.error("No messages to process.");
        }
    }

    // 간단하게 다시 작성해봤습니다.
    // todo : 매번 저장하기 보다는 모아서 저장해보는걸 추천합니다. 예) 0.5초마다 한번씩 모아서 저장하기.
//    @RabbitListener(queues = "#{directQueue.name}")
//    public void consumeSubV2(String accessLogDtoStr) {
//        try {
//            final String name = Thread.currentThread().getName();
//            //log.info("ConsumeSub received, message: {}", accessLogDtoStr);
//            AccessLog accessLog = Utils.toObject(accessLogDtoStr, AccessLog.class);
//            accessLogRepository.save(accessLog);
//        }catch (Exception e) {
//            log.error("ConsumeSub error: {}", e.getMessage());
//            throw new BadRequestRuntimeException(e.getMessage());
//        }
//    }

}
