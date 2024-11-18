package com.memopet.memopet.domain.pet.repository;

import com.memopet.memopet.domain.pet.dto.BlockedListResponseDto;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.memopet.memopet.domain.pet.entity.QBlocked.blocked;

@Repository
public class CustomBlockRepositoryImpl implements CustomBlockRepository{
    private final JPAQueryFactory queryFactory;

    public CustomBlockRepositoryImpl(EntityManager entityManager) {
        this.queryFactory = new JPAQueryFactory(entityManager);
    }
    public Page<BlockedListResponseDto> findBlockedPets(@Param("blockerPetId") Long blockerPetId, Pageable pageable){
        List<BlockedListResponseDto> content = queryFactory.select(
                        Projections.constructor(BlockedListResponseDto.class,
                                blocked.blockedPet.id,
                                blocked.blockedPet.petName,
                                blocked.blockedPet.petProfileUrl,
                                blocked.blockedPet.petDesc
                        ))
                .from(blocked)
                .where(blocked.blockerPetId.eq(blockerPetId),
                        blocked.blockedPet.deletedDate.isNull())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
        JPAQuery<Long> countQuery = queryFactory
                .select(blocked.count())
                .from(blocked)
                .where(blocked.blockerPetId.eq(blockerPetId),
                        blocked.blockedPet.deletedDate.isNull());
        return PageableExecutionUtils.getPage(content, pageable, countQuery::fetchOne);
    }



}
