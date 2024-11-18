package com.memopet.memopet.domain.pet.dto;

import com.querydsl.core.annotations.QueryEntity;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@QueryEntity
@AllArgsConstructor
public class BlockedListResponseDto {
    @NotNull
    private Long petId;

    @NotNull
    private String petName;

    @NotNull
    private String petProfileUrl;

    @NotNull
    private String petDesc;


}
