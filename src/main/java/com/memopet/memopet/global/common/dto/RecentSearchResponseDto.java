package com.memopet.memopet.global.common.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RecentSearchResponseDto {

    private int dataCounts;

    private List<String> searchTexts;
}
