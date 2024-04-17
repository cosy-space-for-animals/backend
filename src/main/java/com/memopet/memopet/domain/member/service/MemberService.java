package com.memopet.memopet.domain.member.service;


import com.memopet.memopet.domain.member.dto.*;
import com.memopet.memopet.domain.member.entity.Member;
import com.memopet.memopet.domain.member.repository.MemberRepository;
import com.memopet.memopet.domain.pet.entity.Comment;
import com.memopet.memopet.domain.pet.entity.Memory;
import com.memopet.memopet.domain.pet.entity.MemoryImage;
import com.memopet.memopet.domain.pet.entity.Pet;
import com.memopet.memopet.domain.pet.repository.CommentRepository;
import com.memopet.memopet.domain.pet.repository.MemoryImageRepository;
import com.memopet.memopet.domain.pet.repository.MemoryRepository;
import com.memopet.memopet.global.common.exception.BadRequestRuntimeException;
import com.memopet.memopet.global.common.service.S3Uploader;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = false)
public class MemberService  {

    private final MemberRepository memberRepository;
    private final CommentRepository commentRepository;
    private final MemoryRepository memoryRepository;
    private final MemoryImageRepository memoryImageRepository;
    private final S3Uploader s3Uploader;
    private final PasswordEncoder passwordEncoder;
    private final EntityManager em;

    /**
     * to deactivate the member status
     * @param email
     * @param deactivationReason
     * @param deactivationReasonComment
     * @return
     */
    public DeactivateMemberResponseDto deactivateMember(String email, String deactivationReason, String deactivationReasonComment) {

        // insert deleted_date, activated = false, deactivation_reason, deactivation_reason_comment
        Optional<Member> memberByEmail = memberRepository.findMemberByEmail(email);
        if(memberByEmail.isEmpty()) throw new BadRequestRuntimeException("User Not Found");

        Member member = memberByEmail.get();

        DeactivateMemberResponseDto deactivateMemberResponseDto;

        member.deactivateMember(LocalDateTime.now(),deactivationReason,deactivationReasonComment, false);

        // find pet info and insert deleted_date
        List<Pet> pets = member.getPets();
        List<Long> petIds = new ArrayList<>();
        for (Pet pet : pets) {
            pet.updateDeletedDate(LocalDateTime.now());
            petIds.add(pet.getId());
        }

        // memory
        List<Memory> memories = memoryRepository.findByPetIds(petIds);
        List<Long> memoryImageIds = new ArrayList<>();
        for (Memory memory : memories) {
            List<MemoryImage> memoryImages = memoryImageRepository.findByMemoryId(memory.getId());

            // delete uploaded images from aws s3.
            for(MemoryImage memoryImage : memoryImages) {
                memoryImageIds.add(memoryImage.getId());
                s3Uploader.deleteS3(memoryImage.getImageUrl());
            }
            memoryImageRepository.updateDeletedDate(memoryImageIds);

            memory.updateDeleteDate(LocalDateTime.now());
        }

        // comment deactivate
        List<Comment> commentsByPetIds = commentRepository.findCommentsByPetIds(pets);
        for (Comment comment : commentsByPetIds) {
            comment.updateDeleteDate(LocalDateTime.now());
        }


        deactivateMemberResponseDto = DeactivateMemberResponseDto.builder().dscCode("1").build();
        return deactivateMemberResponseDto;
    }

    public MemberProfileResponseDto getMemberProfile(String email) {
        Optional<Member> memberByEmail = memberRepository.findMemberByEmail(email);
        if(memberByEmail.isEmpty()) throw new BadRequestRuntimeException("User Not Found");

        Member member = memberByEmail.get();

        return MemberProfileResponseDto.builder().email(member.getEmail()).username(member.getUsername()).phoneNum(member.getPhoneNum()).build();
    }

    public MemberInfoResponseDto changeMemberInfo(MemberInfoRequestDto memberInfoRequestDto) {

        Optional<Member> memberByEmail = memberRepository.findMemberByEmail(memberInfoRequestDto.getEmail());
        if(memberByEmail.isEmpty()) throw new BadRequestRuntimeException("User Not Found");

        Member member = memberByEmail.get();

        if(memberInfoRequestDto.getDscCode() == 1) { // password changes
            member.changePassword(passwordEncoder.encode(memberInfoRequestDto.getPassword()));
        } else if(memberInfoRequestDto.getDscCode() == 2) { // username changes
            member.changeUsername(memberInfoRequestDto.getUsername());
        }else if(memberInfoRequestDto.getDscCode() == 3) { // cellphoneNum changes
            member.changePhoneNum(memberInfoRequestDto.getPhoneNum());
        }
        em.flush();
        em.clear();

        Optional<Member> savedMemberByEmail = memberRepository.findMemberByEmail(memberInfoRequestDto.getEmail());
        Member savedmember = savedMemberByEmail.get();

        return MemberInfoResponseDto.builder().dscCode("1").username(savedmember.getUsername()).phoneNum(savedmember.getPhoneNum()).email(savedmember.getEmail()).build();


    }
}
