package com.memopet.memopet.domain.pet.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FollowListResponseDto {


    @JsonProperty("has_next")
    private boolean hasNext;
    @JsonProperty("current_page")
    private int currentPage;
    @JsonProperty("data_counts")
    private int dataCounts;
    private List<PetFollowingResponseDto> followList;
    @JsonProperty("dec_code")
    private char decCode;
    private String errorDescription;
}