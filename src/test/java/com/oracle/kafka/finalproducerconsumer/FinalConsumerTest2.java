package com.oracle.kafka.finalproducerconsumer;

import com.oracle.kafka.finalproducerandconsumer.FinalConsumer;
import io.helidon.microprofile.tests.junit5.HelidonTest;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;

@HelidonTest
public class FinalConsumerTest2 {

    @Inject
    @ConfigProperty(name = "kafka.final.topic")
    private String finalTopic;

    @Inject
    private FinalConsumer finalConsumer;

    @Test
    void testFinalConsumer() {
        finalConsumer.listen(finalTopic);
    }
}
