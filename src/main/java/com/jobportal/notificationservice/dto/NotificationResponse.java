package com.jobportal.notificationservice.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class NotificationResponse {

    private Long id;
    private Long jobId;
    private Long applicantId;
    private Long employerId;
    private String eventType;
    private String status;
    private LocalDateTime createdAt;
    private boolean read;
}
