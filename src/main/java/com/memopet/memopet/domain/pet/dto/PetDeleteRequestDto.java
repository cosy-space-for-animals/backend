package com.memopet.memopet.domain.pet.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PetDeleteRequestDto {
    @NotNull
    private Long petId;

    @Email
    private String email;

    @NotEmpty(message = "Password must not be empty")
    private String password;

    public PetDeleteRequestDto(Long petId, String email, String password) {
        this.petId = petId;
        this.email = email;
        this.password = password;
    }
}
