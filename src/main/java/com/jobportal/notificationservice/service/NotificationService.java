package com.jobportal.notificationservice.service;


import com.jobportal.notificationservice.dto.NotificationResponse;
import com.jobportal.notificationservice.entity.Notification;
import com.jobportal.notificationservice.event.ApplicationEvent;
import com.jobportal.notificationservice.repository.NotificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

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

    private NotificationResponse mapToResponse(Notification notification){
        return NotificationResponse.builder()
                .id(notification.getId())
                .jobId(notification.getJobId())
                .applicantId(notification.getApplicantId())
                .employerId(notification.getEmployerId())
                .eventType(notification.getEventType())
                .status(notification.getStatus())
                .createdAt(notification.getCreatedAt())
                .read(notification.isRead())
                .build();
    }

    public List<NotificationResponse> getAllNotifications(){
        return notificationRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    public void markAsRead(Long id){

        Notification notification = notificationRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Notification not found"));

        notification.setRead(true);

        notificationRepository.save(notification);
    }
}
