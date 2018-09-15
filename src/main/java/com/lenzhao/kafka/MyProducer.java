package com.lenzhao.kafka;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;

public class MyProducer {

    public static Properties initConfig() {
        Properties kafkaProps = new Properties();
        kafkaProps.put("bootstrap.servers", "localhost:9092");
        kafkaProps.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        kafkaProps.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        return kafkaProps;
    }
    public static void main(String[] args) {
        Properties kafkaProps = initConfig();
        KafkaProducer<String, String> producer = new KafkaProducer<>(kafkaProps);

        ProducerRecord<String, String> record =new ProducerRecord<>("test", "messageKey", "messageValue");
        try {
            producer.send(record);
            producer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
