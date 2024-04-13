package com.memopet.memopet.domain.pet.controller;

import com.memopet.memopet.domain.pet.dto.LikePostORDeleteRequestDto;
import com.memopet.memopet.domain.pet.dto.LikePostORDeleteResponseDto;
import com.memopet.memopet.domain.pet.dto.LikedMemoryRequestDto;
import com.memopet.memopet.domain.pet.dto.LikedMemoryResponseDto;
import com.memopet.memopet.domain.pet.service.LikesService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class LikesController {

    private final LikesService likesService;

    @PreAuthorize("hasAuthority('SCOPE_USER_AUTHORITY')")
    @PostMapping("/likes")
    public LikePostORDeleteResponseDto postOrDeleteLikes(@RequestBody LikePostORDeleteRequestDto likePostORDeleteRequestDto) {
        LikePostORDeleteResponseDto likePostORDeleteResponseDto = likesService.postOrDeleteLike(likePostORDeleteRequestDto);
        return likePostORDeleteResponseDto;
    }
}