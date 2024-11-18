package com.memopet.memopet.domain.pet.entity;

import com.memopet.memopet.global.common.entity.LastModifiedEntity;
import jakarta.persistence.*;
import lombok.*;


@Entity
@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Species extends LastModifiedEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pet_species_id")
    private Long id;

    @Column(name = "species_large_category",nullable = false)
    private String largeCategory;

    @Column(name = "species_mid_category")
    private String midCategory;

    @Column(name = "species_small_category",nullable = false)
    private String smallCategory;
}
