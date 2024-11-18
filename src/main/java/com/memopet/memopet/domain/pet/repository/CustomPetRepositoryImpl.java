package com.memopet.memopet.domain.pet.repository;

import com.memopet.memopet.domain.pet.dto.PetListResponseDto;
import com.memopet.memopet.domain.pet.dto.PetUpdateInfoRequestDto;
import com.memopet.memopet.domain.pet.entity.PetStatus;
import com.memopet.memopet.domain.pet.entity.QPet;
import com.memopet.memopet.global.common.service.S3Uploader;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.CaseBuilder;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.querydsl.jpa.impl.JPAUpdateClause;
import jakarta.persistence.EntityManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static com.memopet.memopet.domain.pet.entity.QPet.pet;

@Repository
@Slf4j
public class CustomPetRepositoryImpl implements CustomPetRepository{
    private final JPAQueryFactory queryFactory;
    private final S3Uploader s3Uploader;

    public CustomPetRepositoryImpl(EntityManager em,S3Uploader s3Uploader ) {
        this.queryFactory = new JPAQueryFactory(em);
        this.s3Uploader = s3Uploader;
    }


    @Override
    public List<PetListResponseDto> findPetsById(Long petId) {

        List<PetListResponseDto> content = queryFactory.select(
                        Projections.constructor(PetListResponseDto.class,
                                pet.id,
                                pet.petName,
                                pet.petProfileUrl))
                .from(QPet.pet)
                .where(pet.member.id.eq(
                        JPAExpressions.select(pet.member.id)
                                .from(pet)
                                .where(pet.id.eq(petId))
                        ))
                .where(pet.deletedDate.isNull())
                .orderBy(new CaseBuilder()
                        .when(pet.petStatus.eq(PetStatus.ACTIVE)).then(0)
                        .otherwise(1)
                        .asc())
                .fetch();
        return content;

    }



    @Override
    @Transactional
    public boolean switchPetProfile(Long petId) {

        long updatedCount = queryFactory.update(pet)
                .set(pet.petStatus,
                        Expressions.cases()
                                .when(pet.id.eq(petId)).then(PetStatus.ACTIVE)
                                .when(pet.petStatus.eq(PetStatus.ACTIVE)).then(PetStatus.DEACTIVE)
                                .otherwise(pet.petStatus))
                .where(pet.member.id.eq(JPAExpressions.select(pet.member.id).from(pet).where(pet.id.eq(petId))
                        .where(pet.deletedDate.isNull())
                ))
                .execute();
        return updatedCount > 0;
    }

    @Override
    public boolean deleteAPet(Long memberId, Long petId) {
        long updatedCount = queryFactory.update(pet)
                .set(pet.deletedDate, LocalDateTime.now())
                .where(pet.id.eq(petId))
                .where(pet.deletedDate.isNull())
                .execute();
        return updatedCount > 0;
    }

    @Override
    public void updateMemoryInfo(String petImgUrl, String backgroundImgUrl, PetUpdateInfoRequestDto petUpdateInfoRequestDto) {

        String petFavs = petUpdateInfoRequestDto.getPetFavs() != null && !petUpdateInfoRequestDto.getPetFavs().equals("") ? petUpdateInfoRequestDto.getPetFavs() : null;
        String petFavs2 = petUpdateInfoRequestDto.getPetFavs2() != null && !petUpdateInfoRequestDto.getPetFavs2().equals("") ? petUpdateInfoRequestDto.getPetFavs2() : null;
        String petFavs3 = petUpdateInfoRequestDto.getPetFavs3() != null && !petUpdateInfoRequestDto.getPetFavs3().equals("") ? petUpdateInfoRequestDto.getPetFavs3() : null;

        String petFav= null, petFav2= null, petFav3 = null;
        String petFavColour = null, petFavColour2= null,petFavColour3 = null;

        if(petFavs != null) {
            int i = petFavs.indexOf(",");
            petFav = petFavs.substring(0, i);
            petFavColour = petFavs.substring(i+1);
        }
        if(petFavs2 != null) {
            int i = petFavs2.indexOf(",");
            petFav2 = petFavs2.substring(0, i);
            petFavColour2 = petFavs2.substring(i+1);
        }
        if(petFavs3 != null) {
            int i = petFavs3.indexOf(",");
            petFav3 = petFavs3.substring(0, i);
            petFavColour3 = petFavs3.substring(i+1);
        }

        JPAUpdateClause clause  = queryFactory
                .update(pet);
        if(petNameEq(petUpdateInfoRequestDto.getPetName()) != null) {
            clause.set(pet.petName, petUpdateInfoRequestDto.getPetName());
        }
        if(petDescEq(petUpdateInfoRequestDto.getPetDesc()) != null) {
            clause.set(pet.petDesc, petUpdateInfoRequestDto.getPetDesc());
        }
        if(petBirthDateEq(petUpdateInfoRequestDto.getPetBirthDate()) != null) {
            clause.set(pet.petBirth, petUpdateInfoRequestDto.getPetBirthDate());
        }
        if(petDeathDateEq(petUpdateInfoRequestDto.getPetDeathDate()) != null) {
            clause.set(pet.petDeathDate, petUpdateInfoRequestDto.getPetDeathDate());
        }
        if(petProfileFrameEq(petUpdateInfoRequestDto.getPetProfileFrame()) != null) {
            clause.set(pet.petProfileFrame, petUpdateInfoRequestDto.getPetProfileFrame());
        }
        if(petFavEq(petFav) != null) {
            clause.set(pet.petFavs, petFav);
        }
        if(petFav2Eq(petFav2) != null) {
            clause.set(pet.petFavs2, petFav2);
        }
        if(petFav3Eq(petFav3) != null) {
            clause.set(pet.petFavs3, petFav3);
        }
        if(petFavColourEq(petFavColour) != null) {
            clause.set(pet.petFavsColour, petFavColour);
        }
        if(petFavColour2Eq(petFavColour2) != null) {
            clause.set(pet.petFavs2Colour, petFavColour2);
        }
        if(petFavColour3Eq(petFavColour3) != null) {
            clause.set(pet.petFavs3Colour, petFavColour3);
        }
        if(petImgUrlEq(petImgUrl) != null) {
            clause.set(pet.petProfileUrl, petImgUrl);
        }
        if(petBackImgUrlEq(backgroundImgUrl) != null) {
            clause.set(pet.backImgUrl, backgroundImgUrl);
        }
        clause.where(pet.id.eq(petUpdateInfoRequestDto.getPetId()));
        clause.execute();
    }

    private BooleanExpression petNameEq(String petName) {
        return petName != null ? pet.petName.eq(petName) : null;
    }
    private BooleanExpression petDescEq(String petDesc) {
        return petDesc != null ? pet.petDesc.eq(petDesc) : null;
    }
    private BooleanExpression petBirthDateEq(LocalDate petBirthDate) {
        return petBirthDate != null ? pet.petBirth.eq(petBirthDate) : null;
    }
    private BooleanExpression petDeathDateEq(LocalDate petDeathDate) {
        return petDeathDate != null ? pet.petDeathDate.eq(petDeathDate) : null;
    }
    private BooleanExpression petProfileFrameEq(Integer petProfileFrame) {
        return petProfileFrame != null ? pet.petProfileFrame.eq(petProfileFrame) : null;
    }
    private BooleanExpression petFavEq(String petFav) {
        return petFav != null ? pet.petFavs.eq(petFav) : null;
    }
    private BooleanExpression petFav2Eq(String petFav2) {
        return petFav2 != null ? pet.petFavs2.eq(petFav2) : null;
    }
    private BooleanExpression petFav3Eq(String petFav3) {
        return petFav3 != null ? pet.petFavs3.eq(petFav3) : null;
    }
    private BooleanExpression petFavColourEq(String petFavColour) {
        return petFavColour != null ? pet.petFavsColour.eq(petFavColour) : null;
    }
    private BooleanExpression petFavColour2Eq(String petFavColour2) {
        return petFavColour2 != null ? pet.petFavs2Colour.eq(petFavColour2) : null;
    }
    private BooleanExpression petFavColour3Eq(String petFavColour3) {
        return petFavColour3 != null ? pet.petFavs3Colour.eq(petFavColour3) : null;
    }

    private BooleanExpression petImgUrlEq(String petImgUrl) {
        return petImgUrl != null ? pet.petProfileUrl.eq(petImgUrl) : null;
    }
    private BooleanExpression petBackImgUrlEq(String backImgUrl) {
        return backImgUrl != null ? pet.backImgUrl.eq(backImgUrl) : null;
    }
}
