package ch.mednota.exporter.kafka.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducer {

    private final KafkaTemplate<Long, Object> kafkaTemplate;

    @Autowired
    public KafkaProducer(KafkaTemplate<Long, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void send(String topic, Long key, Object message) {
        kafkaTemplate.send(topic, key, message);
    }
}
