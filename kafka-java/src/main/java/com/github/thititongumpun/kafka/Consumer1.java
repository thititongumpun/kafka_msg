package com.github.thititongumpun.kafka;

import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;

public class Consumer1 {
    public static void main(String[] args) {
        final Logger logger = LoggerFactory.getLogger(Consumer1.class.getName());

        String bootstrapServer = "127.0.0.1:9092";
        String groupId = "my-fourth-application";
        String topic = "second_topic";

        Properties props = new Properties();
        props.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServer);
        props.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        props.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        props.setProperty(ConsumerConfig.GROUP_ID_CONFIG, groupId);
        //earliest, latest, none
        props.setProperty(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");

        KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(props);
//        consumer.subscribe(Collections.singleton(topic));
        consumer.subscribe(Arrays.asList(topic));

        while (true) {
//            consumer.poll(100);
            ConsumerRecords<String, String> records =  consumer.poll(Duration.ofMillis(100));

            for (ConsumerRecord<String, String> record : records) {
                logger.info("Key:" + record.key() + ", Value: " + record.value());
                logger.info("Partition: " + record.partition() + ", Offset: " + record.offset());
            }
        }
    }
}
