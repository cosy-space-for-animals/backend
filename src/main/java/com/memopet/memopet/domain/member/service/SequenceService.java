package com.memopet.memopet.domain.member.service;

import com.memopet.memopet.domain.member.entity.Sequence;
import com.memopet.memopet.domain.member.repository.SequenceRepository;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class SequenceService {

    private final SequenceRepository sequenceRepository;
    private final EntityManager entityManager;
    public long getNextValue(String sequenceName) {
        Sequence sequence = sequenceRepository.findById(sequenceName)
                .orElse(new Sequence(sequenceName, 0));
        long nextValue = sequence.getValue() + 1;
        sequence.setValue(nextValue);
        sequenceRepository.save(sequence);
        entityManager.flush();
        entityManager.clear();

        return nextValue;
    }
}
