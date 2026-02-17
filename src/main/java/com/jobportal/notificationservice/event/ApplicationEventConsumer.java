package com.jobportal.notificationservice.event;


import com.jobportal.notificationservice.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ApplicationEventConsumer {

    private final NotificationService notificationService;

    @KafkaListener(
            topics = "application-events",
            groupId  = "notification-group"
    )
    public void consume(ApplicationEvent event) {

        notificationService.saveNotification(event);
    }
}
