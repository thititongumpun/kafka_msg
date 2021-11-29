package com.github.thititongumpun.kafka;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;

public class Consumer1AssignandSeek {
    public static void main(String[] args) {
        final Logger logger = LoggerFactory.getLogger(Consumer1AssignandSeek.class.getName());

        String bootstrapServer = "127.0.0.1:9092";
        String topic = "second_topic";

        Properties props = new Properties();
        props.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServer);
        props.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        props.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        //earliest, latest, none
        props.setProperty(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");

        KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(props);
//        consumer.subscribe(Collections.singleton(topic));
        consumer.subscribe(Arrays.asList(topic));

        //assign and seek
        TopicPartition partitionToReadFrom = new TopicPartition(topic, 0);
        consumer.assign(Arrays.asList(partitionToReadFrom));

        long offsetToReadFrom = 15L;
        consumer.seek(partitionToReadFrom, offsetToReadFrom);

        int numberOfMessagesToRead = 5;
        boolean keepOnReading = true;
        int numberOfMessagesSoFar = 0;
        while (true) {
//            consumer.poll(100);
            ConsumerRecords<String, String> records =  consumer.poll(Duration.ofMillis(100));

            for (ConsumerRecord<String, String> record : records) {
                numberOfMessagesSoFar++;
                logger.info("Key:" + record.key() + ", Value: " + record.value());
                logger.info("Partition: " + record.partition() + ", Offset: " + record.offset());
                if (numberOfMessagesSoFar >= numberOfMessagesToRead) {
                    keepOnReading = false;
                    break;
                }
                logger.info("Exiting the application");
            }
        }
    }
}
