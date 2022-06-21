package com.oracle.reactivestreams;

import io.helidon.microprofile.tests.junit5.HelidonTest;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.concurrent.SubmissionPublisher;

@HelidonTest
public class ReactiveProcessorTest {

    @Test
    public void testProcessAndPublisherTest(){

        SubmissionPublisher<String> publisher = new SubmissionPublisher<>();
        TransformProcessor<String,Integer> transformProcessor = new TransformProcessor<>(Integer::parseInt);
        EndSubscriber<Integer> subscriber = new EndSubscriber<>();

        publisher.subscribe(transformProcessor);
        transformProcessor.subscribe(subscriber);

        List<String> items = List.of("1","2","3","4","5","6");

        items.forEach(publisher::submit);

        publisher.close();

    }

}
