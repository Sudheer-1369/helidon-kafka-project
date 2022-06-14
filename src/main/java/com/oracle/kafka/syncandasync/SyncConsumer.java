package com.oracle.kafka.syncandasync;

import com.oracle.kafka.ConsumeAndProcess;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

/**
 * No big difference between consumer and SyncConsumer just to get the feed from a difference topic and a consumer group
 */
@ApplicationScoped
public class SyncConsumer {
    private final String bootStrapServers;
    private final String saslMechanism;
    private final String consumerGroupId;
    private final String autoOffset;
    private KafkaConsumer<String, String> consumer;
    private ConsumerRecords<String, String> records;
    @Inject
    public SyncConsumer(@ConfigProperty(name = "kafka.bootstrapserver") String bootStrapServers
            , @ConfigProperty(name = "kafka.sasl.mechanism") String saslMechanism
            , @ConfigProperty(name = "kafka.sync.consumer.group.id") String consumerGroupId
            , @ConfigProperty(name = "kafka.consumer.auto.offset.reset") String autoOffset) {
        this.bootStrapServers = bootStrapServers;
        this.saslMechanism = saslMechanism;
        this.consumerGroupId = consumerGroupId;
        this.autoOffset = autoOffset;
    }

    public void listen(String incomingTopic) {

        Properties properties = new Properties();
        properties.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootStrapServers);
        properties.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.setProperty(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, autoOffset);
        properties.setProperty(ConsumerConfig.GROUP_ID_CONFIG, consumerGroupId);
        properties.setProperty(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "true");
        properties.setProperty(ConsumerConfig.MAX_POLL_RECORDS_CONFIG, "2000");

        consumer = new KafkaConsumer<>(properties);

        consumer.subscribe(Collections.singletonList(incomingTopic));

        while (true) {
            records = consumer.poll(Duration.ofMillis(5000));

            System.out.println("Entered the poll");
            int count = 0;
            for (ConsumerRecord<String, String> record : records) {
                count++;
                System.out.println("From the topic " + record.topic() + " and partition " + record.partition() + " and offset " + record.offset() + "The count is " + count);
                ConsumeAndProcess.process(record.value());
            }
            System.out.println("The Count is " + count);
            System.out.println("Exiting from the poll");
        }
    }
}
