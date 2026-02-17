package com.jobportal.notificationservice.event;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApplicationEvent {

    private String eventType;
    private Long jobId;
    private Long applicantId;
    private Long employerId;
    private String status;
}
