package com.memopet.memopet.global.common.service;


import com.memopet.memopet.domain.member.service.SequenceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static com.memopet.memopet.global.config.RabbitMQMemberCreationDirectConfig.MEMBER_CREATION_DIRECT_QUEUE_NAME;

@Service
@RequiredArgsConstructor
@Slf4j
public class MemberCreationRabbitConsumer {
    private final SequenceService sequenceService;

    // mq로 보내는 메소드에서 transaction이 걸려져있어서 아래의 transaction을 감싸는 형태가 된다. 무조건 한곳에서 저장해야지 혹시모를 오류를 방지할수있다.
    @RabbitListener(queues = MEMBER_CREATION_DIRECT_QUEUE_NAME)
    public String consumeSub() {
        log.info("MemberCreationRabbitConsumer start");
        String memberId = generateUniqueId();
        log.info("memberId at consumeSub: {}", memberId);
        return memberId;
    }


    public String generateUniqueId() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSSSSS");
        String dateTime = LocalDateTime.now().format(formatter);

        return dateTime;
    }
}
