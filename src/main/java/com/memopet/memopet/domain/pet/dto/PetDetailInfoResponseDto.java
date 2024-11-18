package com.memopet.memopet.domain.pet.dto;

import com.memopet.memopet.domain.pet.entity.Gender;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PetDetailInfoResponseDto {

    private Long petId;
    private String petName;
    private String petBirthDate;
    private String petDeathDate;
    private String petProfileUrl;
    private String backImgUrl;
    private String petFavs;
    private String petFavs2;
    private String petFavs3;
    private String petFavsColour;
    private String petFavs2Colour;
    private String petFavs3Colour;
    private Gender petGender;
    private String petDesc;
    private int petProfileFrame;
    private int follow;
    private String followYN;

    private List<PetCommentResponseDto> petCommentResponseDto;
}
