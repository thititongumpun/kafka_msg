import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class ProducerSimple {
    public static void main(String[] args) throws Exception {
        final Logger logger = LoggerFactory.getLogger(ProducerSimple.class.getName());
        String topicName = "Kafka-Simple";

        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

        KafkaProducer<String, String> producer = new KafkaProducer<String, String>(props);


        for (int i = 0; i < 10; ++i) {
            ProducerRecord<String, String> record = new ProducerRecord<String, String>(topicName, Integer.toString(i), Integer.toString(i));
            producer.send(record);
            logger.info("Key " + record.key());
            logger.info("Value " + record.value());
            TimeUnit.SECONDS.sleep(2);
        }
        System.out.println("Msg Send Successful");
        producer.close();
    }
}
