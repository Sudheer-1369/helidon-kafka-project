package com.oracle.kafka;

import com.oracle.kafka.simple.SimpleConsumer;
import io.helidon.microprofile.tests.junit5.HelidonTest;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;

/**
 * Checking whether we are able to consume the data completely or not
 */
@HelidonTest
public class SimpleConsumerTest {

    @Inject
    @ConfigProperty(name = "kafka.topic")
    private String outgoingTopic;

    @Inject
    private SimpleConsumer consumer;

    @Test
    public void testConsumer(){
        consumer.listen(outgoingTopic);
    }
}
