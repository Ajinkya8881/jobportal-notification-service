package com.jobportal.notificationservice.entity;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "notification")
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long jobId;

    private Long applicantId;

    private Long employerId;

    private String eventType;

    private String status;

    private LocalDateTime createdAt;

    @Column(name = "is_read")
    private boolean read;
}
