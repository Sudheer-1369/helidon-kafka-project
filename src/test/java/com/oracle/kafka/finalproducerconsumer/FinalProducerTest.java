package com.oracle.kafka.finalproducerconsumer;

import com.oracle.kafka.finalproducerandconsumer.FinalProducer;
import io.helidon.microprofile.tests.junit5.HelidonTest;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;

@HelidonTest
public class FinalProducerTest {

    @Inject
    private FinalProducer finalProducer;

    @Test
    void testFinalProducer() {
        finalProducer.produce();
    }
}
