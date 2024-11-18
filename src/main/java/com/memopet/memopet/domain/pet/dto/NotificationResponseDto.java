package com.memopet.memopet.domain.pet.dto;

import com.memopet.memopet.domain.pet.entity.NotificationType;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NotificationResponseDto {

    private Long notificationId;

    private Long receiver;

    private Long sender;

    private String imgUrl;

    private String followYn;

    private NotificationType notificationType;

    private String createdDate;
}
