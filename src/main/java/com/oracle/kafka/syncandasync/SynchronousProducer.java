package com.oracle.kafka.syncandasync;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.config.SaslConfigs;
import org.apache.kafka.common.serialization.StringSerializer;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.Properties;

@ApplicationScoped
public class SynchronousProducer {
    private final String bootStrapServers;
    private final String topic;
    private final String saslMechanism;

    @Inject
    public SynchronousProducer(@ConfigProperty(name = "kafka.bootstrapserver") String bootStrapServers,
                               @ConfigProperty(name = "kafka.sync.topic") String topic,
                               @ConfigProperty(name = "kafka.sasl.mechanism") String saslMechanism
    ) {
        this.bootStrapServers = bootStrapServers;
        this.topic = topic;
        this.saslMechanism = saslMechanism;
    }

    public void produce() {
        Properties properties = new Properties();

        properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootStrapServers);
        properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.setProperty(SaslConfigs.SASL_MECHANISM, saslMechanism);
        properties.setProperty(ProducerConfig.ACKS_CONFIG, "1");

        KafkaProducer<String, String> producer = new KafkaProducer<>(properties);


        RecordMetadata recordMetadata = null;
        try {
            for (int i = 0; i < 10000; i++) {
                recordMetadata =
                        producer.send(new ProducerRecord<>(topic, "Mounika", "Sudheer"))
                                .get();
                System.out.println("\n The message is sent into the topic " + recordMetadata.topic() + " and the partition " + recordMetadata.partition() + " and the offset " + recordMetadata.offset());
                System.out.println("SynchronousProducer completed with success \n");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            producer.flush();
            producer.close();
        }

    }
}
