package com.memopet.memopet.global.common.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RecentSearchDeleteRequestDto {

    private Long petId;

    private String searchText;
}
