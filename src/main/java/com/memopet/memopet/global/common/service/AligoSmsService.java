package com.memopet.memopet.global.common.service;


import com.memopet.memopet.global.common.dto.RestResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Random;

@Service
@RequiredArgsConstructor
@Slf4j
public class AligoSmsService {

    @Value("${aligo.api.url}")
    private String apiUrl;

    @Value("${aligo.api.key}")
    private String apiKey;

    @Value("${aligo.user.id}")
    private String userId;

    @Value("${aligo.sender.phone}")
    private String senderPhone;

    private final RestTemplate restTemplate;


    public RestResult sendSms(String recipientPhone) {
        RestTemplate restTemplate = new RestTemplate();
        String url = "https://apis.aligo.in/send/";

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/x-www-form-urlencoded");

        String authNum = generateRandomNumber();

        // http 스팩에서 para으로 같은 키로 여러값이 들어올수 있음. 테스트 진행
        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("key", apiKey);
        body.add("user_id", userId);
        body.add("sender", senderPhone);
        body.add("receiver",recipientPhone );
        body.add("msg", authNum);


        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(body, headers);

        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, requestEntity, String.class);

        //log.info(response);

        HashMap<String, String> data = new HashMap<>();
        data.put("AuthNum", authNum);

        RestResult result = new RestResult(authNum);

        return result;
    }

    private String generateRandomNumber() {
        Random random = new Random();
        int min = 100000;
        int max = 999999;
        return String.valueOf(random.nextInt((max - min) + 1) + min);
    }
}
