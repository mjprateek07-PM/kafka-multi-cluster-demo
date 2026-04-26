# 🚀 Kafka Multi-Cluster Demo (Spring Boot + MirrorMaker 2)

## 📌 Overview

This project demonstrates a **production-like Kafka architecture** using:

* ✅ Two Kafka Clusters (Cluster A & Cluster B)
* ✅ Multi-broker setup (3 brokers per cluster)
* ✅ MirrorMaker 2.0 for cross-cluster replication
* ✅ Spring Boot Producer & Consumer
* ✅ MySQL integration for persistence

---

## 🏗️ Architecture

```
        ┌──────────────┐
        │  Producer    │  (Spring Boot API)
        └──────┬───────┘
               │
               ▼
        ┌──────────────┐
        │ Kafka A      │ (3 Brokers)
        └──────┬───────┘
               │
        MirrorMaker 2
               │
               ▼
        ┌──────────────┐
        │ Kafka B      │ (3 Brokers)
        └──────┬───────┘
               │
               ▼
        ┌──────────────┐
        │ Consumer     │ (Spring Boot)
        └──────┬───────┘
               │
               ▼
        ┌──────────────┐
        │ MySQL DB     │
        └──────────────┘
```

---

## 🎯 Features

* 🔁 Cross-cluster replication (A → B)
* ⚡ High throughput via partitions
* 🛡️ Fault tolerance with multiple brokers
* 🔄 Consumer group rebalancing
* 💾 Data persistence in MySQL
* 🚀 Scalable microservices design

---

## 🛠️ Tech Stack

* Java 17
* Spring Boot
* Apache Kafka
* Docker & Docker Compose
* MirrorMaker 2.0
* MySQL

---

## ⚙️ Prerequisites

* Docker Desktop (WSL enabled)
* Java 17+
* Maven
* Git

---

## 🚀 Setup & Run

### 1️⃣ Clone Repository

```bash
git clone https://github.com/<your-username>/kafka-multi-cluster-demo.git
cd kafka-multi-cluster-demo
```

---

### 2️⃣ Start Kafka & MySQL

```bash
cd docker
docker compose up -d
```

⏳ Wait ~60 seconds for cluster startup

---

### 3️⃣ Verify Kafka is Running

```bash
docker ps
```

---

### 4️⃣ Create Topic

```bash
docker exec -it docker-kafka-a1-1 kafka-topics \
--create \
--topic orders \
--bootstrap-server localhost:9092 \
--partitions 3 \
--replication-factor 1
```

---

### 5️⃣ Run Consumer

```bash
mvn spring-boot:run -Dspring-boot.run.profiles=consumer
```

---

### 6️⃣ Run Producer

```bash
mvn spring-boot:run -Dspring-boot.run.profiles=producer
```

---

### 7️⃣ Send Test Message

```bash
curl -X POST http://localhost:9090/orders -d "Hello Kafka"
```

---

## 🔍 Verification

### Check Topics

```bash
docker exec -it docker-kafka-a1-1 kafka-topics \
--list \
--bootstrap-server localhost:9092
```

---

### Consume Messages (Cluster B)

```bash
docker exec -it docker-kafka-b1-1 kafka-console-consumer \
--topic orders \
--from-beginning \
--bootstrap-server localhost:9096
```

---

## 📊 Use Cases Tested

### ✅ Fault Tolerance

* Kill broker → leader election observed

### ✅ High Availability

* Multi-broker setup ensures uptime

### ✅ Disaster Recovery

* Cluster B acts as backup

### ✅ Throughput Testing

* Multiple partitions for scaling

---

## ⚠️ Common Issues

### ❌ Connection Timeout

* Ensure Kafka is fully started (wait 60 sec)

### ❌ Port Already in Use

```bash
lsof -i :9090
kill -9 <PID>
```

### ❌ Topic Creation Failure

* Use `replication-factor=1` for local setup

---

## 🧠 Key Learnings

* Kafka requires **proper listener configuration**
* Cluster must be fully formed before topic creation
* MirrorMaker enables **real-time replication**
* Partitioning improves **parallel processing**

-
