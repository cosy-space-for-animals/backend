package com.memopet.memopet.domain.pet.repository;

import com.memopet.memopet.domain.pet.entity.Species;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SpeciesRepository extends JpaRepository<Species, Long> {
    Optional<Species> findByLargeCategory(String largeCategory);
    Optional<Species> findByMidCategory(String midCategory);
    Optional<Species> findBySmallCategory(String smallCategory);
}
