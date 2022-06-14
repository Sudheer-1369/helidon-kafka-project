package com.oracle.kafka;

import com.oracle.kafka.syncandasync.AsynchronousProducer;
import io.helidon.microprofile.tests.junit5.HelidonTest;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;

@HelidonTest
public class AsynchronousProducerTest {

    @Inject
    @ConfigProperty(name = "kafka.async.topic")
    private String asyncTopic;

    @Inject
    private AsynchronousProducer asynchronousProducer;

    @Test
    public void testAsyncProducer(){
       asynchronousProducer.produce();
    }
}
