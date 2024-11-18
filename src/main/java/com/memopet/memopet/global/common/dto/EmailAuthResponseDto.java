package com.memopet.memopet.global.common.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EmailAuthResponseDto {

    private String authCode;
    private long verificationStatusId;
}
