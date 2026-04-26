package com.Kafka_Demo.Repository;

import com.Kafka_Demo.Entity.OrderEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<OrderEvent,Long> {
}
