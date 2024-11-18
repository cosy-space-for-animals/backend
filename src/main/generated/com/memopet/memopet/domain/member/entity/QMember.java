package com.memopet.memopet.domain.member.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMember is a Querydsl query type for Member
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMember extends EntityPathBase<Member> {

    private static final long serialVersionUID = -1070714820L;

    public static final QMember member = new QMember("member1");

    public final com.memopet.memopet.global.common.entity.QFirstCreatedEntity _super = new com.memopet.memopet.global.common.entity.QFirstCreatedEntity(this);

    public final DateTimePath<java.time.LocalDateTime> agreeDate = createDateTime("agreeDate", java.time.LocalDateTime.class);

    public final BooleanPath agreeYn = createBoolean("agreeYn");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdDate = _super.createdDate;

    public final StringPath deactivationReason = createString("deactivationReason");

    public final StringPath deactivationReasonComment = createString("deactivationReasonComment");

    public final DateTimePath<java.time.LocalDateTime> deletedDate = createDateTime("deletedDate", java.time.LocalDateTime.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    //inherited
    public final StringPath lastModifiedBy = _super.lastModifiedBy;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> lastModifiedDate = _super.lastModifiedDate;

    public final StringPath memberId = createString("memberId");

    public final ListPath<com.memopet.memopet.domain.pet.entity.Pet, com.memopet.memopet.domain.pet.entity.QPet> pets = this.<com.memopet.memopet.domain.pet.entity.Pet, com.memopet.memopet.domain.pet.entity.QPet>createList("pets", com.memopet.memopet.domain.pet.entity.Pet.class, com.memopet.memopet.domain.pet.entity.QPet.class, PathInits.DIRECT2);

    public final StringPath phoneNum = createString("phoneNum");

    public final StringPath username = createString("username");

    public QMember(String variable) {
        super(Member.class, forVariable(variable));
    }

    public QMember(Path<? extends Member> path) {
        super(path.getType(), path.getMetadata());
    }

    public QMember(PathMetadata metadata) {
        super(Member.class, metadata);
    }

}

