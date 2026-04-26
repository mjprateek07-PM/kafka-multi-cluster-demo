package com.Kafka_Demo.Service;

import com.Kafka_Demo.Entity.OrderEvent;
import com.Kafka_Demo.Repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ConsumerService {

    private final OrderRepository repo;

    @KafkaListener(topics = "orders", groupId = "order-group")
    public void consume(String msg) {
        OrderEvent e = new OrderEvent();
        e.setMessage(msg);
        repo.save(e);
        System.out.println("Saved: " + msg);
    }
}
