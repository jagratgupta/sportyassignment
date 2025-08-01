# Sports Betting Settlement Service

This is a Java 8 Spring Boot backend application that simulates sports betting event outcome handling and bet settlement using Kafka and a mocked RocketMQ system.

---

## 🔧 Tech Stack
- Java 8
- Spring Boot 2.7.x
- Spring Web
- Spring Kafka
- Spring Data JPA
- H2 In-Memory Database
- Maven
- Lombok

---

## 🛠️ Project Structure

```plaintext
src/main/java/com/example/betting
├── BettingApplication.java                  # Main application class
├── controller/EventController.java          # REST API to publish event outcomes
├── kafka/KafkaEventConsumer.java            # Kafka consumer for event-outcomes
├── kafka/KafkaProducerService.java          # Kafka producer to send event-outcomes
├── rocketmq/RocketMQProducerMock.java       # Mock RocketMQ producer
├── rocketmq/RocketMQConsumerMock.java       # Mock RocketMQ consumer (optional)
├── model/Bet.java                           # Entity representing a bet
├── model/EventOutcome.java                  # DTO for event outcome
├── repository/BetRepository.java            # JPA repository for Bet entity
└── config/KafkaConfig.java (optional)       # For custom Kafka configuration

src/main/resources
├── application.yml                          # Spring Boot app config
└── data.sql (optional seed bets)
```

---

## 🚀 How to Run

### Prerequisites
- Java 8
- Maven
- Docker Desktop

### Start Kafka using Docker

```bash
#Initialize and run docker
docker run -d --name zookeeper -p 2181:2181 zookeeper

#Configure kafka on server and specified port no
docker run -d --name kafka -p 9092:9092 \
  -e KAFKA_ZOOKEEPER_CONNECT=host.docker.internal:2181 \
  -e KAFKA_ADVERTISED_LISTENERS=PLAINTEXT://localhost:9092 \
  -e KAFKA_LISTENER_SECURITY_PROTOCOL_MAP=PLAINTEXT:PLAINTEXT \
  -e KAFKA_BROKER_ID=1 \
  -e KAFKA_OFFSETS_TOPIC_REPLICATION_FACTOR=1 \
  confluentinc/cp-kafka:7.4.0

# Creating Kafka Topic
docker exec kafka kafka-topics \
  --create --topic event-outcomes \
  --bootstrap-server localhost:9092 \
  --replication-factor 1 \
  --partitions 1
```

### Run the Application

```bash
mvn clean install
mvn spring-boot:run
```

### Access H2 Console

- URL: http://localhost:8080/h2-console
- JDBC URL: `jdbc:h2:mem:testdb`
- User: `sample`

### Insert Sample Bet

```sql
INSERT INTO BET (BET_ID, USER_ID, EVENT_ID, EVENT_MARKET_ID, EVENT_WINNER_ID, BET_AMOUNT)
VALUES (1, 124, 1001, 501, 21, 12000.0);
```

### Test Event Outcome API

```bash
curl -X POST http://localhost:8080/events \
  -H "Content-Type: application/json" \
  -d '{
        "eventId": 1001,
        "eventName": "Team A vs Team B",
        "eventWinnerId": 21
     }'
```

✅ Watch console logs for Kafka and RocketMQ mock output.

---

## 🧾 Notes

- Kafka topic: `event-outcomes`
- RocketMQ is mocked via service layer logging
- Clean architecture and separation of concerns followed