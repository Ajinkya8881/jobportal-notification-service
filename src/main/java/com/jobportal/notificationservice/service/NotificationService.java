package com.jobportal.notificationservice.service;


import com.jobportal.notificationservice.entity.Notification;
import com.jobportal.notificationservice.event.ApplicationEvent;
import com.jobportal.notificationservice.repository.NotificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class NotificationService {

    private final NotificationRepository notificationRepository;

    public void saveNotification(ApplicationEvent event){


        Notification notification = Notification.builder()
                .jobId(event.getJobId())
                .applicantId(event.getApplicantId())
                .employerId(event.getEmployerId())
                .eventType(event.getEventType())
                .status(event.getStatus())
                .createdAt(LocalDateTime.now())
                .read(false)
                .build();

        notificationRepository.save(notification);
    }
}
