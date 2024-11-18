package com.memopet.memopet.global.common.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ImageUploadDto {

    private String bucketName;
    private byte[] fileBytes;
    private String fileName;
    private String contentType;
    private String filePath;

}
