package com.oracle.kafka.syncandasync;

import com.oracle.kafka.partitioner.CustomPartitioner;
import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.config.SaslConfigs;
import org.apache.kafka.common.serialization.StringSerializer;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.Properties;

@ApplicationScoped
public class AsynchronousProducer {
    private final String bootStrapServers;
    private final String topic;
    private final String saslMechanism;

    @Inject
    public AsynchronousProducer(@ConfigProperty(name = "kafka.bootstrapserver") String bootStrapServers,
                                @ConfigProperty(name = "kafka.async.topic") String topic,
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
        properties.setProperty(ProducerConfig.MAX_IN_FLIGHT_REQUESTS_PER_CONNECTION, "1");
        properties.setProperty(ProducerConfig.PARTITIONER_CLASS_CONFIG, CustomPartitioner.class.getSimpleName());
        KafkaProducer<String, String> producer = new KafkaProducer<>(properties);

        for (int i = 0; i < 10000; i++) {
            producer.send(new ProducerRecord<>(topic, "sudheer", "Mounika"), new MyCallBack());
        }

        producer.flush();
        producer.close();
    }

}

class MyCallBack implements Callback {

    @Override
    public void onCompletion(RecordMetadata recordMetadata, Exception e) {
        if (e == null) {
            System.out.println("\n The message is sent into the topic " + recordMetadata.topic() + " and the partition " + recordMetadata.partition() + " and the offset " + recordMetadata.offset());
            System.out.println("The message is received by the broker successfully");
        } else {
            System.out.println("Got an error");
            e.printStackTrace();
        }
    }
}
