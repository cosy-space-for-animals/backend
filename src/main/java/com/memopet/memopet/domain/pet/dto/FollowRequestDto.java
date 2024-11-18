package com.memopet.memopet.domain.pet.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class FollowRequestDto {
    private Long petId;
    private Long followingPetId;

    public FollowRequestDto(Long petId, Long followingPetId) {
        this.petId = petId;
        this.followingPetId = followingPetId;
    }
}
