📬 Notification Service

Kafka-based microservice that consumes application events from the Job Portal main application and persists notifications into a dedicated MySQL database.

🚀 Tech Stack

Java 17

Spring Boot 3.5.x

Spring Data JPA

Apache Kafka

MySQL 8

Lombok

🏗 Architecture
Job Portal (Main App)
│
▼
Kafka Topic: application-events
│
▼
Notification Service
│
▼
MySQL (notification_db)

⚙️ Configuration
application.properties
spring.application.name=notification-service

server.port=8081

spring.datasource.url=jdbc:mysql://localhost:3307/notification_db
spring.datasource.username=root
spring.datasource.password=root

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.open-in-view=false

spring.kafka.bootstrap-servers=localhost:9092
spring.kafka.consumer.group-id=notification-group
spring.kafka.consumer.auto-offset-reset=earliest
spring.kafka.consumer.value-deserializer=org.springframework.kafka.support.serializer.JsonDeserializer
spring.kafka.consumer.key-deserializer=org.apache.kafka.common.serialization.StringDeserializer
spring.kafka.consumer.properties.spring.json.trusted.packages=*
spring.kafka.consumer.properties.spring.json.use.type.headers=false
spring.kafka.consumer.properties.spring.json.value.default.type=com.jobportal.notificationservice.event.ApplicationEvent

🗄 Database Setup
CREATE DATABASE notification_db;

▶ Running the Service
mvn clean install
mvn spring-boot:run


Service runs at:

http://localhost:8081

🧪 End-to-End Testing
1️⃣ Trigger Event (From Main Application)

Apply to a job via main app:

POST http://localhost:8080/applications/{jobId}
Authorization: Bearer <USER_TOKEN>

2️⃣ Verify Notification in Database
USE notification_db;
SELECT * FROM notification;

3️⃣ Fetch Notifications
GET http://localhost:8081/notifications

Response
[
{
"id": 1,
"jobId": 1,
"applicantId": 2,
"employerId": 1,
"eventType": "APPLICATION_SUBMITTED",
"status": null,
"createdAt": "2026-02-17T14:35:36.333736",
"read": false
}
]

4️⃣ Mark Notification as Read
PATCH http://localhost:8081/notifications/{id}/read


Example:

PATCH http://localhost:8081/notifications/1/read

📂 Project Structure
controller/
service/
repository/
entity/
dto/
event/

📦 Features

Kafka Consumer (@KafkaListener)

Event-to-Entity Mapping

MySQL Persistence

DTO-based Response

Mark-as-read Endpoint

Production-ready JPA configuration

🔗 Kafka Topic
application-events

🛠 Build
mvn clean package

📌 Author

Ajinkya Kolhe