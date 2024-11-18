package com.memopet.memopet.domain.pet.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class BlockRequestDto {
    @NotNull
    Long blockerPetId;
    @NotNull
    Long blockedPetId;

    public BlockRequestDto(Long petId, Long blockedPet) {
        this.blockerPetId = petId;
        this.blockedPetId = blockedPet;
    }


}
