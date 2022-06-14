package com.oracle.kafka;

import com.oracle.kafka.syncandasync.SynchronousProducer;
import io.helidon.microprofile.tests.junit5.HelidonTest;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;

/**
 * Check whether getting it is taking time to send the data or not
 */
@HelidonTest
public class SynchronousProducerTest {

    @Inject
    private SynchronousProducer syncProducer;

    @Test
    public void testSyncProducer(){
        syncProducer.produce();
    }

}
