package com.oracle.kafka;

import com.oracle.kafka.simple.SimpleProducer;
import io.helidon.microprofile.tests.junit5.HelidonTest;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;

/**
 * Checking if we are able to send the data properly or not
 */
@HelidonTest
public class SimpleProducerTest {

    @Inject
    private SimpleProducer producer;

    @Test
    public void testProducer(){
        producer.produce();
    }
}
