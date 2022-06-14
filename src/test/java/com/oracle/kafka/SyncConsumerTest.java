package com.oracle.kafka;

import com.oracle.kafka.syncandasync.SyncConsumer;
import io.helidon.microprofile.tests.junit5.HelidonTest;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;

@HelidonTest
public class SyncConsumerTest {

    @Inject
    @ConfigProperty(name = "kafka.sync.topic")
    private String topic;

    @Inject
    private SyncConsumer consumer;

    @Test
    public void testConsumer(){
        consumer.listen(topic);
    }
}
