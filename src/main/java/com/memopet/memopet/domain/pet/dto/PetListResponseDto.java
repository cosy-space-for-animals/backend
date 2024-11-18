package com.memopet.memopet.domain.pet.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PetListResponseDto {
    private Long petId;
    private String petName;
    private String petProfileUrl;
    private boolean isActive;
}
