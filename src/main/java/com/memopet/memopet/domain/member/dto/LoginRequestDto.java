package com.memopet.memopet.domain.member.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequestDto {
    @NotEmpty(message = "User email must not be empty")
    private String email;
    @NotEmpty(message = "Password must not be empty")
    private String password;

}
