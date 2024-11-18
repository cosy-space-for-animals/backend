package com.memopet.memopet.domain.member.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MemberCreationDto {
    private String username;
    private String password;
    private String email;
    private String phoneNum;
    private String roleDscCode;
    private String memberId;
}
