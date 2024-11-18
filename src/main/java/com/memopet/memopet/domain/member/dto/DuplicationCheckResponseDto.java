package com.memopet.memopet.domain.member.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DuplicationCheckResponseDto {

    private String dscCode;

    private String errMessage;
}
