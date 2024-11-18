package com.memopet.memopet.domain.pet.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PetCommentResponseDto {

    private Long petId;
    private String petName;
    private String petProfileUrl;
    private Long commentId;
    private String comment;
    private String commentCreatedDate;

}
